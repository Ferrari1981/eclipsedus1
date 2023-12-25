package com.dsy.dsu.CommitingPrices.ViewModel;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dsy.dsu.CommitingPrices.Model.SendAndGetData.GetJsonOt1cComminhgPrices;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Modell extends  ViewModel {
    // TODO: 25.12.2023
    private   MutableLiveData<Bundle> mutableLiveData= new MutableLiveData<>();
    private Context context;
    long PublicId;
    public Modell(long id,  Context context) {
        try{
        this.context = context;
        this.PublicId = id;
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    public LiveData<Bundle> livedatastartGetJsonSting() {
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


    // TODO: 25.12.2023  полочения данных от 1с в виде String
    public  void livedatastartSetJsonSting(@NotNull String adress,@NotNull Integer PublicId,@NotNull ObjectMapper getHiltJaksonObjectMapper){
        // TODO: 25.12.2023 set
        try{
      String validadress=  Objects.requireNonNullElse(adress,"");
      if(!validadress.isEmpty()){
          // TODO: 25.12.2023  Запускаем получее данных на сервеи 1с  String
          String string1сСогласования=
                  new GetJsonOt1cComminhgPrices().startingGetStringOt1cComminhgPrices(context,
                          adress,PublicId,getHiltJaksonObjectMapper);
          Bundle bundle=new Bundle();
          bundle.putString("string1сСогласования",string1сСогласования);
          mutableLiveData.setValue(bundle);

      }
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
        // clean up resources
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
}
