package com.marketkutty.marketkutty.service;


import com.marketkutty.marketkutty.model.dto.ProductDto;
import com.marketkutty.marketkutty.model.dto.responseDto.ProductRespDto;
import com.marketkutty.marketkutty.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductRespDto getProductList(String depth1){
        return productRepository.findAllDepth1(depth1);
    }

}
