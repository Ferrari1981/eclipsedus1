package com.dsy.dsu.Hilt.getSSLSocketFactory2.Businesslogic;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class GetssFactory2 extends  BissennsLogica {
    @Override
    public SSLSocketFactory getSocketFactorySSL(@NonNull Context context) {
        SSLSocketFactory sslSocketFactory2 = null;
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
                    return  getAcceptedIssuers();
                }
            } };


            // Get the file of our certificate
           caFileInputStream = context.getResources().openRawResource(R.raw.certificate_bks_31);
            // We're going to put our certificates in a Keystore
            KeyStore keyStore = KeyStore.getInstance("BKS");
            keyStore.load(caFileInputStream, "secret".toCharArray());

            // Create a KeyManagerFactory with our specific algorithm our our public keys
            // Most of the cases is gonna be "X509"
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
            keyManagerFactory.init(keyStore, "secret".toCharArray());


            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustAllCerts, new SecureRandom());




            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                // Create an ssl socket factory with our all-trusting manager
                sslSocketFactory2 = new TLSSocketFactory(sslContext.getSocketFactory());
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
        }
        return sslSocketFactory2;
    }

    private class TLSSocketFactory extends SSLSocketFactory {
        public TLSSocketFactory(SSLSocketFactory socketFactory) {
        }

        @Override
        public String[] getDefaultCipherSuites() {
            return new String[0];
        }

        @Override
        public String[] getSupportedCipherSuites() {
            return new String[0];
        }

        @Override
        public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
            return null;
        }

        @Override
        public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
            return null;
        }

        @Override
        public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
            return null;
        }

        @Override
        public Socket createSocket(InetAddress host, int port) throws IOException {
            return null;
        }

        @Override
        public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
            return null;
        }
    }
}
