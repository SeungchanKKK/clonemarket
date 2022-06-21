package com.marketkutty.marketkutty.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {

    //카카오 우편번호 서비스 사용
    //https://postcode.map.daum.net/guide#attributes

    private long id;
    private String address; //서울시 성북구 성북로1길 1
    private String addressDetail; //100동 100호
    private String zonecode; //03420
    private Boolean defaultAddress; // 기본배송지여부: true

    @Builder
    public AddressDto(long id, String address, String addressDetail, String zonecode, Boolean defaultAddress) {
        this.id = id;
        this.address = address;
        this.addressDetail = addressDetail;
        this.zonecode = zonecode;
        this.defaultAddress = defaultAddress;
    }
}
