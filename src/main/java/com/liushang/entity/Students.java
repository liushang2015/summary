package com.liushang.entity;

public class Students {
    private String name;
    private Integer credit;
    private Integer age;

    public Students(String name, Integer credit, Integer age) {
        this.name = name;
        this.credit = credit;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public void show() {
    }
}
