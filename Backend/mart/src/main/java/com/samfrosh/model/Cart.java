package com.samfrosh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "product_total_price")
    private int productTotalPrice;

    @Column(name = "product_total_quantity")
    private int productTotalQuantity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartItems> cartItemsList;

}
