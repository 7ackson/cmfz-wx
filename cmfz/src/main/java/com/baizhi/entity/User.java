package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    private int id;
    private String headPic;
    private String DharmaName;
    private String name;
    private int sex;
    private String province;
    private String city;
    private String sign;
    private String phoneNum;
    private String password;
    private int salt;
    private int status;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date regDate;

    public User() {
    }

    public User(int id, String headPic, String dharmaName, String name, int sex, String province, String city, String sign, String phoneNum, String password, int salt, int status, Date regDate) {
        this.id = id;
        this.headPic = headPic;
        DharmaName = dharmaName;
        this.name = name;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.sign = sign;
        this.phoneNum = phoneNum;
        this.password = password;
        this.salt = salt;
        this.status = status;
        this.regDate = regDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getDharmaName() {
        return DharmaName;
    }

    public void setDharmaName(String dharmaName) {
        DharmaName = dharmaName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", headPic='" + headPic + '\'' +
                ", DharmaName='" + DharmaName + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", sign='" + sign + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", salt=" + salt +
                ", status=" + status +
                ", regDate=" + regDate +
                '}';
    }
}
