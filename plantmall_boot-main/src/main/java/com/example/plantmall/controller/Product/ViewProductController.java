package com.example.plantmall.controller.Product;

import java.util.List;

import javax.servlet.http.HttpSession;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.plantmall.service.AuthService;
import com.example.plantmall.service.EnquiryService;
import com.example.plantmall.service.ProductService;
import com.example.plantmall.service.ReviewService;
import com.example.plantmall.controller.UserSession;
import com.example.plantmall.domain.EnqComm;
import com.example.plantmall.domain.Enquiry;
import com.example.plantmall.domain.Product;
import com.example.plantmall.domain.Review;

@Controller
public class ViewProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private AuthService authService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private EnquiryService enquiryService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 제품 목록 페이지
	@RequestMapping("/product/list")
	public String showProductList(@RequestParam(value = "categoryId", required = false) String categoryId,
			ModelMap model) throws Exception {
		List<Product> itemList;
		String categoryName;

		// 모든 제품 목록 보기
		if (categoryId == null) {
			itemList = this.productService.getAllProduct();
			categoryName = "전체";
		}
		// 카테고리별 제품 목록 보기
		else {
			itemList = this.productService.getProductListByCategory(categoryId);
			categoryName = this.productService.getCategory(categoryId).getC_name();
		}
		List<List<Product>> productList = new ArrayList<>();
		List<List<String>> userNameList = new ArrayList<>();

		try {
			for (int i = 0; i < itemList.size(); i += 4) {
				List<Product> miniProductList = new ArrayList<>();
				List<String> miniUserNameList = new ArrayList<>();

				productList.add(miniProductList);
				userNameList.add(miniUserNameList);
				for (int j = 0; j < 4; j++) {
					String userName = this.productService.getUserName(itemList.get(i+j).getUserId());
					miniProductList.add(itemList.get(i + j));
					miniUserNameList.add(userName);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("더 이상 제품을 찾을 수 없음");
		}
		System.out.println(categoryName + " 카테고리 제품 model -> view 전달");

		model.put("categoryName", categoryName);
		model.put("productList", productList);
		model.put("userNameList", userNameList);

		return "product/productList";
	}

	// 제품 상세 페이지
	@RequestMapping("/product/detail")
	public String showProductDetail(@RequestParam(value = "productId", required = false) String productId,
			ModelMap model, HttpSession session) throws Exception {
		Product product = this.productService.getProduct(productId);
		String seller = this.productService.getUserName(product.getUserId());
		
		// 배송예정일(현재 시각으로 부터 3일 뒤)
		LocalDate after3days = LocalDate.now().plusDays(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 ");
		DayOfWeek dayOfWeek = after3days.getDayOfWeek();
		String dayOfWeekKor = "";
		switch (dayOfWeek.getValue()) {
		case 1:
			dayOfWeekKor = "(월) ";
			break;
		case 2:
			dayOfWeekKor = "(화) ";
			break;
		case 3:
			dayOfWeekKor = "(수) ";
			break;
		case 4:
			dayOfWeekKor = "(목) ";
			break;
		case 5:
			dayOfWeekKor = "(금) ";
			break;
		case 6:
			dayOfWeekKor = "(토) ";
			break;
		case 7:
			dayOfWeekKor = "(일) ";
			break;
		}
		String orderDate = after3days.format(formatter) + dayOfWeekKor;

		// productDetail에서 보일 리뷰 리스트
		List<Review> list = reviewService.getReviewsByProductId(productId);
		if (list != null) {
			product.setReviews(list);
			for (int i = 0; i < list.size(); i++) {
				String reviewUserName = authService.getUserById(list.get(i).getUserId()).getUserName();
				product.getReviews().get(i).setUserName(reviewUserName);
			}
		}

		// productDetail에서 보일 문의 리스트
		List<Enquiry> enquiryList = enquiryService.getEnquiryListByProductId(productId);
		if (enquiryList != null) {
			product.setEnquiryList(enquiryList);
			for (int i = 0; i < enquiryList.size(); i++) {
				String enquiryUserName = authService.getUserById(enquiryList.get(i).getUserId()).getUserName();
				product.getEnquiryList().get(i).setUserName(enquiryUserName);
				EnqComm enqComm = enquiryService.getEnqCommByEuquiryId(enquiryList.get(i).getEnquiryId());
				product.getEnquiryList().get(i).setEnqComm(enqComm);
			}
		}

		// 문의를 수정, 답변할때 필요한 유저 확인용도로 보내야됨 (현재 세션에 있는 유저가 문의를 남긴 유저와 같으면 수정 버튼뜨거나 판매하는
		// 유저이면 답변달기 버튼을 보여줘야됨)
		String sessionUserId = "noUser";
		UserSession userSession = (UserSession) session.getAttribute("userSession");

		if (userSession != null) {
			sessionUserId = userSession.getUser().getUserId();
		}

		model.put("product", product);
		model.put("seller", seller);
		model.put("orderDate", orderDate);
		model.addAttribute("reviewList", product.getReviews());
		model.addAttribute("enquiryList", product.getEnquiryList());
		model.addAttribute("sessionUserId", sessionUserId);

		System.out.println(product.getP_name() + " 제품 상세 model -> view 전달");

		return "product/productDetail";
	}

	// BEST 제품 페이지
	@RequestMapping("/product/best")
	public String showProductBest(ModelMap model) throws Exception {
		List<Product> itemList;

		itemList = this.productService.getBestProductList();

		List<List<Product>> productList = new ArrayList<>();
		List<List<String>> userNameList = new ArrayList<>();

		try {
			for (int i = 0; i < itemList.size(); i += 4) {
				List<Product> miniProductList = new ArrayList<>();
				List<String> miniUserNameList = new ArrayList<>();

				productList.add(miniProductList);
				userNameList.add(miniUserNameList);
				for (int j = 0; j < 4; j++) {
					String userName = this.productService.getUserName(itemList.get(i+j).getUserId());
					miniProductList.add(itemList.get(i + j));
					miniUserNameList.add(userName);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("더 이상 제품을 찾을 수 없음");
		}
		System.out.println("BEST 카테고리 제품 model -> view 전달");

		model.put("productList", productList);
		model.put("userNameList", userNameList);

		return "product/productBestList";
	}
}
