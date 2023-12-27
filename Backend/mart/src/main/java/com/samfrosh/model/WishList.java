package com.samfrosh.model;

import jakarta.persistence.*;

//@Entity
//@Table(name = "wishlist")
public class WishList {

//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "wishlist_seq"
//    )
//    @SequenceGenerator(
//            name = "wishlist_seq",
//            sequenceName = "wishlist_seq"
//    )
    private Long id;

    private Long UserId;

    private Long Product;
}
