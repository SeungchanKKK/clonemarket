package com.marketkutty.marketkutty.model.entity.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.marketkutty.marketkutty.model.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Depth2 {
    @Id
    @Column(name="DEPTH2_ID")
    private String id;

    //private String code; //001

    private String name; //유기농

    @ManyToOne
    @JoinColumn(name = "DEPTH1_ID")
    @JsonBackReference(value = "depth2-depth1-fk")
    private Depth1 depth1;

    @OneToMany(mappedBy = "depth2")
    @JsonManagedReference(value = "product-depth2-fk")
    private List<Product> productList;
}
