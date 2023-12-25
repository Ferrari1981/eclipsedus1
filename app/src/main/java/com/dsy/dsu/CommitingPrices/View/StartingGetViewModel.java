package com.dsy.dsu.CommitingPrices.View;


import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;

// TODO: 25.12.2023  бизнес логика
public class StartingGetViewModel {
   private com.dsy.dsu.CommitingPrices.ViewModel.Modell commitPricesViewModel;

    public StartingGetViewModel(@NotNull  com.dsy.dsu.CommitingPrices.ViewModel.Modell commitPricesViewModel) {
        this.commitPricesViewModel = commitPricesViewModel;
    }

    void startGetViewModel(@NotNull  LifecycleOwner lifecycleOwner){
        LiveData<Long> liveData1 = commitPricesViewModel.getData();
        liveData1.observe((LifecycleOwner) lifecycleOwner, new Observer<Long>() {
            @Override
            public void onChanged(Long s) {
                Log.d(this.getClass().getName(),"\n"
                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        });



    }


}
