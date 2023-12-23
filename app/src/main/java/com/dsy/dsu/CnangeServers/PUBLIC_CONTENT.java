package com.dsy.dsu.CnangeServers;


import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.BusinessLogicAll.SubClassCreatingMainAllTables;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

;


/////--------TODO В ДАННОМ КЛАССЕ СОБРАНЫ ВСЕ СТАТИЧЕСКИЕ ПЕРЕМЕННЫЕ ДЛЯ РАБОТЫ ВСЕГО ПРИЛОЖЕНИЯ DSU-1  ( И БОЛЬШНЕ В КЛАСЕ НИЧЕГО НЕТ )
public  class PUBLIC_CONTENT extends SubClassCreatingMainAllTables {
            //////////
            CompletableFuture completableFutureМенеджер;
                ScheduledFuture scheduledFuture;
                Context context;
                public CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда = new CopyOnWriteArrayList();
    public ArrayList<String> ИменаПроектовОтСервера = new ArrayList<String>(); ////список проектов
    ////ГЛАВНЫЙ СПИСОК ТАБЛИЦ ДЛЯ  ОБМЕНАМИ ДАННЫМИ ИЗ НЕГО БУДЕТ БРАТЬСЯ СПИСКО ТАБЛИЦ
    public LinkedHashMap<String, Long> ВерсииВсехСерверныхТаблиц =  new LinkedHashMap<String, Long>();

    public LinkedHashMap<String, String> ВерсииДатыСерверныхТаблиц =  new LinkedHashMap<>();
           public    CompletionService МенеджерПотоков;
           public    CompletionService МенеджерМногоПотоков;
           public ExecutorService МенеджерПотоковСервис;
    public Handler.Callback callback;
    private   LinkedHashMap<Integer,String> МассивПортовСервера= new LinkedHashMap();
    // TODO: 10.11.2022
    public PUBLIC_CONTENT(Context context) {
        super(context);
        this.context=context;
      //////////todo  ГЛАВНЫЙ МЕНЕДЖЕР ПОТОКОВ ПРОЕКТА
        if (МенеджерПотоков==null) {
              //  МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newFixedThreadPool(1000));
            МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newSingleThreadExecutor());
              // МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newWorkStealingPool());
        }
        if (МенеджерМногоПотоков==null) {
            //  МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newFixedThreadPool(1000));
          //  МенеджерМногоПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newSingleThreadExecutor());
            МенеджерМногоПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newCachedThreadPool());
        }

        if (МенеджерПотоковСервис==null) {
            //  МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newFixedThreadPool(1000));
            //  МенеджерМногоПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newSingleThreadExecutor());
            МенеджерПотоковСервис=  (ExecutorService  )  Executors.newSingleThreadExecutor();
        }
    }



    private   String  СсылкаНаРежимСервера;
    public String getСсылкаНаРежимСервераТабель() {
    //   СсылкаНаРежимСервера="dsu1.glassfish.atomic";//TODO РЕЛИЗ
        //СсылкаНаРежимСервера="jboss-1.0-SNAPSHOT/dsu1.glassfish.atomic";//TODO РЕЛИЗ
        СсылкаНаРежимСервера="jboss-1.0-SNAPSHOT/sous.jboss.tabel";//TODO РЕЛИЗ
        return СсылкаНаРежимСервера.trim();
    }
    private   String  СсылкаНаРежимСервераОбновлениеПО;
    public String getСсылкаНаРежимСервераОбновлениеПО() {
        СсылкаНаРежимСервераОбновлениеПО="jboss-1.0-SNAPSHOT/sous.jboss.download";//TODO РЕЛИЗ
        return СсылкаНаРежимСервераОбновлениеПО.trim();
    }

    private   String  СсылкаНаРежимСервераRuntime;
    public String getСсылкаНаРежимСервераRuntime() {
        //   СсылкаНаРежимСервера="dsu1.glassfish.atomic";//TODO РЕЛИЗ
        //СсылкаНаРежимСервера="jboss-1.0-SNAPSHOT/dsu1.glassfish.atomic";//TODO РЕЛИЗ
        СсылкаНаРежимСервераRuntime="jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss";//TODO РЕЛИЗ
        return СсылкаНаРежимСервераRuntime.trim();
    }



    public LinkedHashMap<Integer,String> getМассивПортовСервера() {


        // TODO: 18.03.2023 дебаг сервер
       // МассивПортовСервера.putIfAbsent(8080,"192.168.254.40");// TODO: 10.11.2022 Debug
        МассивПортовСервера.putIfAbsent(8443,"192.168.254.40");// TODO: 10.11.2022 Debug
        МассивПортовСервера.putIfAbsent(8084,"192.168.254.40");// TODO: 10.11.2022 Debug
        МассивПортовСервера.putIfAbsent(8085,"192.168.254.40");// TODO: 10.11.2022 Debug
        // TODO: 18.03.2023 московский сервер
       // http://185.136.77.98:8888/jboss-1.0-SNAPSHOT/sous.jboss.tabel


   /*     МассивПортовСервера.putIfAbsent(8888,"185.136.77.98");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
        МассивПортовСервера.putIfAbsent(8889,"185.136.77.98");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
        МассивПортовСервера.putIfAbsent(8890,"185.136.77.98");// TODO: 10.11.2022 РЕЛИЗ  Москвовский*/
        //todo//////////20.15
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ МассивПортовСервера.values());

        return МассивПортовСервера;
    }







}
///////-------------------------TODO    КЛАСС ВСТАВКИ ОШИБОК------------------




