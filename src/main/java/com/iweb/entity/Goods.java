package com.iweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Liu Xiong
 * @date 7/12/2023 上午9:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    private int id;
    private int cid;
    private String name;
    private BigDecimal price;
    private int stock;
    private String info;

    public Goods(int cid, String name, BigDecimal price, int stock, String info) {
        this.cid = cid;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.info = info;
    }

    public Goods(int cid, String name) {
        this.cid = cid;
        this.name = name;
    }
}
