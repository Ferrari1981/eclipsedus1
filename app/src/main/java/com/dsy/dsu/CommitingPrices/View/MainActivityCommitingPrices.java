package com.dsy.dsu.CommitingPrices.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.dsy.dsu.CommitsPayments.Fragment1_List_CommitPay;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Hilt.Jakson.HiltInterfaceJakson;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.datatransport.runtime.dagger.Provides;
import com.google.common.util.concurrent.AtomicDouble;

import javax.inject.Inject;

import dagger.hilt.EntryPoint;
import dagger.hilt.EntryPoints;
import dagger.hilt.InstallIn;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.scopes.ActivityScoped;
@AndroidEntryPoint
public class MainActivityCommitingPrices extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragmentCommitPrices;
    private  Bl bl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main_commiting_prices);
        // TODO: 21.12.2023
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            // TODO: 21.12.2023 inisia
            fragmentManager =  getSupportFragmentManager();

             bl=    new Bl();
            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try{
            bl.startingFragmentProces();
            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }





    // TODO: 21.12.2023 class bicces logic
    class Bl{
        // TODO: 21.12.2023
        protected void startingFragmentProces(){
            try{
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentCommitPrices = new FragmentCommingPrices();
                getSupportActionBar().hide(); ///скрывать тул бар

                Bundle data=new Bundle();
                fragmentCommitPrices.setArguments(data);
                fragmentTransaction.replace(R.id.activity_maincommitgpreces, fragmentCommitPrices).commit();
                fragmentTransaction.show(fragmentCommitPrices);
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

    }





}//todo  end  public class MainActivityCommitingPrices extends AppCompatActivity


