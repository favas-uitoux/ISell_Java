package com.project.isell_java.database.daos;


import androidx.room.Dao;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.isell_java.database.entities.ChartcodeEntity;

import java.util.List;


@Dao
public interface ChartcodeEntityDao {

    @Query(" Select count(*)  from chartcode where type='series_no' ")
    public int get_count_of_stored_series_no();

    @Query(" Select count(*)  from chartcode where type='token' ")
    public int get_count_of_stored_token();


    @Query(" Select val1 from chartcode where type='token' ")
    public String get_stored_token();

    @Query(" Select * from chartcode  where type='series_no' ")
    public List<ChartcodeEntity> get_series_details();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public Long insert_chartcode_item(ChartcodeEntity tbl);


    @Query(" Delete from chartcode where type='token' ")
    public int del_token();

    @Query(" Delete from chartcode where type='series_no' ")
    public int del_series();


}
