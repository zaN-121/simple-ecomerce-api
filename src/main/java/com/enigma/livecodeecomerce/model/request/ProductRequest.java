package com.enigma.livecodeecomerce.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductRequest {
    private String name;
    private List<String> categoryIds = new ArrayList<>();
    private Integer price;

}
