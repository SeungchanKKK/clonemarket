package com.marketkutty.marketkutty.controller;

import com.marketkutty.marketkutty.model.dto.ProductDto;
import com.marketkutty.marketkutty.model.dto.responseDto.ProductRespDto;
import com.marketkutty.marketkutty.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/category/{depth1}")
    public ProductRespDto getProductList(@PathVariable String depth1){
        return productService.getProductList(depth1);
    }
}