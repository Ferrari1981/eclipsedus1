package com.dsy.dsu.CommitingPrices.View;


import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;

// TODO: 25.12.2023  бизнес логика
public class StartingGetJsonSting {
   private com.dsy.dsu.CommitingPrices.ViewModel.Modell commitPricesViewModel;

    public StartingGetJsonSting(@NotNull  com.dsy.dsu.CommitingPrices.ViewModel.Modell commitPricesViewModel) {
        this.commitPricesViewModel = commitPricesViewModel;
    }

    void getCallBacks(@NotNull  LifecycleOwner lifecycleOwner){
        LiveData<Bundle> liveData1 = commitPricesViewModel.livedatastartGetJsonSting();
        liveData1.observe((LifecycleOwner) lifecycleOwner, new Observer<Bundle>() {
            @Override
            public void onChanged(Bundle bundle) {
                Log.d(this.getClass().getName(),"\n"
                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        });



    }


}
