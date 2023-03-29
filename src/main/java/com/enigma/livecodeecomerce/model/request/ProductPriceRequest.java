package com.enigma.livecodeecomerce.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter @Setter
@Accessors(chain = true)
public class ProductPriceRequest {
    private String productId;
    private Integer price;
}
