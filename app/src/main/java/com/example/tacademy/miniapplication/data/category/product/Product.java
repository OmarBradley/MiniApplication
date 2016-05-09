package com.example.tacademy.miniapplication.data.category.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Tacademy on 2016-05-09.
 */
@Getter
@Setter
public class Product {
    String categoryPath;
    String webUrl;
    int charge;
    int downloadCount;
    String description;
    String thumbnailUrl;
    String name;
    float score;
    String tinyUrl;
    String detailDescription;
    String productId;
}
