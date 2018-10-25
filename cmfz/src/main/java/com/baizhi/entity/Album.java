package com.baizhi.entity;

import java.util.Date;
import java.util.List;

public class Album {
    private String id;
    private String name;
    private String coverImg;
    private int count;
    private Double score;
    private String author;
    private String broadCast;
    private String brief;
    private Date publishDate;
    private List<Chapter> children;

    public Album() {
    }

    public Album(String id, String name, String coverImg, int count, Double score, String author, String broadCast, String brief, Date publishDate, List<Chapter> children) {
        this.id = id;
        this.name = name;
        this.coverImg = coverImg;
        this.count = count;
        this.score = score;
        this.author = author;
        this.broadCast = broadCast;
        this.brief = brief;
        this.publishDate = publishDate;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBroadCast() {
        return broadCast;
    }

    public void setBroadCast(String broadCast) {
        this.broadCast = broadCast;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public List<Chapter> getChildren() {
        return children;
    }

    public void setChildren(List<Chapter> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", title='" + name + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", count=" + count +
                ", score=" + score +
                ", author='" + author + '\'' +
                ", broadCast='" + broadCast + '\'' +
                ", brief='" + brief + '\'' +
                ", publishDate=" + publishDate +
                ", children=" + children +
                '}';
    }
}