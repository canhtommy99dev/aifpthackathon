package com.fptsoft.aifpthackathon.activity.model;

public class Expression {
    private int id;
    private String express;

    public Expression() {
    }

    public Expression(int id, String express) {
        this.id = id;
        this.express = express;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }
}
