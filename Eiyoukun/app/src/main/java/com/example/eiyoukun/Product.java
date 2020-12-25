package com.example.eiyoukun;

import java.io.Serializable;

public class Product implements Serializable {
    private int id ; //ID
    private String foodName; //食べ物名
    private double foodgram;
    private double calorie ; //カロリー
    private double protain ; //タンパク質
    private double carbon ; //炭水化物
    private double fat ; //脂質

    //コンストラクタ
    public Product() {}

    public Product(int id, String foodName, double foodgram, double calorie, double protain, double carbon, double fat){
        this.id = id;
        this.foodName = foodName;
        this.foodgram = foodgram;
        this.calorie = calorie;
        this.protain = protain;
        this.carbon = carbon;
        this.fat = fat;
    }

    //セッタ&ゲッタ 引数をメンバ変数に設定、メンバ変数を返す
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getfoodName() {
        return foodName;
    }
    public void setfoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getfoodgram() {
        return foodgram;
    }
    public void setfoodgram(double foodgram) {
        this.foodgram = foodgram;
    }

    public double getCalorie() {
        return calorie;
    }
    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public double getProtain() {
        return protain;
    }
    public void setProtain(double protain) {
        this.protain = protain;
    }

    public double getCarbon() {
        return carbon;
    }
    public void setCarbon(double carbon) {
        this.carbon = carbon;
    }

    public double getFat() {
        return fat;
    }
    public void setFat(double fat) {
        this.fat = fat;
    }

}
