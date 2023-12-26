package com.dsy.dsu.CommitingPrices.Model.startingViewModels;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.dsy.dsu.CommitingPrices.ViewModel.ModelComminingPrisesString;
import com.dsy.dsu.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

// TODO: 25.12.2023  бизнес логика
public class StartingLiveDataJsonString {
   private ModelComminingPrisesString modelComminingPrisesString;
  private   Context context;
    @SuppressLint("SuspiciousIndentation")
    public StartingLiveDataJsonString(@NotNull ModelComminingPrisesString modelComminingPrisesString, @NotNull Context context) {
        try{
        this.modelComminingPrisesString = modelComminingPrisesString;
        this.context = context;
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
    }

    public void getLiveDataCallBacks(@NotNull LifecycleOwner lifecycleOwner){
        try{
        LiveData<Bundle> liveData1 = modelComminingPrisesString.livedatastartGetJsonSting();
        liveData1.observe((LifecycleOwner) lifecycleOwner, new Observer<Bundle>() {
            @Override
            public void onChanged(Bundle bundle) {
                // TODO: 25.12.2023
                Bundle validadress=   Objects.requireNonNullElse(bundle,new Bundle());
                if(!validadress.isEmpty()) {
                    // TODO: 25.12.2023  пришел ответ в livedata от VieModel
              String   string1сСогласования = validadress.getString("string1сСогласования");

                    Log.d(this.getClass().getName(),"\n"
                            + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " string1сСогласования " + string1сСогласования);

                }
                Log.d(this.getClass().getName(),"\n"
                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        });

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }


}
