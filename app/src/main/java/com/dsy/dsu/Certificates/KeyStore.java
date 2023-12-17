package com.dsy.dsu.Certificates;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class KeyStore {

    Context context;

    public KeyStore(Context context) {
        this.context = context;
    }

    void startingkeyStore (){
try{

    TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                            Thread.currentThread().getStackTrace()[2].getMethodName()+
                            " время " +new Date().toLocaleString() );
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                            Thread.currentThread().getStackTrace()[2].getMethodName()+
                            " время " +new Date().toLocaleString() );
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                            Thread.currentThread().getStackTrace()[2].getMethodName()+
                            " время " +new Date().toLocaleString() );
                    return new java.security.cert.X509Certificate[]{};
                }
            }
    };


    OkHttpClient.Builder builderokhtttp = new OkHttpClient.Builder();
    SSLContext sslContext = SSLContext.getInstance("SSL");
    sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
    builderokhtttp.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0] );

    builderokhtttp.hostnameVerifier((hostname, session) -> true);

    OkHttpClient okHttpClient = builderokhtttp.build();
    Dispatcher dispatcher= okHttpClient.dispatcher();
    okHttpClient.newCall(new Request.Builder().url("https://expired.badssl.com/").build()).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() );
            dispatcher.executorService().shutdown();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() );
            dispatcher.executorService().shutdown();
        }
    });


    dispatcher.executorService().awaitTermination(1, TimeUnit.MINUTES);

    Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
            Thread.currentThread().getStackTrace()[2].getMethodName()+
            " время " +new Date().toLocaleString() );









// TODO: 15.12.2023 #2


/*  Dispatcher dispatcher= newClient.dispatcher();
    newClient.newCall(new Request.Builder().url("https://expired.badssl.com/").build()).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() );
            dispatcher.executorService().shutdown();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() );
            dispatcher.executorService().shutdown();
        }
    });

    dispatcher.executorService().awaitTermination(1, TimeUnit.MINUTES);

    Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
            Thread.currentThread().getStackTrace()[2].getMethodName()+
            " время " +new Date().toLocaleString() );*/

/*    String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
    TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);



    SSLContext sslContextOne = SSLContext.getInstance("TLSv1.2");
    sslContextOne.init(null, null, new SecureRandom());
   SSLEngine engine= sslContextOne.createSSLEngine();

    AssetManager assManager = context.getAssets();
    InputStream caInput = assManager.open("testCert.pfx");
InputStream fis = new FileInputStream(CertificatServer) *//* insert your file path here *//*;
    BufferedInputStream bis = new BufferedInputStream(fis);
       CertificateFactory cf = CertificateFactory.getInstance("X.509");


    SSLContext sslContext = SSLContext.getInstance("TLSv1.2");//TLS
  //  SSLContext context = SSLContext.getInstance("TLS");

    sslContext.init(null, tmf.getTrustManagers(), null);

    CertificateFactory cf = CertificateFactory.getInstance("X.509");




  String fileName = "server2.keystore.jks";
     String patchFileName="SousAvtoFile";
    File CertificatServer =
            new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    File.separator+patchFileName +File.separator+ fileName);

    InputStream fis = new FileInputStream(CertificatServer) *//* insert your file path here *//*;
    BufferedInputStream bis = new BufferedInputStream(fis);

    while (bis.available() > 0) {
        Certificate cert = cf.generateCertificate(bis);
        trustStore.setCertificateEntry("fiddler"+bis.available(), cert);
    }*/


        Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                Thread.currentThread().getStackTrace()[2].getMethodName()+
                " время " +new Date().toLocaleString() );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }



    java.security.KeyStore getKeyCert(){
        java.security.KeyStore keyStore=null;
     try{
        keyStore= java.security.KeyStore.getInstance(java.security.KeyStore.getDefaultType());

         //get passsword

         char[] password="secret".toCharArray();

         FileInputStream fileInputStreamCert=new FileInputStream("*/server.cer");
         keyStore.load(fileInputStreamCert,password);

        Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                Thread.currentThread().getStackTrace()[2].getMethodName()+
                " время " +new Date().toLocaleString()  + " fileInputStreamCert  " +fileInputStreamCert );

          InputStream   inputStreamCert=    context.getResources().openRawResource(R.raw.server);

         Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                 Thread.currentThread().getStackTrace()[2].getMethodName()+
                 " время " +new Date().toLocaleString()+ " inputStreamCert " +inputStreamCert );

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
return  keyStore;

    }

}



