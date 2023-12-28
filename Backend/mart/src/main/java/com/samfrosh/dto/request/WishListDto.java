package com.samfrosh.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class WishListDto {

    private Long userid;

    private Long Productid;
}
