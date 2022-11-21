package com.example.plantmall.controller.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchValueCommand {
	private String categoryId;
	private String keyword;
	private Integer lowPrice;
	private Integer highPrice;
}
