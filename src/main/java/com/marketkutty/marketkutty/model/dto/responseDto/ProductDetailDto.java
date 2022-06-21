package com.marketkutty.marketkutty.model.dto.responseDto;

import com.marketkutty.marketkutty.model.entity.ProductDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailDto {
    Long id;
    String name;
    String summary;
    String thumb;
    int price;
    String amount;
    String weight;
    String origin;
    String packageType;
    String expired;
    String notice;
    String introImage;
    String introDesc;
    String viewImage;


    public ProductDetailDto(ProductDetail productDetail) {
        this.id = productDetail.getProduct().getId();
        this.name = productDetail.getProduct().getName();
        this.summary = productDetail.getProduct().getSummary();
        this.thumb = productDetail.getProduct().getThumb();
        this.price = productDetail.getProduct().getPrice();
        this.amount = productDetail.getAmount();
        this.weight = productDetail.getWeight();
        this.origin = productDetail.getOrigin();
        this.packageType = productDetail.getPackageType();
        this.expired = productDetail.getExpired();
        this.notice = productDetail.getNotice();
        this.introImage = productDetail.getIntroImage();
        this.introDesc = productDetail.getIntroDesc();
        this.viewImage = productDetail.getViewImage();
    }
}
