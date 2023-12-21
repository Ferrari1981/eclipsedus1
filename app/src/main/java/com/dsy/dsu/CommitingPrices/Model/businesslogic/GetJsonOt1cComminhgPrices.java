package com.dsy.dsu.CommitingPrices.Model.businesslogic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicAll.Class_Get_Json_1C;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteSource;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetJsonOt1cComminhgPrices extends  GetJsonOt1cComminhgPricesParent {

    @Override
    JsonNode startingJsonOt1cComminhgPrices(@NonNull Context context,@NotNull String adress,@NotNull int PublicId) {
        JsonNode jsonNode1сСогласования=null;
        try{
                    // MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient okHttpClient1cСогласованиеЦен = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request originalRequest = chain.request();
                                    Request.Builder builder = originalRequest.newBuilder()
                                            .header("dsu1user", String.valueOf(PublicId))//TODO old ПубличныйIDДляФрагмента   или 8
                                            .header("Authorization",
                                                    Credentials.basic("dsu1Admin", "dsu1Admin"));
                                    Request newRequest = builder.build();
                                    return chain.proceed(newRequest);
                                }
                            }).connectTimeout(3, TimeUnit.SECONDS)
                            .writeTimeout(2, TimeUnit.MINUTES)
                            .readTimeout(2, TimeUnit.MINUTES)
                            .build();
                    // TODO: 25.10.2022 Диспечер
                    Dispatcher dispatcher=  okHttpClient1cСогласованиеЦен.dispatcher();



            //TODO POST () Генерируем JSON
            byte[] dataforsend1cCommitPay= GenetarJsonOt1cComminhgPrices(ПолученныйНомерДокументаСогласования, ПередаемСтатусСогласования,НомерТекущегоДокумента);

            RequestBody bodyДляОтправки1cСогласования =
                    RequestBody.create(MediaType.parse("application/json; charset=utf-8"),dataforsend1cCommitPay);
            //  RequestBody bodyДляОтправки1cСогласования = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),stringBufferСгенерированыйJSONДляОтпрвкиНа1С.toString());

            Request requestGet1cСогласованииЦен = new Request.Builder()
                    .post(bodyДляОтправки1cСогласования)
                    .url(adress).build();
                    // TODO  Call callGET = client.newCall(requestGET);
                  okHttpClient1cСогласованиеЦен.newCall(requestGet1cСогласованииЦен).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());

                            //TODO
                            dispatcher.executorService().shutdown();
                            //TODO закрываем п отоки
                        }
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            try{
                                if (response.isSuccessful()) {
                                    String  ПришедшегоПотока =    response.header("stream_size");
                                    ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                                    Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                                    Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets"))
                                            .map(String::new).orElse("0"));
                                    Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream"))
                                            .map(String::new).orElse("false"));


                                    byte[] asByteBuffer=    response.body().source().readByteArray();
                                    InputStream     inputStream1c  = ByteSource.wrap(asByteBuffer).openBufferedStream();

                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "     inputStream1c " + inputStream1c);

                                    // TODO: 09.11.2023  close
                                    response.body().source().close();
                                    //TODO
                                    dispatcher.executorService().shutdown();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    });
        /*            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

                    dispatcher.executorService().awaitTermination(1,TimeUnit.DAYS);




                //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С
                InputStream inputStream1cСогласования  =
                        new Class_Get_Json_1C(context,adress)//TODO
                                .МетодПолучемJSONОт1СДляСогласования(ПубличныйIDДляФрагмента,"sog");//
                //TODO БУфер JSON от Сервера
                ObjectMapper jsonGenerator = new PUBLIC_CONTENT(context).getGeneratorJackson();

                if (  inputStream1cСогласования !=null) {
                    if (inputStream1cСогласования.available()>0 ) {
                        jsonNode1сСогласования= jsonGenerator.readTree(inputStream1cСогласования);

                        inputStream1cСогласования.close();
                    }
                }*/

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return null;
    }


    // TODO: 21.12.2023 генерация джейсона
    @Override
    byte[] GenetarJsonOt1cComminhgPrices(@NonNull Context context, @NonNull ArrayList arrayListforgenetaror) {
        try{

        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return new byte[0];
    }
}
