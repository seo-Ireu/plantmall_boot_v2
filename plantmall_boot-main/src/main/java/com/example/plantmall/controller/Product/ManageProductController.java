package com.example.plantmall.controller.Product;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.plantmall.domain.Category;
import com.example.plantmall.domain.Product;
import com.example.plantmall.service.ProductService;

@Controller
public class ManageProductController {

	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 제품 관리 목록
	@RequestMapping("/product/manage")
	public String showProductManage(@RequestParam(value = "userId") String userId, ModelMap model) throws Exception {
		List<Product> itemList = this.productService.getProductListforUser(userId);
		String userName = this.productService.getUserName(userId);

		List<List<Product>> productList = new ArrayList<>();

		try {
			for (int i = 0; i < itemList.size(); i += 4) {
				List<Product> miniProductList = new ArrayList<>();

				productList.add(miniProductList);
				for (int j = 0; j < 4; j++) {
					miniProductList.add(itemList.get(i + j));
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("더 이상 제품을 찾을 수 없음");
		}
		System.out.println("user: " + userName + "의 관리 페이지 model -> view 전달");

		model.put("userId", userId);
		model.put("userName", userName);
		model.put("productList", productList);

		return "product/productManage";
	}

	// 제품 등록
	@RequestMapping("/product/registration")
	public String showProductRegistration(@RequestParam(value = "userId") String userId, ModelMap model) {

		List<Category> categoryList = this.productService.getCategoryList();

		model.put("userId", userId);
		model.put("categoryList", categoryList);

		return "product/productRegistration";
	}

	// 제품 등록 완료
	@RequestMapping("/product/registrationResult")
	public String productRegistration(ProductWithImgCommand productWithImgCommand, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes rttr) throws Exception {
		
		// 제품 테이블 저장
		String productImgIdx = "img_" + productWithImgCommand.getP_name();
		Product product = new Product();

		product.setProductId(productWithImgCommand.getProductId());
		product.setCategoryId(productWithImgCommand.getCategoryId());
		product.setUserId(productWithImgCommand.getUserId());
		product.setP_name(productWithImgCommand.getP_name());
		product.setDescription(productWithImgCommand.getDescription());
		product.setPrice(productWithImgCommand.getPrice());
		product.setProductImg(productImgIdx);

		this.productService.insertProduct(product);

		// 제품 이미지 테이블 저장
		ProductImg productImg = new ProductImg(productImgIdx, productWithImgCommand.getProductImgValue().getBytes());
		productService.saveProductImage(productImg);

		System.out.println(product.getP_name() + " 제품 등록 완료");
		System.out.println(product);
		rttr.addFlashAttribute(product.getP_name() + " 제품이 등록되었습니다.");

		return "redirect:/product/manage?userId=" + product.getUserId();
	}

	// 제품 수정
	@RequestMapping("/product/modify")
	public String showProductModify(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "productId") String productId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		// 제품, 카테고리 가져오기
		Product product = this.productService.getProduct(productId);
		List<Category> categoryList = this.productService.getCategoryList();		
		
		model.put("userId", userId);
		model.put("product", product);
		model.put("categoryList", categoryList);

		System.out.println(product.getP_name() + " 제품 수정 페이지");

		return "product/productModify";
	}

	// 제품 수정 완료
	@RequestMapping("/product/modifyResult")
	public String productModify(ProductWithImgCommand productWithImgCommand, RedirectAttributes rttr) throws Exception {

		// 제품 테이블 저장
		String productImgIdx = "img_" + productWithImgCommand.getP_name();
		Product product = new Product();

		product.setProductId(productWithImgCommand.getProductId());
		product.setCategoryId(productWithImgCommand.getCategoryId());
		product.setUserId(productWithImgCommand.getUserId());
		product.setP_name(productWithImgCommand.getP_name());
		product.setDescription(productWithImgCommand.getDescription());
		product.setPrice(productWithImgCommand.getPrice());
		product.setProductImg(productImgIdx);

		this.productService.updateProduct(product);

		// 제품 이미지 테이블 수정
		if (!productWithImgCommand.getProductImgValue().isEmpty()) {	//새로 전달된 값이 있으면 수정, 아니면 유지
			ProductImg productImg = new ProductImg(productImgIdx, productWithImgCommand.getProductImgValue().getBytes());
			productService.updateProductImage(productImg);
		}
		System.out.println(product.getP_name() + " 제품 수정 완료");
		System.out.println(product);
		rttr.addFlashAttribute(product.getP_name() + " 제품이 수정되었습니다.");

		return "redirect:/product/manage?userId=" + product.getUserId();
	}

	// 제품 삭제
	@RequestMapping("/product/delete")
	public String showProductDelete(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "productId") String productId, ModelMap model) throws Exception {

		Product product = this.productService.getProduct(productId);
		List<Category> categoryList = this.productService.getCategoryList();

		model.put("userId", userId);
		model.put("product", product);
		model.put("categoryList", categoryList);

		System.out.println(product.getP_name() + " 제품 삭제 페이지");

		return "product/productDelete";
	}

	// 제품 삭제 완료
	@ResponseBody
	@RequestMapping("/product/deleteResult")
	public String productDelete(Product product, RedirectAttributes rttr) throws Exception {

		List<Product> checkProductinLineItem = this.productService.getProductHaveLineItem(product.getProductId());
		List<Product> checkProductinCartItem = this.productService.getProductHaveCartItem(product.getProductId());
		String resultmsg = "";

		if (checkProductinLineItem.size() > 0 || checkProductinCartItem.size() > 0 ) { // LineItem, CartItem에 담긴 제품은 삭제 불가
			System.out.println(product.getP_name() + " 제품을 삭제할 수 없음");
			resultmsg = "해당 제품을 삭제할 수 없음";
		} else {
			// 제품 이미지 테이블 삭제
			String productImgIdx = "img_" + product.getP_name();
			productService.deleteProductImage(productImgIdx);

			this.productService.deleteContentHaveProduct(product); // 해당 제품 관련 피드 있으면 먼저 삭제
			this.productService.deleteProduct(product); // 그다음 제품 삭제

			System.out.println(product.getP_name() + " 제품 삭제 완료");
			System.out.println(product);
			rttr.addFlashAttribute(product.getP_name() + " 제품이 삭제되었습니다.");

			resultmsg = "해당 제품이 삭제되었습니다";
		}

		return "<script>alert('"+resultmsg+"');location.href='/product/manage?userId=" + product.getUserId()+ "'</script>";
	}
	
	// 제품 이미지 가져오기
	@RequestMapping(value="/getByteImage")
	public ResponseEntity<byte[]> getByteImage(@RequestParam(value = "p_name", required = false) String p_name) {
		Map<String, Object> productImgMap; 
		byte[] imageContent = null;
		final HttpHeaders headers;
		
		if (p_name != null) {
			String fileId = "img_"+p_name; 
				
			System.out.println("==start to find image==");
			
			productImgMap = productService.selectProductImage(fileId); 
			try {
				imageContent = blobToBytes((Blob) productImgMap.get("PRODUCTIMGVALUE"));
			 } catch (Exception e) {
		            System.out.println("이미지를 찾을 수 없음");
		        }
			headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
		} else {
			imageContent = null;
			headers = new HttpHeaders();
		}
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
		
	// blob 데이터 -> 바이트 변환
    private static byte[] blobToBytes(Blob blob) throws SQLException, IOException {
        BufferedInputStream is = null;
        byte[] bytes = null;
        
        is = new BufferedInputStream(blob.getBinaryStream());
        bytes = new byte[(int) blob.length()];
        int len = bytes.length;
        int offset = 0;
        int read = 0;

        while (offset < len
                && (read = is.read(bytes, offset, len - offset)) >= 0) {
            offset += read;
        }

        return bytes;
    }
}
