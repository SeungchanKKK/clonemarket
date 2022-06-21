package com.marketkutty.marketkutty.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ADDRESS_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    @JsonBackReference(value="address-user-fk")
    private User user;

    @NotNull
    private String address;

    @NotNull
    private String addressDetail;

    @NotNull
    private String zonecode;

    @NotNull
    private Boolean defaultAddress;

    @Builder
    public Address(Long id, String address, String addressDetail, String zonecode, Boolean defaultAddress, User user) {
        this.id = id;
        this.address = address;
        this.addressDetail = addressDetail;
        this.zonecode = zonecode;
        this.defaultAddress = defaultAddress;
        this.user = user;
    }
}
