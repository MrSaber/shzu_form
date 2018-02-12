package com.shzu.shzu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CePin {
    private Integer cp_id;
    private String cp_name;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date cp_begindate;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date cp_enddate;

    public Integer getCp_id() {
        return cp_id;
    }

    public void setCp_id(Integer cp_id) {
        this.cp_id = cp_id;
    }

    public String getCp_name() {
        return cp_name;
    }

    public void setCp_name(String cp_name) {
        this.cp_name = cp_name;
    }

    public Date getCp_begindate() {
        return cp_begindate;
    }

    public void setCp_begindate(Date cp_begindate) {
        this.cp_begindate = cp_begindate;
    }

    public Date getCp_enddate() {
        return cp_enddate;
    }

    public void setCp_enddate(Date cp_enddate) {
        this.cp_enddate = cp_enddate;
    }

    @Override
    public String toString() {
        return "CePin{" +
                "cp_id=" + cp_id +
                ", cp_name='" + cp_name + '\'' +
                ", cp_begindate=" + cp_begindate +
                ", cp_enddate=" + cp_enddate +
                '}';
    }
}
