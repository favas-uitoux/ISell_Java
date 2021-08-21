package com.project.isell_java.database.appdb;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project.isell_java.Constants;
import com.project.isell_java.database.daos.CartEntityDao;
import com.project.isell_java.database.daos.InvEntityDao;
import com.project.isell_java.database.entities.CartEntity;
import com.project.isell_java.database.entities.InvEntity;


@Database(version = 3,entities = {InvEntity.class, CartEntity.class})
public abstract  class Appdb extends RoomDatabase {


  private static Appdb db;

  public abstract InvEntityDao getInvEntityDao();
  public abstract CartEntityDao getCartEntityDao();



  public static synchronized Appdb getDb_instance(Context context)
  {

    if(db==null)
    {
      db = Room.databaseBuilder(context.getApplicationContext(),
              Appdb.class, Constants.Database_Name)
              .fallbackToDestructiveMigration()
              .allowMainThreadQueries()
              .build();



    }


    return db;

  }



}
