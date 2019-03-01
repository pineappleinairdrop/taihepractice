package com.taihe.databasedemo.entity;

import java.sql.Date;

public class Student {
    public Student(){

    }
    public Student(String name,String sex,String registerDate,Double tuition){
        this.name=name;
        this.sex=sex;
        this.tuition=tuition;
        //2019-12-23
        //0   45 78 10
        this.registerDate=new Date(
                Integer.valueOf(registerDate.substring(0,4))-1900,
                Integer.valueOf(registerDate.substring(5,7))-1,
                Integer.valueOf(registerDate.substring(8, 10))
        );

    }
    private Integer id;

    private String name;

    private String sex;

    private Date registerDate;

    private Long loginId;

    private String password;

    private Double tuition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getTuition() {
        return tuition;
    }

    public void setTuition(Double tuition) {
        this.tuition = tuition;
    }
}