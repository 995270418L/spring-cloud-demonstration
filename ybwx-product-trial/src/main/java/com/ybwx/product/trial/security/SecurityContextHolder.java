package com.ybwx.product.trial.security;

public class SecurityContextHolder {

    private static ThreadLocal<String> login = new ThreadLocal<>();

    public static void setContext(){
        login.set("has login");
    }

    public static String getContext(){
        return login.get();
    }

}
