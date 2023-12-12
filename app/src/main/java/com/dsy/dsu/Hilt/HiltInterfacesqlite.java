package com.dsy.dsu.Hilt;


import android.database.sqlite.SQLiteDatabase;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@QualifiertEnd
@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfacesqlite {


    SQLiteDatabase metodHiltSQl( );
}


