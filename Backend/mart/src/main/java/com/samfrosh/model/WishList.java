package com.samfrosh.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

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


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"productStatus", "category", "wishList"})
    private Product product;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WishList wishList)) return false;
        return Objects.equals(getId(), wishList.getId()) && Objects.equals(getUserId(), wishList.getUserId()) && Objects.equals(getProduct(), wishList.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getProduct());
    }
}
