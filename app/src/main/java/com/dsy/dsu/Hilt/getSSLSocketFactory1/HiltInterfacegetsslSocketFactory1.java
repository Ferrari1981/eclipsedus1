package com.dsy.dsu.Hilt.getSSLSocketFactory1;


import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfacegetsslSocketFactory1 {


    OkHttpClient.Builder getHiltOkHttpBulderInterface1( );
}


