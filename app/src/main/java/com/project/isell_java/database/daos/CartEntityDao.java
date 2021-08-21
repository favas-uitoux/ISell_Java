package com.project.isell_java.database.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.isell_java.database.entities.CartEntity;
import com.project.isell_java.database.entities.InvEntity;


import java.util.List;


@Dao
public interface CartEntityDao {

    @Query(" Select count(*)  from cart")
    public int get_count();


    @Query(" Select * from cart order by id")
    public List<CartEntity> get_all_datas();


    @Query(" Select count(*) from cart  where item_id=:uid  ")
    public int get_count_of_item(String uid);

    @Query(" Select qty from cart  where item_id=:uid  ")
    public double get_qty_of_item(String uid);


    @Query(" Delete from cart")
    public int del_all();


    @Query(" Delete from cart where item_id=:uid")
    public int del_one(String uid);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public Long insert_cart_item(CartEntity tbl);



}
