package com.example.tacademy.miniapplication.data.category.product;

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
public class Category {
    private String categoryName;
    private int totalCount;
    private String categoryCode;
}
