package com.marketkutty.marketkutty.repository;

import com.marketkutty.marketkutty.model.dto.ProductDto;
import com.marketkutty.marketkutty.model.dto.responseDto.CategoryRespDto;
import com.marketkutty.marketkutty.model.dto.responseDto.Depth1Dto;
import com.marketkutty.marketkutty.model.dto.responseDto.Depth2Dto;
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

    public Product findProduct(Long productId){
        return em.find(Product.class, productId);
    }

    public CategoryRespDto findCategory(){
        List<Depth1> depth1List = em.createQuery("select d1 from Depth1 d1", Depth1.class)
                .getResultList();

        CategoryRespDto categoryRespDto;
        Depth1Dto depth1Dto;
        Depth2Dto depth2Dto;
        List<Depth1Dto> depth1DtoList = new ArrayList<>();
        List<Depth2Dto> depth2DtoList = new ArrayList<>();

        if(depth1List.size() > 0) {
            for (Depth1 depth1 : depth1List) {
                for (Depth2 depth2 : depth1.getDepth2()) {
                    depth2Dto = Depth2Dto.builder()
                            .id(depth2.getId())
                            .name(depth2.getName())
                            .build();
                    depth2DtoList.add(depth2Dto);
                }
                depth1Dto = Depth1Dto.builder()
                        .id(depth1.getId())
                        .name(depth1.getName())
                        .depth2DtoList(depth2DtoList)
                        .build();
                depth1DtoList.add(depth1Dto);
                //depth2Dtos.clear(); //????????? depth1Dto??? depth2DtoList ??? ???????????????!
                depth2DtoList = new ArrayList<>();
            }

            categoryRespDto = CategoryRespDto.builder()
                    .result(true)
                    .depth1DtoList(depth1DtoList)
                    .responseMsg("???????????? ????????? ??????????????????.")
                    .build();

        } else {
            categoryRespDto = CategoryRespDto.builder()
                    .result(false)
                    .depth1DtoList(null)
                    .responseMsg("????????? ??????????????? ????????????.")
                    .build();
        }
        return categoryRespDto;
    }

    public ProductRespDto findAllDepth1(String depth1){

        Depth1 depth1Data = em.find(Depth1.class, depth1); //depth1 ??? ???????????? ???????????? ?????????(depth1??? 1???????????? 1??? ??????)

        if(depth1Data != null) {
            List<Product> productList = depth1Data.getProductList(); //depth1Data??? ???????????? ?????? ????????? ??????
            List<ProductDto> productDtoList = new ArrayList<>(); //ProductDto??? ?????? ?????? ?????? ??????
            for (Product product : productList) { //depth1Data??? ???????????? ?????? ???????????? ???????????? ProductDto List ??????
                ProductDto productDto= ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .summary(product.getSummary())
                        .thumb(product.getThumb())
                        .packageType(product.getPackageType())
                        .build();
                productDtoList.add(productDto);
            }

            return ProductRespDto.builder() //depth1Data??? null ??? ????????? true ??????
                    .result(true)
                    .productDto(productDtoList)
                    .responseMsg(depth1+depth1Data.getName()+" ???????????? ????????? ??????????????????. ??? ??????: "+productList.size())
                    .build();
        } else {
            return ProductRespDto.builder() //depth1Data??? null ?????? false ??????
                    .result(false)
                    .responseMsg(depth1+" ???????????? ?????? ????????? ????????????.")
                    .build();
        }
    }

    //?????? ??????
    public ProductRespDto findAllDepth2(String depth1, String depth2){
        Depth2 depth2Data = em.createQuery("select d from Depth2 d where d.depth1.id=:depth1 and d.id=:depth2", Depth2.class)
                .setParameter("depth1", depth1)
                .setParameter("depth2", depth2)
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
                        .packageType(product.getPackageType())
                        .build();
                productDtoList.add(productDto);
            }

            return ProductRespDto.builder()
                    .result(true)
                    .productDto(productDtoList)
                    .responseMsg(depth2+depth2Data.getName()+" ???????????? ????????? ??????????????????. ??? ??????: "+productList.size())
                    .build();
        } else {
            return ProductRespDto.builder()
                    .result(false)
                    .productDto(null)
                    .responseMsg(depth2+" ???????????? ????????? ??????????????????.")
                    .build();
        }
    }
}