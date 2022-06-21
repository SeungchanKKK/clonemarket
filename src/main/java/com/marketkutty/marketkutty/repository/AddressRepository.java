package com.marketkutty.marketkutty.repository;

import com.marketkutty.marketkutty.model.dto.AddressDto;
import com.marketkutty.marketkutty.model.entity.Address;
import com.marketkutty.marketkutty.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressRepository {

    @PersistenceContext
    private EntityManager em;

    public List<AddressDto> findAll(User user) {
        List<Address> addressList = em.createQuery("select a from Address a where a.user.id=:userId", Address.class)
                .setParameter("userId", user.getId())
                .getResultList();

        if(addressList != null) {
            AddressDto addressDto;
            List<AddressDto> responseDto = new ArrayList<>();
            for (Address address : addressList) {
                addressDto = AddressDto.builder()
                        .id(address.getId())
                        .address(address.getAddress())
                        .addressDetail(address.getAddressDetail())
                        .zonecode(address.getZonecode())
                        .defaultAddress(address.getDefaultAddress())
                        .build();
                responseDto.add(addressDto);
            }
            return responseDto;
        } else {
            throw new IllegalArgumentException("해당 유저의 글이 없습니다.");
        }
    }

    @Transactional
    public AddressDto save(User user, AddressDto requestDto) {
        Address address = Address.builder()
                .id(null)
                .address(requestDto.getAddress())
                .addressDetail(requestDto.getAddressDetail())
                .zonecode(requestDto.getZonecode())
                .defaultAddress(requestDto.getDefaultAddress())
                .user(user)
                .build();

        em.persist(address);

        if(em.find(Address.class, address.getId())==null){
            throw new IllegalArgumentException("배송지 저장에 실패했습니다.");
        } else {
            return AddressDto.builder()
                            .id(address.getId())
                            .address(address.getAddress())
                            .addressDetail(address.getAddressDetail())
                            .zonecode(address.getZonecode())
                            .defaultAddress(address.getDefaultAddress())
                            .build();
        }
    }


}
