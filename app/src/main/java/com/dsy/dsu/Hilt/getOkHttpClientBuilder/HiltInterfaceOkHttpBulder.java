package com.dsy.dsu.Hilt.getOkHttpClientBuilder;


import com.fasterxml.jackson.databind.ObjectMapper;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfaceOkHttpBulder {


    OkHttpClient.Builder  getHiltOkHttpBulderInterface( );
}


