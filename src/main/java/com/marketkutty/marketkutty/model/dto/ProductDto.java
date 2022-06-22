package com.marketkutty.marketkutty.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private int price;
    private String summary;
    private String thumb;
    private String packageType;

    @Builder
    public ProductDto(Long id, String name, int price, String summary, String thumb, String packageType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.summary = summary;
        this.thumb = thumb;
        this.packageType = packageType;
    }
}
