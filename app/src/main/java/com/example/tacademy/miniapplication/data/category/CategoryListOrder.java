package com.example.tacademy.miniapplication.data.category;

import lombok.Getter;

/**
 * Created by Tacademy on 2016-05-09.
 */
public enum CategoryListOrder {

    BEST_C("C"), BEST_F("F"), NEW("N"), RECOMMEND("R");

    CategoryListOrder(String orderChar) {
        this.orderChar = orderChar;
    }

    @Getter
    String orderChar;
}
