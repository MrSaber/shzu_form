package com.shzu.shzu.model;

public class Office {
    private Integer of_id;
    private String of_name;
    private Integer user_id;

    private String user_name;
    private String user_password;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getOf_id() {
        return of_id;
    }

    public void setOf_id(Integer of_id) {
        this.of_id = of_id;
    }

    public String getOf_name() {
        return of_name;
    }

    public void setOf_name(String of_name) {
        this.of_name = of_name;
    }
}

