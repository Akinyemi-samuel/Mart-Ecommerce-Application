package com.samfrosh.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private Long userId;


    @OneToOne()
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
}
