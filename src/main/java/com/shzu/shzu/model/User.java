package com.shzu.shzu.model;

public class User {
    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_other;
    private Integer user_role;
    private Integer user_office;

    private Integer user_permit;
    private String user_academy="";

    private String of_name;
    private Integer of_id;


    public Integer getUser_office() {
        return user_office;
    }

    public void setUser_office(Integer user_office) {
        this.user_office = user_office;
    }

    public String getOf_name() {
        return of_name;
    }

    public void setOf_name(String of_name) {
        this.of_name = of_name;
    }

    public String getUser_academy() {
        return user_academy;
    }

    public Integer getOf_id() {
        return of_id;
    }

    public void setOf_id(Integer of_id) {
        this.of_id = of_id;
    }

    public void setUser_academy(String user_academy) {
        this.user_academy = user_academy;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_role=" + user_role +
                ", user_permit=" + user_permit +
                '}';
    }

    public String getUser_other() {
        return user_other;
    }

    public void setUser_other(String user_other) {
        this.user_other = user_other;
    }

    public Integer getUser_permit() {
        return user_permit;
    }

    public void setUser_permit(Integer user_permit) {
        this.user_permit = user_permit;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getUser_role() {
        return user_role;
    }

    public void setUser_role(Integer user_role) {
        this.user_role = user_role;
    }

}
