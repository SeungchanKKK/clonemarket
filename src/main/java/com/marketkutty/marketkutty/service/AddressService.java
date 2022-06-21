package com.marketkutty.marketkutty.service;

import com.marketkutty.marketkutty.model.dto.AddressDto;
import com.marketkutty.marketkutty.model.entity.User;
import com.marketkutty.marketkutty.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<AddressDto> getAddressList(HttpServletRequest httpRequest){
        User user = new User(); // UserRepository로 수정 필요
        if(user != null){
            return addressRepository.findAll(user);
        } else {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
    }

    public AddressDto createAddress(HttpServletRequest httpRequest, AddressDto requestDto) {
        //TokenDecode decode = (TokenDecode) httpRequest.getAttribute("decode");
        User user = new User(); // UserRepository로 수정 필요

        if(user != null){

            if(requestDto.getAddress() == null) throw new IllegalArgumentException("주소를 입력해주세요.");
            if(requestDto.getAddressDetail() == null) throw new IllegalArgumentException("상세 주소를 입력해주세요.");
            if(requestDto.getZonecode() == null) throw new IllegalArgumentException("우편번호를 입력해주세요.");
            if(requestDto.getDefaultAddress() == null) throw new IllegalArgumentException("기본배송지 여부를 선택해주세요.");

            return addressRepository.save(user, requestDto);
        } else {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
    }
}
