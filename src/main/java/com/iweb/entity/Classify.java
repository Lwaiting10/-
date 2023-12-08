package com.iweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Liu Xiong
 * @date 7/12/2023 上午9:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classify {
    private int id;
    private String name;

    public Classify(String name) {
        this.name = name;
    }
}
