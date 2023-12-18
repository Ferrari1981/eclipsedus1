package com.dsy.dsu.Dashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

import com.dsy.dsu.AllTestsBusinessLogic.AllTestsBusinessLogic;
import com.dsy.dsu.BusinessLogicAll.Permissions.ClassPermissions;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.Dashboard.Fragments.DashboardFragmentMaterialDesign;
import com.dsy.dsu.R;
import com.dsy.dsu.Websocet.WebSocet2;
import com.dsy.dsu.Websocet.WebSockets1;

import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


/////////////////////////////////////////////////////////////////////////
@AndroidEntryPoint
public class MainActivity_Dashboard extends AppCompatActivity {


    @Inject
    SQLiteDatabase sqlite;



    private   Activity activity;
    private ScrollView scrollview_dashboard;
    //private  NavigationView navigator_dashboard;

    private  BuniccessLogicaActivityDashboard buniccessLogicaActivityDashboard;

    private  Handler handlerAsync;

    public static final int CAMERA_PERSSION_CODE=1;
    public static final int ALL_PERSSION_CODE=1;
    private ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private  LifecycleOwner lifecycleOwner;

    private SharedPreferences preferences;

    private ServiceConnection   connectionОбновлениеПО;

    // TODO: 03.11.2022 FaceApp
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_dashboard);

            scrollview_dashboard = (ScrollView) findViewById(R.id.scrollview_dashboard); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА

            activity = this;
            lifecycleOwner=this;
            getSupportActionBar().hide();
            fragmentManager =  getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            preferences=   getApplicationContext() .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);

            // TODO: 29.09.2023 Статус Повтороной Синхрониазции
            методЗаписываемПовторныйЭтапСинхрогниазции( );


            // TODO: 15.08.2023 Начинается Пользовательский КОд
            buniccessLogicaActivityDashboard=new BuniccessLogicaActivityDashboard();


            методПолучениеДанныхBinder();

            МетодБиндингаОбновлениеПО();


            buniccessLogicaActivityDashboard.     МетодИнициализацияHandler();


            // TODO: 04.10.2023 разрешения для всего
            new ClassPermissions(this,ALL_PERSSION_CODE,CAMERA_PERSSION_CODE);

            // TODO: 15.11.2023  ТЕСТ КОД
        ///  buniccessLogicaActivityDashboard.      testingCode();





            // TODO: 28.09.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try{
            if (connectionОбновлениеПО!=null) {
                unbindService(connectionОбновлениеПО);
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
    }
    }


    private void методЗаписываемПовторныйЭтапСинхрогниазции( ) {
        try {
            // TODO: 02.08.2023 БИЗНЕС КОД
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("РежимЗапускаСинхронизации", "ПовторныйЗапускСинхронизации");
            editor.apply();
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    @SuppressLint("SuspiciousIndentation")
    private void методПолучениеДанныхBinder() {
        try{
            Bundle bundleBinderUpdate=      getIntent().getExtras();
            if (bundleBinderUpdate!=null) {
                localBinderОбновлениеПО= ( ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО)  bundleBinderUpdate.getBinder("callbackbinderdashbord");
                // TODO: 28.09.2023
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "localBinderОбновлениеПО " +localBinderОбновлениеПО  );
                // TODO: 28.09.2023
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
    }

    }

    private void metodSetListerBinder(    ) {
        Bundle bundleBinderПрихолОтAsync=new Bundle();
        bundleBinderПрихолОтAsync.putBinder("callbackbinderdashbord", localBinderОбновлениеПО);
        fragmentManager.setFragmentResult("callbackbinderdashbord" , bundleBinderПрихолОтAsync);
    }


    // TODO: 03.10.2023  метод когда не биндинга
    private void МетодБиндингаОбновлениеПО(         ) {
        try {
            Boolean asBoolenОбновлениеПО = null;
            if (localBinderОбновлениеПО==null) {
                connectionОбновлениеПО = new ServiceConnection() {
                       @Override
                       public void onServiceConnected(ComponentName name, IBinder service) {
                           try {
                               if (service.isBinderAlive()) {
                                   // TODO: 28.07.2023  Update
                                   localBinderОбновлениеПО = (ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО) service;

                                   metodSetListerBinder( );
                               }

                               Log.d(getApplicationContext().getClass().getName(), "\n"
                                       + " время: " + new Date() + "\n+" +
                                       " Класс в процессе... " + this.getClass().getName() + "\n" +
                                       " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                                       + "localBinderОбновлениеПО " + localBinderОбновлениеПО);

                           } catch (Exception e) {
                               e.printStackTrace();
                               Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                       " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                               new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                       this.getClass().getName(),
                                       Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                           }
                       }


                       @Override
                       public void onServiceDisconnected(ComponentName name) {
                           try {
                               localBinderОбновлениеПО = null;
                               Log.i(getApplicationContext().getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()");
                           } catch (Exception e) {
                               e.printStackTrace();
                               Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                       " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                               new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                       this.getClass().getName(),
                                       Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                           }
                       }
                   };
                Intent intentЗапускСлужбыОбновлениеПО = new Intent(getApplicationContext(), ServiceUpdatePoОбновлениеПО.class);
                intentЗапускСлужбыОбновлениеПО.setAction("com.ServiceUpdatePoОбновлениеПО");
                asBoolenОбновлениеПО = bindService(intentЗапускСлужбыОбновлениеПО, connectionОбновлениеПО, Context.BIND_AUTO_CREATE);
            }

            // TODO: 28.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " asBoolenОбновлениеПО " + asBoolenОбновлениеПО);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        try {
            buniccessLogicaActivityDashboard.     методStartingDashboardFragment();
            buniccessLogicaActivityDashboard.    методСлушательФрагментов(  );





                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    @Override
    public void onBackPressed() {
        try{
            int count = getSupportFragmentManager().getBackStackEntryCount();
   /*         int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 0) {
                super.onBackPressed();
                //additional code
            } else {
                getSupportFragmentManager().popBackStack();
            }
*/

             finishAndRemoveTask();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }
    }

























    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard

    public class BuniccessLogicaActivityDashboard {






        void методStartingDashboardFragment() {
            try {
                // TODO Запусукаем Фргамент DdshBoard
                DashboardFragmentMaterialDesign dashboardFragmentHarmonyOS = DashboardFragmentMaterialDesign.newInstance();
                Bundle data = new Bundle();
                data.putBinder("callbackbinderdashbord", localBinderОбновлениеПО);
                dashboardFragmentHarmonyOS.setArguments(data);
                fragmentTransaction.remove(dashboardFragmentHarmonyOS);
                String fragmentNewImageNameaddToBackStack = dashboardFragmentHarmonyOS.getClass().getName();
                fragmentTransaction.addToBackStack(fragmentNewImageNameaddToBackStack)
                        .setPrimaryNavigationFragment(dashboardFragmentHarmonyOS)
                        .setReorderingAllowed(true);
                Fragment FragmentУжеЕСтьИлиНЕт = fragmentManager.findFragmentByTag(fragmentNewImageNameaddToBackStack);
                if (FragmentУжеЕСтьИлиНЕт == null) {
                    dashboardFragmentHarmonyOS.show(fragmentManager, "dashboardFragmentHarmonyOS");
                    // TODO: 01.08.2023
                }
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " FragmentУжеЕСтьИлиНЕт " + FragmentУжеЕСтьИлиНЕт);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }


        private void методСлушательФрагментов() {
            try {
                fragmentManager.setFragmentResultListener("CallBackDashborndFragment", lifecycleOwner, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        if (requestKey.equalsIgnoreCase("CallBackDashborndFragment")) {
                            try {
                                onBackPressed();
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(getApplicationContext().getClass().getName(),
                                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }

                        }
                    }
                });
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        private void МетодИнициализацияHandler() {
try{
            handlerAsync = new Handler(Looper.getMainLooper()) {


                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                }

                @Override
                public void dispatchMessage(@NonNull Message msg) {
                    super.dispatchMessage(msg);

                    Bundle bundleCallsBackAsynsService = msg.getData();


                }
            };

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }



        private void МетодСитемныйНастройкиЭкран() {
            try{
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                //////todo настрока экрана
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                getSupportActionBar().setHomeButtonEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setHomeAsUpIndicator(null);

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                ((Activity) activity) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                ((Activity) activity).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                ((Activity) activity) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                // TODO: 25.03.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


// TODO: 13.09.2023  класс создание ПАПКИ ДЛЯ BINATY Материалорв


// TODO: 15.11.2023 test metod


        void testingCode(){
           // AllTestsBusinessLogic allTestsBusinessLogic=new AllTestsBusinessLogic();       allTestsBusinessLogic.new ClassGetFileProvider().методGetFileProvider();
            try {

              /*  WebSocet2 webSocet2=new WebSocet2(getApplicationContext());

                webSocet2.connectToWebSocket();*/


                WebSockets1 webSockets1=new WebSockets1( getApplicationContext(),5);
                webSockets1.new классИнициализацииКлиентаWebSocerts().методИнициализацииwebsocets();


            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

















//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic
    }//TODO END BUNIVEESS Logic //TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic




}


