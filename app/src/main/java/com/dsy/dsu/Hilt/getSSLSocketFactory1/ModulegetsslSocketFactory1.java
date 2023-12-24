
package com.dsy.dsu.Hilt.getSSLSocketFactory1;


import android.content.Context;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.inject.Singleton;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@QualifiergetsslSocketFactory1
@Module
@InstallIn(SingletonComponent.class)
public class ModulegetsslSocketFactory1 {


    @Singleton
    @Provides
    public     SSLSocketFactory getsslSocketFactory1(@ApplicationContext Context context) {
        SSLSocketFactory sslSocketFactory=null;
        try {

                // Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                } };


                // Get the file of our certificate
                InputStream caFileInputStream = context.getResources().openRawResource(R.raw.certificate_bks_31);
                // We're going to put our certificates in a Keystore
                KeyStore keyStore = KeyStore.getInstance("BKS");
                keyStore.load(caFileInputStream, "secret".toCharArray());

                // Create a KeyManagerFactory with our specific algorithm our our public keys
                // Most of the cases is gonna be "X509"
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
                keyManagerFactory.init(keyStore, "secret".toCharArray());



                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(keyManagerFactory.getKeyManagers(), trustAllCerts, new SecureRandom());
                // Create an ssl socket factory with our all-trusting manager
                 sslSocketFactory = sslContext.getSocketFactory();

                caFileInputStream.close();

                Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                        Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " время " +new Date().toLocaleString()+ " sslSocketFactory " +sslSocketFactory );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return sslSocketFactory;


    }
}
