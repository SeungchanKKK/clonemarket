package com.marketkutty.marketkutty.service;


import com.marketkutty.marketkutty.model.dto.responseDto.*;
import com.marketkutty.marketkutty.model.entity.Product;
import com.marketkutty.marketkutty.model.entity.ProductDetail;
import com.marketkutty.marketkutty.model.entity.category.Depth1;
import com.marketkutty.marketkutty.repository.MainProductRepository;
import com.marketkutty.marketkutty.repository.ProductDetailRepository;
import com.marketkutty.marketkutty.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    private final MainProductRepository mainProductRepository;


    public CategoryRespDto getCategoryList(){
        return productRepository.findCategory();
    }

    public ProductRespDto getDepth1List(String depth1){
        return productRepository.findAllDepth1(depth1);
    }

    public ProductDetailDto getProductDetail(Long productId) {
        ProductDetail productDetail = productDetailRepository.findByProductId(productId);
        ProductDetailDto productTotal= new ProductDetailDto(productDetail);
        return productTotal;

    }

    public ProductRespDto getDepth2List(String depth1, String depth2) {
        return productRepository.findAllDepth2(depth1,depth2);
    }

    public TotalProductDto getTotal() {
        String caten1= "001";
        String caten2= "002";
        String caten3= "003";
        List<Product> cate1 = mainProductRepository.findAllByDepth1_Id(caten1);
        List<Product> cate2 = mainProductRepository.findAllByDepth1_Id(caten2);
        List<Product> cate3 = mainProductRepository.findAllByDepth1_Id(caten3);


        TotalProductDto total = new TotalProductDto(cate1,cate2,cate3);
        return total;
    }
}
