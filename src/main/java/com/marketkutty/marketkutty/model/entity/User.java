package com.marketkutty.marketkutty.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.marketkutty.marketkutty.model.entity.baseEntity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long id;

    @OneToMany (mappedBy = "user")
    @JsonManagedReference(value = "address-user-fk")
    private List<Address> addressList;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String nickname;

    @NotNull
    private String email;

    @NotNull
    private int phone;

    @Builder
    public User(Long id, String username, String password, String nickname, String email, int phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
    }
}
