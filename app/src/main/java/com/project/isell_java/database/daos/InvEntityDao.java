package com.project.isell_java.database.daos;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;



import com.project.isell_java.database.entities.InvEntity;

import java.util.List;


@Dao
public interface InvEntityDao {

    @Query(" Select count(*)  from inv")
    public int get_count();


    @Query(" Select * from inv order by id")
    public List<InvEntity> get_all_datas();



    @Query(" Select * from inv  where name Like  :arg0  order by id")
    public List<InvEntity> get_all_similar_datas(String arg0);



    @Query(" Delete from inv")
    public int del_all();
//
//
//    @Query(" Select sum(qty) from cart ")
//    public LiveData<Integer> get_total_qty();
//
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public Long insert_inv_item(InvEntity tbl);





}
