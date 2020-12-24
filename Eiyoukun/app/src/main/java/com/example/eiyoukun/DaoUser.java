package com.example.eiyoukun;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class DaoUser {
    //Insert query
    @Insert(onConflict = REPLACE)
    abstract void insert(EntityUser entityUser);

    //Delete query
    @Delete
    abstract void delete(EntityUser entityUser);

    //Delete all query
    @Delete
    abstract void reset(List<EntityUser> entityUser);
    //Update query
    @Query("UPDATE EIYOUKIROKU SET DATE = :sDate WHERE uid = :sID")
    abstract void update(int sID, String sDate);

    /*-----
    追加
    -----*/
    //Update query
    @Update
    abstract void update(EntityUser... entityUser);

    //Get all data query
    @Query("SELECT * FROM EIYOUKIROKU")
    public abstract List<EntityUser> getAll();

    /*-----
    追加
    -----*/
    @Query("SELECT * FROM EIYOUKIROKU WHERE DATE  BETWEEN :from AND :to")
    public abstract List<EntityUser> getSearchDateAll(String from,String to);

}
