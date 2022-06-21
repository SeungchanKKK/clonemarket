package com.marketkutty.marketkutty.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRODUCT_ID")
    private Long id;

    @OneToOne //단방향. product 리스트에서는 디테일을 볼 필요가 없게 구성함
    @JoinColumn (name = "PRODUCT_ID")
    private Product product;

    @NotNull
    private String amount; //판매단위: 1팩

    @NotNull
    private String weight; //중량: 100g

    @NotNull
    private String origin; //원산지: 국내

    @NotNull
    private String packageType; //포장타입: 냉장

    @NotNull
    private String expired; //유통기한: 30

    @NotNull
    private String notice; //안내사항: - 신선한 채소는 꼭 냉장보관해서 드세요.

    @NotNull
    private String introImage; //상품설명 - 이미지

    @NotNull
    private String introDesc; //상품설명 - 상세설명

    private String viewImage; //상세정보 - 식품정보 이미지

    private String viewTable; //상세정보 - 식품정보 테이블

    @Builder
    public ProductDetail(Long id, String amount, String weight, String origin, String packageType, String expired, String notice, String introImage, String introDesc, String viewImage, String viewTable) {
        this.id = id;
        this.amount = amount;
        this.weight = weight;
        this.origin = origin;
        this.packageType = packageType;
        this.expired = expired;
        this.notice = notice;
        this.introImage = introImage;
        this.introDesc = introDesc;
        this.viewImage = viewImage;
        this.viewTable = viewTable;
    }
}
