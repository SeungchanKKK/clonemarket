package com.marketkutty.marketkutty.repository;

import com.marketkutty.marketkutty.model.dto.ProductDto;
import com.marketkutty.marketkutty.model.dto.responseDto.ProductRespDto;
import com.marketkutty.marketkutty.model.entity.Product;
import com.marketkutty.marketkutty.model.entity.category.Depth1;
import com.marketkutty.marketkutty.model.entity.category.Depth2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public ProductRespDto findAllDepth1(String depth1){

        Depth1 depth1Data = em.find(Depth1.class, depth1); //depth1 에 해당하는 데이터를 불러옴(depth1은 1건이므로 1건 로드)

        if(depth1Data != null) {
            List<Product> productList = depth1Data.getProductList(); //depth1Data에 해당하는 상품 리스트 조회
            List<ProductDto> productDtoList = new ArrayList<>(); //ProductDto에 담기 위한 변수 선언
            for (Product product : productList) { //depth1Data에 해당하는 상품 리스트를 가져와서 ProductDto List 생성
                ProductDto productDto = ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .summary(product.getSummary())
                        .thumb(product.getThumb())
                        .build();
                productDtoList.add(productDto);
            }

            return ProductRespDto.builder() //depth1Data가 null 이 아니면 true 반환
                    .result(true)
                    .productDto(productDtoList)
                    .responseMsg(depth1+depth1Data.getName()+" 카테고리 조회에 성공했습니다. 총 개수: "+productList.size())
                    .build();
        } else {
            return ProductRespDto.builder() //depth1Data가 null 이면 false 반환
                    .result(false)
                    .responseMsg(depth1+" 카테고리 조회 결과가 없습니다.")
                    .build();
        }
    }

    public ProductRespDto findAllDepth2(String depth1, String depth2){
        Depth2 depth2Data = em.createQuery("select d from Depth2 d where d.depth1.id=:depth1", Depth2.class)
                .setParameter("depth1", depth1)
                .getSingleResult();

        if(depth2Data != null){
            List<Product> productList = depth2Data.getProductList();
            List<ProductDto> productDtoList = new ArrayList<>();

            for(Product product:productList){
                ProductDto productDto = ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .summary(product.getSummary())
                        .thumb(product.getThumb())
                        .build();
                productDtoList.add(productDto);
            }

            return ProductRespDto.builder()
                    .result(true)
                    .productDto(productDtoList)
                    .responseMsg(depth2+depth2Data.getName()+" 카테고리 조회에 성공했습니다. 총 개수: "+productList.size())
                    .build();
        } else {
            return ProductRespDto.builder()
                    .result(true)
                    .responseMsg(depth2+" 카테고리 조회에 실패했습니다.")
                    .build();
        }
    }
}