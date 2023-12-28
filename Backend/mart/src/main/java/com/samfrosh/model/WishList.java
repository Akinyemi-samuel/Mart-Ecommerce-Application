package com.samfrosh.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "wishlist")
public class WishList {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wishlist_seq"
    )
    @SequenceGenerator(
            name = "wishlist_seq",
            sequenceName = "wishlist_seq"
    )
    private Long id;

    private Long UserId;

    @OneToOne()
    @JoinColumn(name = "product_id")
    private Product Product;
}
