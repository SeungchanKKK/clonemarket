package com.marketkutty.marketkutty.controller;

import com.marketkutty.marketkutty.model.TokenDecode;
import com.marketkutty.marketkutty.model.dto.AddressDto;
import com.marketkutty.marketkutty.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

//    @GetMapping("/api/address")
//    public ResponseEntity<List<AddressDto>> getAddressList(HttpServletRequest httpRequest){
//        TokenDecode decode = (TokenDecode) httpRequest.getAttribute("decode");
//        List<AddressDto> responseDto = addressService.getAddressList(httpRequest);
//        return ResponseEntity.ok()
//                .body(responseDto);
//    }

    @PostMapping("/api/address/create")
    public ResponseEntity<AddressDto> createAddress(HttpServletRequest httpRequest, @RequestBody AddressDto requestDto){
        AddressDto responseDto =  addressService.createAddress(httpRequest, requestDto);
        return ResponseEntity.ok()
                .body(responseDto);
    }

}
