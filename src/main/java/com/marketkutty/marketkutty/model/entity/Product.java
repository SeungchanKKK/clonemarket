package com.marketkutty.marketkutty.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.marketkutty.marketkutty.model.entity.baseEntity.BaseEntity;
import com.marketkutty.marketkutty.model.entity.category.Depth1;
import com.marketkutty.marketkutty.model.entity.category.Depth2;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRODUCT_ID")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int price;

    @NotNull
    private String summary;

    @NotNull
    private String thumb;

    @NotNull
    @ManyToOne
    @JoinColumn(name="DEPTH1_ID")
    @JsonBackReference(value = "product-depth1-fk")
    private Depth1 depth1;

    @ManyToOne
    @JoinColumn(name="DEPTH2_ID")
    @JsonBackReference(value = "product-depth2-fk")
    private Depth2 depth2;
}
