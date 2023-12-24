package com.dsy.dsu.Hilt.getSSLSocketFactory2.Businesslogic;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;

import java.security.KeyStore;
import java.security.SecureRandom;
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














         /*   // We're going to put our certificates in a Keystore
            KeyStore keyStore = KeyStore.getInstance("BKS");
            keyStore.load(caFileInputStream, "secret".toCharArray());



            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, tmf.getTrustManagers(), new SecureRandom());

            sslSocketFactory2 = sslContext.getSocketFactory();

            caFileInputStream.close();*/

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
