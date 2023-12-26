package com.dsy.dsu.CommitingPrices.Model.SendAndGetData;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CommitingPrices.Model.businesslogic.GeneratorJsonFor1C.DeserializeJsonCommingPrices;
import com.dsy.dsu.CommitingPrices.Model.businesslogic.GeneratorJsonFor1C.GeneratorJsonFor1cCommitingPrices;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.io.ByteSource;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

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
import okio.ByteString;

public class GetJsonOt1cComminhgPrices extends  GetJsonOt1cComminhgPricesParent {

    public GetJsonOt1cComminhgPrices() {
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    }

    @Override
   public InputStream startingGetJsonOt1cComminhgPrices(@NonNull Context context,@NotNull String adress,@NotNull Integer PublicId,
                                                        @NonNull ObjectMapper objectMapper) {
        final InputStream[] inputStreamCommingPrecies = new InputStream[1];
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


// TODO: 22.12.2023 генерируем даным для 1с согласование цен
            LinkedHashMap<String,Long> linkedHashMapОтпавркаНа1с=new LinkedHashMap<>();
            linkedHashMapОтпавркаНа1с.put("dsu1user",PublicId.longValue());
            byte[] dataforsend1cCommitPay=    GenetarJsonGet1cComminhgPrices(context,linkedHashMapОтпавркаНа1с,objectMapper);

            
            RequestBody bodyДляОтправки1cСогласования =
                    RequestBody.create(MediaType.parse("application/octet-stream; charset=utf-8"),dataforsend1cCommitPay);
           
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

                                    inputStreamCommingPrecies[0] = ByteSource.wrap(asByteBuffer).openBufferedStream();

                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                            + "     inputStreamCommingPrecies[0] " + inputStreamCommingPrecies[0]);

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
                 Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

                    dispatcher.executorService().awaitTermination(1,TimeUnit.DAYS);
                    dispatcher.cancelAll();

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
        return inputStreamCommingPrecies[0];
    }

    @Override
    public String startingGetStringOt1cComminhgPrices(@NonNull Context context, @NonNull String adress,
                                                      @NonNull Integer PublicId,
                                                      @NonNull ObjectMapper objectMapper) {
        final String[] stringCommingPrecies = {null};
        try{
            OkHttpClient okHttpClient1cСогласованиеЦен = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("user", String.valueOf(PublicId))//TODO old ПубличныйIDДляФрагмента   или 8
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
            Request requestet1cСогласованииЦен = new Request.Builder().get().url(adress).build();
            // TODO  Call callGET = client.newCall(requestGET);
            okHttpClient1cСогласованиеЦен.newCall(requestet1cСогласованииЦен).enqueue(new Callback() {
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
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                + "   response.isSuccessful() " +  response.isSuccessful());

                     if (response.isSuccessful()) {
                            String  ПришедшегоПотока =    response.header("stream_size");
                            ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                            Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                            Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets"))
                                    .map(String::new).orElse("0"));
                            Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream"))
                                    .map(String::new).orElse("false"));


                        // TODO: 26.12.2023
                        InputStream inputStreamОтПинга =  response.body().source().inputStream();
                        // TODO: 07.10.2023 end
                        BufferedReader РидерОтСервераМетодаGET;//

                            РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(inputStreamОтПинга, StandardCharsets.UTF_8));

                       StringBuffer БуферСамиДанныеОтСервера= РидерОтСервераМетодаGET.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                StringBuffer::append);

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                    + "     stringCommingPrecies[0] " +  stringCommingPrecies[0]);
                     }

                        response.body().source().close();
                        //TODO
                        dispatcher.executorService().shutdown();
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
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

            dispatcher.executorService().awaitTermination(1,TimeUnit.DAYS);
            dispatcher.cancelAll();

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
        return   stringCommingPrecies[0];
    }


    // TODO: 21.12.2023 генерация джейсона
    @Override
    public byte[] GenetarJsonGet1cComminhgPrices(@NonNull Context context,    LinkedHashMap<String,Long> linkedHashMapОтпавркаНа1с,
                                                 @NonNull ObjectMapper objectMapper) {
        byte[] dataforsend1cCommitPay=null;
        try{
            //TODO POST () Генерируем JSON
            // TODO: 10.11.2023 starting Jakson JSON
            StringWriter stringWriterJSONAndroid=    new StringWriter();
            SimpleModule module = new SimpleModule();
            // TODO: 11.09.2023  какая текущап таблица
            Bundle bundle=new Bundle();
            bundle.putSerializable("sercommingrices",(Serializable) linkedHashMapОтпавркаНа1с);
            module.addSerializer(Bundle.class, new GeneratorJsonFor1cCommitingPrices(context));
            objectMapper.registerModule(module);
            objectMapper.getFactory().createGenerator( stringWriterJSONAndroid ).useDefaultPrettyPrinter();

           dataforsend1cCommitPay=    objectMapper.writeValueAsBytes(bundle);
       //  String  dataforsend1cCommitPay2=    jsonGenerator.writeValueAsString(bundle);

            Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() 
                    + " dataforsend1cCommitPay " +dataforsend1cCommitPay);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return dataforsend1cCommitPay;
    }




    @Override
    public Integer DeserializerJson1cComminhgPrices(@NonNull Context context, @NonNull InputStream inputStream,
                                                    @NonNull ObjectMapper objectMapper) {
try{
    SimpleModule module = new SimpleModule();
    module.addDeserializer(Integer.class, new DeserializeJsonCommingPrices(context));
    objectMapper.registerModule(module);
    String s="[ {\n" +
            "  \"dsu1user\" : 5\n" +
            "} ]";
    Integer result = objectMapper.readValue(s, new TypeReference<Integer>() {
        @Override
        public Type getType() {
            return super.getType();
        }
    });
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                + " inputStream " +inputStream);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return null;
    }


}
