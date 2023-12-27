package com.dsy.dsu.Hilt.getSSLSocketFactory2.Businesslogic;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class GetssFactory1 extends  BissennsLogica {
    @Override
    public SSLSocketFactory getSocketFactorySSL(@NonNull Context context) {
        SSLSocketFactory sslSocketFactory2 = null;
        try {
            caFileInputStream = context.getResources().openRawResource(R.raw.certificate_bks_31);


            SSLContext sslContext = SSLContext.getInstance("SSL");

                KeyStore ks = KeyStore.getInstance("BKS");
               ks.load(caFileInputStream, "secret".toCharArray());
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                tmf.init(ks);
                KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                kmf.init(ks,  "secret".toCharArray());




            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());

            sslSocketFactory2 = sslContext.getSocketFactory();

            caFileInputStream.close();














/*            try {

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
                        return  getAcceptedIssuers();
                    }
                } };


                // Get the file of our certificate
                caFileInputStream = context.getResources().openRawResource(R.raw.certificate_bks_31);
                // We're going to put our certificates in a Keystore


     *//*       KeyStore keyStore = KeyStore.getInstance("BKS" ,"BC");
            keyStore.load(caFileInputStream, "secret".toCharArray());


            Certificate cert = (Certificate) keyStore.getCertificate("localhost");
            System.out.println(cert.toString());


            keyStore.setCertificateEntry("localhost",cert);
*//*

                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(caFileInputStream,null);


                TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);


*//*
            // Create a KeyManagerFactory with our specific algorithm our our public keys
            // Most of the cases is gonna be "X509"
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
            keyManagerFactory.init(keyStore, "secret".toCharArray());
*//*




                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("TLSv1.2");

                // sslContext.init(keyManagerFactory.getKeyManagers(), trustAllCerts, new SecureRandom());
                sslContext.init(null, trustManagerFactory.getTrustManagers(), null);




                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                    // Create an ssl socket factory with our all-trusting manager
                    sslSocketFactory2 = new GetssFactory2.TLSSocketFactory(sslContext.getSocketFactory());
                } else {
                    // Create an ssl socket factory with our all-trusting manager
                    sslSocketFactory2 = sslContext.getSocketFactory();
                }
                // TODO: 25.12.2023  clear
                caFileInputStream.close();

                Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                        Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " время " +new Date().toLocaleString()+ " sslSocketFactory2 " +sslSocketFactory2);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }*/

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
        return sslSocketFactory2;
    }
}
