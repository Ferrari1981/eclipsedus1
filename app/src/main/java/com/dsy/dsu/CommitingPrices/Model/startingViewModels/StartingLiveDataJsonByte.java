package com.dsy.dsu.CommitingPrices.Model.startingViewModels;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.dsy.dsu.CommitingPrices.View.FragmentCommingPrices;
import com.dsy.dsu.CommitingPrices.ViewModel.ModelComminingPrisesByte;
import com.dsy.dsu.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

// TODO: 25.12.2023  бизнес логика
public class StartingLiveDataJsonByte {
   private ModelComminingPrisesByte modelComminingPrisesByte;
  private   Context context;
  private   FragmentCommingPrices.BiznesLogicainnerFragment biznesLogicainnerFragment;
    @SuppressLint("SuspiciousIndentation")
    public StartingLiveDataJsonByte(@NotNull  ModelComminingPrisesByte modelComminingPrisesByte ,
                                    @NotNull Context context,
                                    @NotNull FragmentCommingPrices.BiznesLogicainnerFragment biznesLogicainnerFragment) {
        try{
        this.modelComminingPrisesByte = modelComminingPrisesByte;
        this.context = context;
        this.biznesLogicainnerFragment = biznesLogicainnerFragment;
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

   public void getLiveDataCallBacks(@NotNull  LifecycleOwner lifecycleOwner){
        try{
        LiveData<Bundle> liveData1 = modelComminingPrisesByte.livedatastartGetJsonByte();
        liveData1.observe((LifecycleOwner) lifecycleOwner, new Observer<Bundle>() {
            @Override
            public void onChanged(Bundle bundle) {
                // TODO: 25.12.2023
                Bundle validadress=   Objects.requireNonNullElse(bundle,new Bundle());

                    // TODO: 25.12.2023  пришел ответ в livedata от VieModel
                    byte[] getbyteComminhgPrices=  validadress.getByteArray("getbyteComminhgPrices");

                    // TODO: 26.12.2023  когда данные пришли от 1с согласования цен
                    if (getbyteComminhgPrices.length==0){

                        // TODO: 26.12.2023 пришли байты
                        Log.d(this.getClass().getName(),"\n"
                                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " getbyteComminhgPrices " + getbyteComminhgPrices);
                    }else {


                        biznesLogicainnerFragment.completeIsNullRecyreView();

                        biznesLogicainnerFragment.   dontvissiblePrograssBar();

                        // TODO: 26.12.2023 пришли байты
                        Log.d(this.getClass().getName(),"\n"
                                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " getbyteComminhgPrices " + getbyteComminhgPrices);
                    }

                    Log.d(this.getClass().getName(),"\n"
                            + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " getbyteComminhgPrices " + getbyteComminhgPrices);

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
