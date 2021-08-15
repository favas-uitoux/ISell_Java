package com.project.isell_java.database.daos;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.project.isell_java.database.entities.InvEntity;

import java.util.List;


@Dao
public interface InvEntityDao {

    @Query(" Select count(*)  from inv")
    public int get_count();


    @Query(" Select * from inv order by id")
    public List<InvEntity> get_all_datas();



    @Query(" Delete from inv")
    public int del_all();
//
//
//    @Query(" Select sum(qty) from cart ")
//    public LiveData<Integer> get_total_qty();
//
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public Long insert_inv_item(InvEntity tbl);


//
//
//    @Query(" Select *  from cart where stkid=:stockid")
//    public  CartEntity get_qty_of_stockid(String stockid);
//
//    @Update
//    void update(CartEntity tbl);
//
//
//    @Query(" Update cart set qty=:qty where id=:id")
//    public  int update_qty(long id,int qty);
//
//    @Query(" Delete from cart where  id=:id")
//    public int del_item(long id);
//
//    @Query(" Update cart set rate=:rate ,offer_price=:offer_price,buy_qty=:buy_qty,free_qty=:free_qty,free_percent=:free_percent,offer_end_date=:offer_end_date  where stkid=:stkid")
//    public int update_new_rate_and_offers(String stkid,float rate,float offer_price,int buy_qty,int free_qty,float free_percent,String offer_end_date);
//
//    @Query(" Update cart set qty=:qty  where stkid=:stkid")
//    public int update_avb_qty(String stkid,int qty);


}
