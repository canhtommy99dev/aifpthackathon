package com.fptsoft.aifpthackathon.activity.model;

import java.util.List;

public class Sick {
    private String name;
    private String image;
    private int percent;
    private List<Expression> expressions;
    private String content;

    public Sick() {
    }

    public Sick(String name, String image, int percent, List<Expression> expressions, String content) {
        this.name = name;
        this.image = image;
        this.percent = percent;
        this.expressions = expressions;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
