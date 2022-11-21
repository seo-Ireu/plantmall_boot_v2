package com.example.plantmall.controller.Funding;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.example.plantmall.controller.OrderForm;
import com.example.plantmall.controller.UserForm;
import com.example.plantmall.controller.UserSession;
import com.example.plantmall.domain.EnqComm;
import com.example.plantmall.domain.Enquiry;
import com.example.plantmall.domain.Funding;
import com.example.plantmall.domain.FundingOrder;
import com.example.plantmall.domain.LineItem;
import com.example.plantmall.domain.Product;
import com.example.plantmall.domain.Review;
import com.example.plantmall.domain.User;
import com.example.plantmall.service.AuthService;
import com.example.plantmall.service.EnquiryService;
import com.example.plantmall.service.FundingRelationService;
import com.example.plantmall.service.FundingService;
import com.example.plantmall.service.ProductService;
import com.example.plantmall.service.ReviewService;

@Controller
@RequestMapping("/funding")
public class FundingController {

	@Value("funding/fundingForm")
	private String formViewName;
	
	@Autowired
	private FundingService fundingService;	
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FundingRelationService fundingRelationService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private EnquiryService enquiryService;
	
	@ModelAttribute("fundingForm")
	public FundingForm formBackingObject() throws Exception{
		return new FundingForm();
	}
	
	@ModelAttribute("creditCardTypes")
	public List<String> referenceData() {
		ArrayList<String> creditCardTypes = new ArrayList<String>();
		creditCardTypes.add("Visa");
		creditCardTypes.add("MasterCard");
		return creditCardTypes;			
	}
	
	@RequestMapping(path={"/create","/update"},method=RequestMethod.GET)
	public ModelAndView showForm(@RequestParam(value="fundingId", required=false)String fundingId,HttpServletRequest request) {
		UserSession userSession=
				(UserSession)WebUtils.getSessionAttribute(request, "userSession");
		
		ModelAndView mav = new ModelAndView();
		
		if(userSession!=null) {
			List<Product> list = productService.getProductListforUser(userSession.getUser().getUserId());
			mav.addObject("productList",list);
			mav.setViewName(formViewName);

			if(fundingId==null) {
			mav.addObject("fundingForm",new FundingForm());				
			}else {
				mav.addObject("fundingForm",new FundingForm(fundingService.getFunding(fundingId)));				
			}
		}else {
			mav.setViewName("/auth/error");
			mav.addObject("errorMessage", "로그인을 해주세요.");
		}
		return mav;
	}
	
	@RequestMapping(path="/create",method=RequestMethod.POST)
	public ModelAndView submitted(HttpServletRequest request, @ModelAttribute("fundingForm") FundingForm fundingForm) {
		UserSession userSession=
				(UserSession)WebUtils.getSessionAttribute(request, "userSession");
		
		ModelAndView mav = new ModelAndView();
		
		if(userSession!=null) {
			if(fundingForm.getFunding().getFundingId() !=null) {
				fundingService.updateFunding(fundingForm.getFunding());
				System.out.println("funding 수정");
				
			}else {
				fundingForm.getFunding().setSellerId(userSession.getUser().getUserId());
				fundingService.insertFunding(fundingForm.getFunding());
				System.out.println("funding 생성");
			}
			mav.setViewName("redirect:"+"/funding");
		}else {
			mav.setViewName("/auth/error");
			mav.addObject("errorMessage", "로그인을 해주세요.");
		}
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView fundingMain()
	{
		ModelAndView mav = new ModelAndView();
		
		List<Funding> list = fundingService.getAllFundingList();
		
		mav.setViewName("funding/fundingList2");
		mav.addObject("fundingList",list);
		return mav;
		
	}
	
	@RequestMapping("/{fundingId}")
	public ModelAndView viewFundingDetail(@PathVariable String fundingId, HttpServletRequest request) {
		UserSession userSession=
				(UserSession)WebUtils.getSessionAttribute(request, "userSession");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("funding/funding2");
		
		Funding funding = fundingService.getFunding(fundingId);
		Product product = funding.getProduct();
		
		mav.addObject("funding",funding);
		mav.addObject("product", product);
		
		
		// productDetail에서 보일 리뷰 리스트
		List<Review> list = reviewService.getReviewsByProductId(product.getProductId());
		if (list != null) {
			product.setReviews(list);
			for (int i = 0; i < list.size(); i++) {
				String reviewUserName = authService.getUserById(list.get(i).getUserId()).getUserName();
				product.getReviews().get(i).setUserName(reviewUserName);
			}
		}

		// productDetail에서 보일 문의 리스트
		List<Enquiry> enquiryList = enquiryService.getEnquiryListByProductId(product.getProductId());
		if (enquiryList != null) {
			product.setEnquiryList(enquiryList);
			for (int i = 0; i < enquiryList.size(); i++) {
				String enquiryUserName = authService.getUserById(enquiryList.get(i).getUserId()).getUserName();
				product.getEnquiryList().get(i).setUserName(enquiryUserName);
				EnqComm enqComm = enquiryService.getEnqCommByEuquiryId(enquiryList.get(i).getEnquiryId());
				product.getEnquiryList().get(i).setEnqComm(enqComm);
			}
		}
		System.out.println(product.getReviews());
		System.out.println(product.getEnquiryList());
		mav.addObject("reviewList", product.getReviews());
		mav.addObject("enquiryList", product.getEnquiryList());
		mav.addObject("isSeller", false);
		
		if(userSession!=null) {
			if(funding.getSellerId().equals(userSession.getUser().getUserId())) {
				mav.addObject("isSeller", true);
				return mav;
			}
		}
		return mav;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteFunding(@RequestParam(value="fundingId", required=false) String fundingId, HttpSession session) {
		UserSession userSession=
				(UserSession)session.getAttribute("userSession");

		if(fundingId !=null) {
			fundingService.deleteFunding(fundingId);
		}
		
		return fundingMain();
	}
	
	//바로 주문
	@RequestMapping("/newOrder")
	public ModelAndView initNewOrder(@RequestParam("fundingId") String fundingId, @RequestParam("quantity") int quantity, ModelAndView mav, HttpSession session) throws ModelAndViewDefiningException {
		UserSession userSession = (UserSession) session.getAttribute("userSession");

		if (userSession == null) {
			return new ModelAndView("auth/loginForm");
		}
		User user = userSession.getUser();
		
		Funding funding = fundingService.getFunding(fundingId);
		Product product = funding.getProduct();
		
		FundingOrderForm fundingOrderForm = new FundingOrderForm();

		fundingOrderForm.getFundingOrder().initOrder(user, fundingId, quantity, product.getPrice(),fundingOrderForm.getFundingOrder().getCreditCard(),fundingOrderForm.getFundingOrder().getExpiryDate());
		
		mav.addObject("newOrder", 1);	// 제품 상세 페이지에서 바로 주문한거인지 확인하기 위한 용도(폼에서 submit하면 newOrderSubmitted에서 확인함)
		mav.addObject("fundingOrderForm", fundingOrderForm);
		mav.addObject("product", product);
		mav.setViewName("funding/fundingOrderForm");
		return mav;
	}
	
	@RequestMapping("/newOrderSubmitted")
	public ModelAndView newOrderSubmitted (@Valid @ModelAttribute("fundingOrderForm") FundingOrderForm fundingOrderForm, BindingResult result, SessionStatus sessionStatus, HttpSession session) {
		if (result.hasErrors()) {
			return new ModelAndView("order/OrderForm");
		}

		fundingRelationService.insertFundingOrder(fundingOrderForm.getFundingOrder());
		
		String fundingId = fundingOrderForm.getFundingOrder().getFundingId();
		Funding funding = fundingService.getFunding(fundingId);

		
		ModelAndView mav = new ModelAndView("funding/FundingOrderDetail");
		mav.addObject("order", fundingOrderForm.getFundingOrder());
		mav.addObject("funding", funding);
		
		return mav;
	}

	
	@RequestMapping("/order")
	public ModelAndView viewFundingOrderList(HttpServletRequest request) {
		UserSession userSession=
				(UserSession)WebUtils.getSessionAttribute(request, "userSession");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("funding/fundingOrderList");
		
		List<FundingOrder> fundingOrderList = fundingRelationService.getAllFundingOrderListByBuyerId(userSession.getUser().getUserId());
		List<Funding> myFundingList = fundingService.getAllMyFundingList(userSession.getUser().getUserId());
		
		mav.addObject("fundingOrderList",fundingOrderList);
		mav.addObject("myFundingList", myFundingList);
		return mav;
	}	

	@RequestMapping("/order/{fundingRelationId}")
	public ModelAndView viewFundingOrderDetail(@PathVariable String fundingRelationId, HttpServletRequest request) {
		UserSession userSession=
				(UserSession)WebUtils.getSessionAttribute(request, "userSession");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("funding/FundingOrderDetail");
		
		FundingOrder fundingOrder = fundingRelationService.getFundingOrder(fundingRelationId);
		Funding funding = fundingService.getFunding(fundingOrder.getFundingId());
		
		System.out.println(funding);
		mav.addObject("order", fundingOrder);
		mav.addObject("funding",funding);
		return mav;
	}
	
	@RequestMapping(value="/order/delete", method={RequestMethod.POST})
	@ResponseBody
	public Boolean deleteFundingOrder(@RequestParam String fundingRelationId, HttpServletRequest request) {
		UserSession userSession=
				(UserSession)WebUtils.getSessionAttribute(request, "userSession");
		fundingRelationService.deleteFundingOrder(fundingRelationId);
		
		return true;
	}	
	
	
	@RequestMapping(value="/buyerList", method= {RequestMethod.POST})
	@ResponseBody
	public List<FundingOrder> viewMyFundingOrderDetail(@RequestParam String fundingId, HttpServletRequest request) {
		UserSession userSession=
				(UserSession)WebUtils.getSessionAttribute(request, "userSession");
		
		List<FundingOrder> orderList = fundingRelationService.getAllMyFundingOrderList(fundingId); 
		System.out.println(orderList);
		return orderList;
	}	
	
}
