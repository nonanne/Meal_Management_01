package com.example.eiyoukun;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Define table name
@Entity(tableName = "EiyouKiroku")
public class EntityUser implements Serializable {
    // Create id column
    @PrimaryKey(autoGenerate = true)
    public int uid;

    // Create text column
    @ColumnInfo(name = "DATE")
    public String DATE;
    @ColumnInfo(name = "WEIGHT")
    public Double WEIGHT;
    @ColumnInfo(name = "PURPOSE")
    public String PURPOSE;
    @ColumnInfo(name = "CALORIE_GOAL")
    public Double CALORIE_GOAL;
    @ColumnInfo(name = "PROTEIN_GOAL")
    public Double PROTEIN_GOAL;
    @ColumnInfo(name = "CARBON_GOAL")
    public Double CARBON_GOAL;
    @ColumnInfo(name = "FAT_GOAL")
    public Double FAT_GOAL;
    @ColumnInfo(name = "CALORIE_NOW")
    public Double CALORIE_NOW;
    @ColumnInfo(name = "PROTEIN_NOW")
    public Double PROTEIN_NOW;
    @ColumnInfo(name = "CARBON_NOW")
    public Double  CARBON_NOW;
    @ColumnInfo(name = "FAT_NOW")
    public Double FAT_NOW;
    @ColumnInfo(name = "CALORIE_COMPARE")
    public String CALORIE_COMPARE;
    @ColumnInfo(name = "PROTEIN_COMPARE")
    public String PROTEIN_COMPARE;
    @ColumnInfo(name = "CARBON_COMPARE")
    public String CARBON_COMPARE;
    @ColumnInfo(name = "FAT_COMPARE")
    public String FAT_COMPARE;

    //Generate getter and setter


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public Double getWEIGHT() {
        return WEIGHT;
    }

    public void setWEIGHT(Double WEIGHT) {
        this.WEIGHT = WEIGHT;
    }

    public String getPURPOSE() {
        return PURPOSE;
    }

    public void setPURPOSE(String PURPOSE) {
        this.PURPOSE = PURPOSE;
    }

    public Double getCALORIE_GOAL() {
        return CALORIE_GOAL;
    }

    public void setCALORIE_GOAL(Double CALORIE_GOAL) {
        this.CALORIE_GOAL = CALORIE_GOAL;
    }

    public Double getPROTEIN_GOAL() {
        return PROTEIN_GOAL;
    }

    public void setPROTEIN_GOAL(Double PROTEIN_GOAL) {
        this.PROTEIN_GOAL = PROTEIN_GOAL;
    }

    public Double getCARBON_GOAL() {
        return CARBON_GOAL;
    }

    public void setCARBON_GOAL(Double CARBON_GOAL) {
        this.CARBON_GOAL = CARBON_GOAL;
    }

    public Double getFAT_GOAL() {
        return FAT_GOAL;
    }

    public void setFAT_GOAL(Double FAT_GOAL) {
        this.FAT_GOAL = FAT_GOAL;
    }

    public Double getCALORIE_NOW() {
        return CALORIE_NOW;
    }

    public void setCALORIE_NOW(Double CALORIE_NOW) {
        this.CALORIE_NOW = CALORIE_NOW;
    }

    public Double getPROTEIN_NOW() {
        return PROTEIN_NOW;
    }

    public void setPROTEIN_NOW(Double PROTEIN_NOW) {
        this.PROTEIN_NOW = PROTEIN_NOW;
    }

    public Double getCARBON_NOW() {
        return CARBON_NOW;
    }

    public void setCARBON_NOW(Double CARBON_NOW) {
        this.CARBON_NOW = CARBON_NOW;
    }

    public Double getFAT_NOW() {
        return FAT_NOW;
    }

    public void setFAT_NOW(Double FAT_NOW) {
        this.FAT_NOW = FAT_NOW;
    }

    public String getCALORIE_COMPARE() {
        return CALORIE_COMPARE;
    }

    public void setCALORIE_COMPARE(String CALORIE_COMPARE) {
        this.CALORIE_COMPARE = CALORIE_COMPARE;
    }

    public String getPROTEIN_COMPARE() {
        return PROTEIN_COMPARE;
    }

    public void setPROTEIN_COMPARE(String PROTEIN_COMPARE) {
        this.PROTEIN_COMPARE = PROTEIN_COMPARE;
    }

    public String getCARBON_COMPARE() {
        return CARBON_COMPARE;
    }

    public void setCARBON_COMPARE(String CARBON_COMPARE) {
        this.CARBON_COMPARE = CARBON_COMPARE;
    }

    public String getFAT_COMPARE() {
        return FAT_COMPARE;
    }

    public void setFAT_COMPARE(String FAT_COMPARE) {
        this.FAT_COMPARE = FAT_COMPARE;
    }
}