package com.dsy.dsu.CommitingPrices.ViewModel;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dsy.dsu.BusinessLogicAll.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.CommitingPrices.Model.SendAndGetData.GetJsonOt1cComminhgPrices;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ModelComminingPrisesByte  extends ViewModel {

    // TODO: 25.12.2023
    MutableLiveData<Bundle> mutableLiveData= new MutableLiveData<>();
    Context context;
    long PublicId;


    public ModelComminingPrisesByte(long id, Context context) {
        try{
            this.context = context;
            this.PublicId = id;
            Log.d(context.getClass().getName(),"\n"
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
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        try{
            Log.d(context.getClass().getName(),"\n"
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
    }


    public LiveData<Bundle> livedatastartGetJsonByte() {
        try{
            Log.d(context.getClass().getName(),"\n"
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
        return mutableLiveData;
    }




    // TODO: 25.12.2023  полочения данных от 1с в виде Byte
    public  void livedatastartSetJsonByte(@NotNull String adress, @NotNull ObjectMapper getHiltJaksonObjectMapper){
        // TODO: 25.12.2023 set
        try{
            Bundle bundle = new Bundle();
            Completable.fromSupplier(new Supplier<Bundle>() {
                        @Override
                        public Bundle get() throws Throwable {
                            String validadress=  Objects.requireNonNullElse(adress,"");
                            if(!validadress.isEmpty()) {
                                // TODO: 25.12.2023  get PUBLIC id
                                Integer ПубличныйID =
                                        new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);

                                // TODO: 25.12.2023  Запускаем получее данных на сервеи 1с  byte
                                byte[] getbyteComminhgPrices = new GetJsonOt1cComminhgPrices().getByteComminhgPrices(context,
                                        adress, 8, getHiltJaksonObjectMapper);

                                // TODO: 26.12.2023 добавление byte для согласование цен
                                if (getbyteComminhgPrices.length>0) {
                                    // TODO: 26.12.2023
                                    bundle.putByteArray("getbyteComminhgPrices", getbyteComminhgPrices);
                                }else {
                                    // TODO: 26.12.2023
                                    // TODO: 26.12.2023
                                    bundle.putSerializable("getbyteComminhgPrices","нет данных".getBytes());
                                }

                                Log.d(this.getClass().getName(),"\n"
                                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                
                                
                           
                            }
                            Log.d(this.getClass().getName(),"\n"
                                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());


                            return bundle;
                        }
                    }).subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(()->{
                        // TODO: 25.12.2023  полученый результат обнолвяем экран
                        mutableLiveData.setValue(bundle);
                        Log.d(this.getClass().getName(),"\n"
                                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

                    })
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }).onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    }).subscribe();
            
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

















}
