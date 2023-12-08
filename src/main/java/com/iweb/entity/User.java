package com.iweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Liu Xiong
 * @date 7/12/2023 上午9:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private byte[] headSculpture;

    public User(String username, String password, String email, byte[] headSculpture) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.headSculpture = headSculpture;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
