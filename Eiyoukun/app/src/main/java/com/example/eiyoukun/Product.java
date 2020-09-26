package com.example.eiyoukun;

import java.io.Serializable;

public class Product implements Serializable {
    private int id ; //ID
    private String name; //ユーザー名
    private String purpose; //目的
    private String sex ; //性別
    private int age ; //年齢
    private double weight ; //体重
    private double height ; //身長
    private String activityLevel ; //活動レベル

    //コンストラクタ
    public Product() {}

    public Product(int id, String name,String purpose, String sex, int age, double weight, double height, String activityLevel){
        this.id = id;
        this.name = name;
        this.purpose = purpose;
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.activityLevel = activityLevel;
    }

    //セッタ&ゲッタ 引数をメンバ変数に設定、メンバ変数を返す
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

    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public String getActivityLevel() {
        return activityLevel;
    }
    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }
}
