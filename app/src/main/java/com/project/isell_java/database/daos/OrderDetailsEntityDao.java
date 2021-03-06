package com.project.isell_java.database.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.isell_java.database.entities.CartEntity;
import com.project.isell_java.database.entities.OrderDetailsEntity;
import com.project.isell_java.pojos.order_not_uploaded.Model;

import java.util.List;


@Dao
public interface OrderDetailsEntityDao {

    @Query(" Select count(*)  from order_details")
    public int get_count();


    @Query(" Select * from order_details order by id")
    public List<OrderDetailsEntity> get_all_datas();


    @Query(" Select ono,sum(total) as tot from order_details group by ono")
    public List<Model> get_summary();






    @Query(" Delete from order_details")
    public int del_all();





    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public Long insert_order_item(OrderDetailsEntity tbl);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllOrders(List<OrderDetailsEntity> tbls);


}
