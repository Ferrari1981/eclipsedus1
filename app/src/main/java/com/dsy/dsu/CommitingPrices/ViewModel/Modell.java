package com.dsy.dsu.CommitingPrices.ViewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.util.Objects;

public class Modell extends  ViewModel {
    // TODO: 25.12.2023
    private   MutableLiveData<Long> data= new MutableLiveData<>();;
    private Context context;

    public Modell(long id,  Context context) {
        this.data = data;
        this.context = context;
    }

    public LiveData<Long> getData() {
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        return data;
    }

    public  void setData(@NotNull Long l){
        // TODO: 25.12.2023 set
      Number d=  Objects.requireNonNullElse(l,0);
        data.setValue(d.longValue());
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }




    @Override
    protected void onCleared() {
        // clean up resources
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
}
