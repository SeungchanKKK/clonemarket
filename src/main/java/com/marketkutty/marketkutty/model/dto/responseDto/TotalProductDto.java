package com.marketkutty.marketkutty.model.dto.responseDto;

import com.marketkutty.marketkutty.model.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TotalProductDto {
    List<Product>cate1;
    List<Product>cate2;
    List<Product>cate3;

    public TotalProductDto(List<Product> cate1, List<Product> cate2, List<Product> cate3) {
        this.cate1 = cate1;
        this.cate2 = cate2;
        this.cate3 = cate3;
    }
}
