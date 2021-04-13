package com.fptsoft.aifpthackathon.activity.model;

import java.util.Date;
import java.util.List;

public class Result {
    private String image;
    private double maxPercent;
    private Date date;
    private List<Sick> sicks;

    public Result() {
    }

    public Result(String image, double maxPercent, Date date, List<Sick> sicks) {
        this.image = image;
        this.maxPercent = maxPercent;
        this.date = date;
        this.sicks = sicks;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getMaxPercent() {
        return maxPercent;
    }

    public void setMaxPercent(double maxPercent) {
        this.maxPercent = maxPercent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Sick> getSicks() {
        return sicks;
    }

    public void setSicks(List<Sick> sicks) {
        this.sicks = sicks;
    }
}
