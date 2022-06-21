package com.marketkutty.marketkutty.model.entity.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.marketkutty.marketkutty.model.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Depth1 {

    @Id
    @Column(name="DEPTH1_ID")
    private String id;
    //private String code; //001

    private String name; //채소

    @OneToMany(mappedBy = "depth1")
    @JsonManagedReference(value = "depth2-depth1-fk")
    private List<Depth2> depth2;

    @OneToMany(mappedBy = "depth1")
    @JsonManagedReference(value = "product-depth1-fk")
    private List<Product> productList;
}
