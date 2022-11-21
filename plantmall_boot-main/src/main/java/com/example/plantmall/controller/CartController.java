package com.example.plantmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.plantmall.domain.Cart;
import com.example.plantmall.domain.CartItem;
import com.example.plantmall.domain.Product;
import com.example.plantmall.domain.User;
import com.example.plantmall.service.CartService;
import com.example.plantmall.service.ProductService;

@Controller
@RequestMapping("/cart")
@SessionAttributes("sessionCart")
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	
	@ModelAttribute("sessionCart")
	public Cart createCart(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("sessionCart");
		if (cart == null) {
			cart = new Cart();
			System.out.println("createCart 카트 널\n\n");
			session.setAttribute("sessionCart", cart);
		}
		return cart;
	}
	
	@RequestMapping("/list")
	public ModelAndView getCartItems(HttpSession session, ModelAndView mav, SessionStatus status) throws Exception {
		Cart cart = (Cart) session.getAttribute("sessionCart");

		Map<String, CartItem> cartItemMap = new HashMap<String, CartItem>();
		if (cart.getItemMap().isEmpty()) {
			cart.setItemMap(cartItemMap);
		}
		cartItemMap = cart.getItemMap();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// session에 저장된 user
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
		if (userSession == null) {
			session.removeAttribute("sessionCart");
			status.setComplete();
			return new ModelAndView("auth/loginForm");
		}
		User user = userSession.getUser();


		String userId = user.getUserId();	

		List<CartItem> cartList = cartService.getCartItemList(userId);
		
		CartItem cartItem;
		Product product;

//		if (cartItemMap.isEmpty()) {
//			System.out.println("\ncartItemMap.isEmpty()");
//			for (int i = 0; i < cartList.size(); i++) {
//				cartItem = cartList.get(i);
//				product = productService.getProduct(cartItem.getProductId());
//				cartItem.setProduct(product);
//				System.out.println("\n cartItem.getProductId() :" + cartItem.getProductId());
//				System.out.println("cartItem.product.getP_name() :" + cartItem.getProduct().getP_name() + "\n");
//				cartItemMap.put(cartItem.getProductId(), cartItem);	
//				System.out.println(cartItem);
//			}
//		}
		
		for (int i = 0; i < cartList.size(); i++) {
			cartItem = cartList.get(i);
			product = productService.getProduct(cartItem.getProductId());
			cartItem.setProduct(product);
			cartItemMap.put(cartItem.getProductId(), cartItem);	
		}
		
		int sumMoney = (cartService.sumMoney(userId) == null) ? 0 : cartService.sumMoney(userId);
		int fee = 3000;
		
		map.put("sumMoney", sumMoney);
		map.put("fee", fee);
		map.put("sum", sumMoney + fee);
		map.put("cartItemList", cartList);
		map.put("numberOfItems", cartList.size());
		mav.setViewName("Cart");
		mav.addObject("cart", map);
		return mav;
	}
	
	@RequestMapping(value="/addItemToCart", method= {RequestMethod.POST})
	@ResponseBody
	public int addItemToCart(@RequestParam("productId") String productId, @RequestParam("quantity") int quantity,
			@ModelAttribute("sessionCart") Cart cart, HttpSession session, SessionStatus status) throws Exception {
		
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if (userSession == null) {
			session.removeAttribute("sessionCart");
			status.setComplete();
			return -1;
		}
		User user = userSession.getUser();
		System.out.println(user);
		
		String userId= user.getUserId();
		System.out.println("\n addItemToCart");

		Product product = productService.getProduct(productId);
		CartItem cartItem = new CartItem(userId, productId, quantity, product.getPrice(), quantity * product.getPrice(), product);
		
		if (cart.containsProductId(productId)) {
			System.out.println("containsProduct");
			return 0;
		}
		else {
			System.out.println("cartItem: " + cartItem);
			cart.addProduct(cartItem);
			cartService.insertCartItem(cartItem);
		}
		return 1;
	}
	
	@RequestMapping("/deleteCartItem")
	public String deleteCartItem(HttpSession session, @RequestParam("productId") String productId, @RequestParam("userId") String userId, 
			@ModelAttribute("sessionCart") Cart cart) throws Exception {
		System.out.println("\n\n deleteCartItem \n\n");
		cartService.deleteCartItem(cart.getItemMap().get(productId));
		cart.getItemMap().remove(productId);
		return "redirect:/cart/list";
	}
	
	@RequestMapping("/updateCartItem")
	public String updateCartItem(@RequestParam("productId") String productId, @RequestParam("quantity") int quantity,
			@ModelAttribute("sessionCart") Cart cart) throws Exception {
		System.out.println("\n\n updateCartItem- productId: "+ productId + ", quantity: " + quantity + "\n\n");
		cart.setQuantityByProductId(productId, quantity);
		cartService.updateCartItem(cart.getItemMap().get(productId));
		return "redirect:/cart/list";
	}
	
}
