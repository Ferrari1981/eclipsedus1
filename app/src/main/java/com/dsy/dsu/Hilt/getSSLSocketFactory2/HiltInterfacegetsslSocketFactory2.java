package com.dsy.dsu.Hilt.getSSLSocketFactory2;


import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfacegetsslSocketFactory2 {


    OkHttpClient.Builder getHiltOkHttpBulderInterface2( );
}


