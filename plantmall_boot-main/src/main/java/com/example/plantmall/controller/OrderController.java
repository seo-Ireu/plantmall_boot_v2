package com.example.plantmall.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.example.plantmall.domain.Cart;
import com.example.plantmall.domain.LineItem;
import com.example.plantmall.domain.Order;
import com.example.plantmall.domain.Product;
import com.example.plantmall.domain.User;
import com.example.plantmall.service.CartService;
import com.example.plantmall.service.OrderService;
import com.example.plantmall.service.ProductService;

@Controller
@RequestMapping("/order")
@SessionAttributes({"sessionCart", "orderForm"})
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	
	@ModelAttribute("orderForm")
	public OrderForm createOrderForm() {
		System.out.println("@ModelAttribute(orderForm) work\n");
		return new OrderForm();
	}
	
	@ModelAttribute("creditCardTypes")
	public List<String> referenceData() {
		ArrayList<String> creditCardTypes = new ArrayList<String>();
		creditCardTypes.add("Visa");
		creditCardTypes.add("MasterCard");
		return creditCardTypes;			
	}
	
	@RequestMapping("/newOrder")
	public ModelAndView initNewOrder(@RequestParam(value="chkbox") String productIdArray,
			@ModelAttribute("sessionCart") Cart cart,  @ModelAttribute("orderForm") OrderForm orderForm, ModelAndView mav, HttpSession session) throws ModelAndViewDefiningException {
		UserSession userSession = (UserSession) session.getAttribute("userSession");

		if (userSession == null) {
			return new ModelAndView("auth/loginForm");
		}
		User user = userSession.getUser();
		String[] productId = productIdArray.split(",");

		if (orderForm.getOrder().getLineItems().size() == 0) {
			for (int i = 0; i < productId.length; i++) {

				orderForm.getOrder().addLineItem(cart.getItemMap().get(productId[i].trim()));
			}
			orderForm.getOrder().initOrder(user, orderForm.getOrder().getLineItems());
		}

		mav.addObject("pdNewOrder", 0);	// 제품 상세 페이지에서 바로 주문한거인지 확인하기 위한 용도(폼에서 submit하면 newOrderSubmitted에서 확인함)
		mav.addObject("productIdArray", productIdArray);
		mav.setViewName("order/OrderForm");
		return mav;
	}
	
	// 제품 상세 페이지에서 바로 주문
	@RequestMapping("/PDnewOrder")
	public ModelAndView initNewOrder(@RequestParam("productId") String productId, @RequestParam("quantity") int quantity, 
			@ModelAttribute("orderForm") OrderForm orderForm, ModelAndView mav, HttpSession session) throws ModelAndViewDefiningException {
		UserSession userSession = (UserSession) session.getAttribute("userSession");

		if (userSession == null) {
			return new ModelAndView("auth/loginForm");
		}
		User user = userSession.getUser();
		
		if (orderForm.getOrder().getLineItems().size() == 0) {
			Product product = productService.getProduct(productId);
			LineItem lineItem = new LineItem(0, 0, quantity, product.getPrice(), product, productId);
			System.out.println(lineItem);
			orderForm.getOrder().addLineItem(lineItem);
			orderForm.getOrder().initOrder(user, orderForm.getOrder().getLineItems());
			System.out.println(orderForm.getOrder());
		}

		mav.addObject("pdNewOrder", 1);	// 제품 상세 페이지에서 바로 주문한거인지 확인하기 위한 용도(폼에서 submit하면 newOrderSubmitted에서 확인함)
		mav.addObject("productIdArray", productId);
		mav.setViewName("order/OrderForm");
		return mav;
	}
	
	@RequestMapping("/orderReset")
	public String resetOrder(@ModelAttribute("orderForm") OrderForm orderForm, SessionStatus status, HttpSession session) throws ModelAndViewDefiningException {
		session.removeAttribute("orderForm");
		status.setComplete();
		return "redirect:/cart/list";
	}
	
	@RequestMapping("/newOrderSubmitted")
	public ModelAndView newOrderSubmitted (@Valid @ModelAttribute("orderForm") OrderForm orderForm, BindingResult result, 
			@RequestParam(value="chkbox") String productIdArray, @RequestParam(value="status") int status, @RequestParam(value="pd") int pd, 
			SessionStatus sessionStatus, HttpSession session) {
		if (result.hasErrors()) {
			String[] productId = productIdArray.split(",");
			ModelAndView mav = new ModelAndView("order/OrderForm");
			mav.addObject("pdNewOrder", pd);
			mav.addObject("productIdArray", productId);
			return mav;
		}
		
		orderService.insertOrder(orderForm.getOrder());
		ModelAndView mav = new ModelAndView("order/OrderDetail");
		mav.addObject("order", orderForm.getOrder());
		mav.addObject("status", status);
		
		// 카트에서 주문한거면 카트아이템에서 삭제해야됨
		if (pd == 0) {
			String[] productId = productIdArray.split(",");
			for (int i = 0; i < productId.length; i++) {
				cartService.deleteCartItem(orderForm.getOrder().getUserId(), productId[i].trim());
			}
		}
		
		session.removeAttribute("orderForm");
		sessionStatus.setComplete();
		return mav;
	}
	

	@RequestMapping("/list")
	public ModelAndView orderList(HttpSession session) throws Exception {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if (userSession == null) {
			return new ModelAndView("auth/loginForm");
		}
		User user = userSession.getUser();
		List<Order> orderList = orderService.getOrdersByUserId(user.getUserId());
		for (Order o : orderList) {
			Order o2 = orderService.getOrder(o.getOrderId());
			o.setLineItems(o2.getLineItems());
			for (LineItem item : o.getLineItems()) {
				item.setProduct(productService.getProduct(item.getProductId()));
			}
			o.setTotalPrice(o.setTotalPriceUsingLineItems(o.getLineItems()));
		}

		return new ModelAndView("order/OrderList", "orderList", orderList);
	}
	
	@RequestMapping("/orderDetail")
	public ModelAndView orderDetail(@RequestParam("orderId") int orderId, @RequestParam("status") int status) throws Exception {
		Order order = orderService.getOrder(orderId);
		for (LineItem item : order.getLineItems()) {
			item.setProduct(productService.getProduct(item.getProductId()));
		}
		order.setTotalPrice(order.setTotalPriceUsingLineItems(order.getLineItems()));
		ModelAndView mav = new ModelAndView("order/OrderDetail");
		mav.addObject("order", order);
		mav.addObject("status", status);
		return mav;
	}
}
