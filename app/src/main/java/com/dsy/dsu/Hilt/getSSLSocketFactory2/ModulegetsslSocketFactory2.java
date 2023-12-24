
package com.dsy.dsu.Hilt.getSSLSocketFactory2;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Hilt.getSSLSocketFactory2.Businesslogic.BissennsLogica;
import com.dsy.dsu.Hilt.getSSLSocketFactory2.Businesslogic.GetssFactory1;
import com.dsy.dsu.Hilt.getSSLSocketFactory2.Businesslogic.GetssFactory2;
import com.dsy.dsu.R;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Date;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@QualifiergetsslSocketFactory2
@Module
@InstallIn(SingletonComponent.class)
public class ModulegetsslSocketFactory2 {
    @Singleton
    @Provides
    public SSLSocketFactory getsslSocketFactory2(@ApplicationContext Context context) {
        SSLSocketFactory sslSocketFactory2=null;
       try{
         //sslSocketFactory2 = new GetssFactory1().getSocketFactorySSL(context);
         sslSocketFactory2 = new GetssFactory2().getSocketFactorySSL(context);


        Log.i(this.getClass().getName(), " Атоманически установкаОбновление ПО " +
                Thread.currentThread().getStackTrace()[2].getMethodName() +
                " время " + new Date().toLocaleString() + " sslSocketFactory2 " + sslSocketFactory2);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        Log.i(this.getClass().getName(), " Атоманически установкаОбновление ПО " +
                Thread.currentThread().getStackTrace()[2].getMethodName() +
                " время " + new Date().toLocaleString() + " sslSocketFactory2 " + sslSocketFactory2);

        return sslSocketFactory2;

    }

}

