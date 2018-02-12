package com.shzu.shzu.model;

public class Result {
    private String academy_name;
    private Double score_self =0.0;
    private Double score_expert =0.0;
    private Double score_depart =0.0;

    public String getAcademy_name() {
        return academy_name;
    }

    public void setAcademy_name(String academy_name) {
        this.academy_name = academy_name;
    }

    public Double getScore_self() {
        return score_self;
    }

    public void setScore_self(Double score_self) {
        this.score_self = score_self;
    }

    public Double getScore_expert() {
        return score_expert;
    }

    public void setScore_expert(Double score_expert) {
        this.score_expert = score_expert;
    }

    public Double getScore_depart() {
        return score_depart;
    }

    public void setScore_depart(Double score_depart) {
        this.score_depart = score_depart;
    }
}
