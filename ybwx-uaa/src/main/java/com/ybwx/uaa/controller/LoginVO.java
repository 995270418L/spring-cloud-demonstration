package com.ybwx.uaa.controller;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginVO {

    @NotNull(message = "username can't be null")
    private String username;
    @NotNull(message = "password can't be null")
    private String password;
}
