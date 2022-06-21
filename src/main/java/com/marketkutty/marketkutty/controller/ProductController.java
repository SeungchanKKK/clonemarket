package com.marketkutty.marketkutty.controller;


import com.marketkutty.marketkutty.model.dto.ProductDto;
import com.marketkutty.marketkutty.model.dto.responseDto.*;
import com.marketkutty.marketkutty.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/category")
    public CategoryRespDto getCategoryList(){
        return productService.getCategoryList();
    }

    @GetMapping("/api/category/{depth1}")
    public ProductRespDto getDepth1List(@PathVariable String depth1){
        return productService.getDepth1List(depth1);
    }

    @GetMapping("/api/category/{depth1}/{depth2}")
    public ProductRespDto getDepth2List(@PathVariable String depth1, @PathVariable String depth2){
        return productService.getDepth2List(depth1, depth2);
    }

    @GetMapping("/api/product/{productId}")
    public ProductDetailDto getProductdetail(@PathVariable Long productId) {
        return productService.getProductDetail(productId);
    }

    @GetMapping("/api/product")
    public TotalProductDto getProducts() {
        return productService.getTotal();
    }
}