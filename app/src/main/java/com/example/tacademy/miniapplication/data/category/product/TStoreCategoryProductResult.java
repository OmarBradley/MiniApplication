package com.example.tacademy.miniapplication.data.category.product;


import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Tacademy on 2016-05-09.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TStoreCategoryProductResult {
    private Category category;
    private Products products;
}
