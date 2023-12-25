package com.dsy.dsu.CommitingPrices.ViewModel;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Modell extends  ViewModel {
    // TODO: 25.12.2023
    private   MutableLiveData<Bundle> mutableLiveData= new MutableLiveData<>();
    private Context context;
    long PublicId;
    public Modell(long id,  Context context) {
        this.context = context;
        this.PublicId = id;
    }

    public LiveData<Bundle> livedatastartGetJsonSting() {
        Log.d(context.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        return mutableLiveData;
    }

    public  void livedatastartSetJsonSting(@NotNull String adress,@NotNull Integer PublicId,@NotNull ObjectMapper getHiltJaksonObjectMapper){
        // TODO: 25.12.2023 set
      String validadress=  Objects.requireNonNullElse(adress,"");
      if(!validadress.isEmpty()){
          Bundle bundle=new Bundle();
          bundle.putString("adress",adress);
          mutableLiveData.setValue(bundle);

      }
        Log.d(context.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }




    @Override
    protected void onCleared() {
        // clean up resources
        Log.d(context.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
}
