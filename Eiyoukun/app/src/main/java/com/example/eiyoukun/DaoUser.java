package com.example.eiyoukun;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface DaoUser {
    //Insert query
    @Insert(onConflict = REPLACE)
    void insert(EntityUser entityUser);

    //Delete query
    @Delete
    void delete(EntityUser entityUser);

    //Delete all query
    @Delete
    void reset(List<EntityUser> entityUser);

    //Update query
    @Query("UPDATE EIYOUKIROKU SET DATE = :sDate WHERE uid = :sID")
    void update(int sID,String sDate);

    //Get all data query
    @Query("SELECT * FROM EIYOUKIROKU")
    List<EntityUser> getAll();
}
