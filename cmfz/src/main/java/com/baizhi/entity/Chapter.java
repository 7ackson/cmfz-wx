package com.baizhi.entity;

import java.util.Date;

public class Chapter {
    private int id;
    private String name;
    private String url;
    private double size;
    private String duration;

    public Chapter() {
    }

    public Chapter(int id, String name, String url, double size, String duration) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.size = size;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", size=" + size +
                ", duration='" + duration + '\'' +
                '}';
    }
}
