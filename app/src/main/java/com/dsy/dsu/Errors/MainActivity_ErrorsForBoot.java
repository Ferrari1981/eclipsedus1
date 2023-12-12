package com.dsy.dsu.Errors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BootAndAsync.MainActivityBootAndAsync;
import com.dsy.dsu.BusinessLogicAll.Class_Sendiing_Errors;
import com.dsy.dsu.Dashboard.Fragments.DashboardFragmentSettings;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;


//вывод данных на Автивити
public class MainActivity_ErrorsForBoot extends AppCompatActivity  {
    private SQLiteDatabase sqLiteDatabase ;
    private  TextView КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения;

    private  MaterialButton materialButtonОтправка;
    private SharedPreferences preferences;
    private        File file;

    private String fileName = "Sous-Avtodor-ERROR.txt";

    private   String patchFileName="SousAvtoFile";


    private MaterialButton imageViewBackboot;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
                super.onCreate(savedInstanceState);
            setContentView(R.layout.activitymain_errors_boot); ///activitymain_viewlogin  /// fragment_dashboard

            getSupportActionBar().hide(); ///скрывать тул бар

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения = (TextView) findViewById(R.id.textViewDATA);
            sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            materialButtonОтправка = (MaterialButton) findViewById(R.id.materialButtonОтправка);
            preferences=   getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            imageViewBackboot = (MaterialButton) findViewById(R.id.imageViewBackboot);


            materialButtonОтправка.setClickable(false);
            materialButtonОтправка.setFocusable(false);
            методListerBackInError();

            МетодПросмотраОшибокПриложения();

            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
             // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public void setTheme(@Nullable Resources.Theme theme) {
        super.setTheme(theme);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
        if(requestCode == 11){
            Toast.makeText(this, "Отправляем...", Toast.LENGTH_LONG).show();
            // TODO: 22.09.2023  после оптавление ОШИБОК  
           // TODO: 28.06.2023 очищаем таблиц
            МетодУдаланиеОшибок(sqLiteDatabase );
            // TODO: 22.09.2023  exit error fragment
            методвыходизОшибок();
        }
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    // TODO: 28.06.2023 Запись Ошибков
    private void МетодУдаланиеОшибок(
            @NonNull SQLiteDatabase create_database_error) throws ExecutionException, InterruptedException {
        try {
            CompletableFuture.supplyAsync(new Supplier<Object>() {
                @Override
                public Object get() {
                    if (!create_database_error.inTransaction()) {
                        create_database_error.beginTransaction();
                    }
                    create_database_error.execSQL("DELETE FROM errordsu1 ");

                    // TODO: 22.09.2023


// TODO: 17.04.2023
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

                    create_database_error.setTransactionSuccessful();
                    
                    if (create_database_error.inTransaction()) {
                        create_database_error.endTransaction();
                    }
                    return create_database_error;
                }
            }).thenRun(new Runnable() {
                        @Override
                        public void run() {
                            // TODO: 22.09.2023 удалаляем данные
                            методЧистимФайлсОшибкамиErrors();
                            // TODO: 17.04.2023
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        }
                    })
                    .exceptionally(throwable -> {
                        throwable.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                        return  null;
                    })
                    .complete(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void методЧистимФайлсОшибкамиErrors() {
        try    {
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                File.separator+patchFileName +File.separator+ fileName);

            if (file.exists()) {
                BufferedWriter bf = Files.newBufferedWriter(Paths.get(file.getPath()),
                        StandardOpenOption.TRUNCATE_EXISTING);

                bf.flush();
                bf.close();
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );


        } catch (IOException e) {
            e.printStackTrace();
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    private void методListerBackInError() {
            imageViewBackboot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        Intent Интент_BackВозвращаемАктивти = new Intent();
                        Интент_BackВозвращаемАктивти.setClass(getApplicationContext(), MainActivityBootAndAsync.class); // Т
                        Интент_BackВозвращаемАктивти.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity( Интент_BackВозвращаемАктивти);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                            + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }
            });

    }

    private void методвыходизОшибок() {
        try{
        // TODO Запусукаем Фргамент НАстройки  dashbord
        DashboardFragmentSettings dashboardFragmentSettings = DashboardFragmentSettings.newInstance();
        Bundle data=new Bundle();
        dashboardFragmentSettings.setArguments(data);
        fragmentTransaction.remove(dashboardFragmentSettings);
        String fragmentNewImageNameaddToBackStack=   dashboardFragmentSettings.getClass().getName();
        fragmentTransaction.addToBackStack(fragmentNewImageNameaddToBackStack);
        Fragment FragmentУжеЕСтьИлиНЕт=     fragmentManager.findFragmentByTag(fragmentNewImageNameaddToBackStack);
        if (FragmentУжеЕСтьИлиНЕт==null) {
            dashboardFragmentSettings.show(fragmentManager, "DashboardFragmentSettings");
            // TODO: 01.08.2023

        }
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    protected void МетодПросмотраОшибокПриложения() {

            StringBuffer БуерДляОшибок =new StringBuffer();
            String ИнфоТелефон = Build.MANUFACTURER
                    + " " + Build.MODEL + " " + Build.VERSION.RELEASE
                    + " " + Build.VERSION_CODES.class.getFields()[Build.VERSION.SDK_INT].getName();

          file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), File.separator+patchFileName +File.separator+ fileName);
            if (file.isFile()) {
                BufferedReader newBufferedReader = null;
                try {
                    newBufferedReader = Files.newBufferedReader(Paths.get(file.getPath()), StandardCharsets.UTF_16);
                 String    lineErrorsAll=null;
                    while ((lineErrorsAll = newBufferedReader.readLine()) != null) {
                        БуерДляОшибок.append(lineErrorsAll);
                        БуерДляОшибок.append('\n');
                        Log.d(this.getClass().getName(), "line " +lineErrorsAll  );
                    }

                    newBufferedReader.close();
                    // TODO: 21.09.2023

                    if (БуерДляОшибок!=null && БуерДляОшибок.toString().length()>50){
                        materialButtonОтправка.setClickable(true);
                        materialButtonОтправка.setFocusable(true);
                        materialButtonОтправка.setVisibility(View.VISIBLE);


                        методКнопкиОтправлениеError(  БуерДляОшибок,ИнфоТелефон);
                        Log.d(this.getClass().getName(), "БуерДляОшибок "+БуерДляОшибок  );
                        // TODO: 02.08.2023  clear

                    }else {
                        // TODO: 07.10.2023  когжда  гне ощибок 
                        методКогдаНетОшибок(БуерДляОшибок, ИнфоТелефон);
                        Log.d(this.getClass().getName(), "БуерДляОшибок "+БуерДляОшибок  );
                        // TODO: 02.08.2023  clear

                    }
                    Log.d(this.getClass().getName(), "БуерДляОшибок "+БуерДляОшибок  );
                    // TODO: 02.08.2023  clear


                    методФинальйВставки(БуерДляОшибок, ИнфоТелефон);



                } catch (IOException e   ) {
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());

                    методФинальйВставки(БуерДляОшибок, ИнфоТелефон);

                    throw new RuntimeException(e);


                }

            }else {
                методКогдаНетФайла(БуерДляОшибок, ИнфоТелефон);
                // TODO: 07.07.2023  сама вставка ошибок
                КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения.setText(БуерДляОшибок.toString());
            }

            Log.d(this.getClass().getName(),  " date " +new Date().toGMTString().toString() + " preferences " +preferences.getAll());
    }






    private void методФинальйВставки(StringBuffer БуерДляОшибок, String ИнфоТелефон) {
        // TODO: 07.07.2023  сама вставка ошибок
        КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения.setText(БуерДляОшибок.toString());
    }

    // TODO: 07.10.2023 КОГДА НЕТ ОШИБОК
    private void методКогдаНетОшибок(StringBuffer БуерДляОшибок, String ИнфоТелефон) {
        try{
        БуерДляОшибок.append(    "---------------Ошибок Нет.-----------"+"\n"+"\n"+
                "   " + ИнфоТелефон + "  : " + "  Инфо. телефона " + "\n" + "\n" +
                "   " + Build.BRAND.toUpperCase() + "  : " + " Имя " + "\n" + "\n" +
                Build.VERSION.SDK_INT+ "  : " + " API ("+Build.VERSION.RELEASE+ ")"+ "\n" + "\n" +
                "- время : " +new Date().toString()+"-" + "\n"+  "\n"+
                "   " + "-----------------------------------------" + "\n"+  "\n" );

        Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
    private void методКогдаНетФайла(StringBuffer БуерДляОшибок, String ИнфоТелефон) {
        try{
            БуерДляОшибок.append(    "---------------Нет файла ошибок-----------"+"\n"+"\n"+
                    "   " + ИнфоТелефон + "  : " + "  Инфо. телефона " + "\n" + "\n" +
                    "   " + Build.BRAND.toUpperCase() + "  : " + " Имя " + "\n" + "\n" +
                    Build.VERSION.SDK_INT+ "  : " + " API ("+Build.VERSION.RELEASE+ ")"+ "\n" + "\n" +
                    "- время : " +new Date().toString()+"-" + "\n"+  "\n"+
                    "   " + "-----------------------------------------" + "\n"+  "\n" );

            Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    protected void методКнопкиОтправлениеError( @NonNull  StringBuffer БуерДляОшибок, String ИнфоТелефон) {
        try {
                materialButtonОтправка.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO полывоаем ошибки на почту
                        Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26
                            v2.vibrate(50);
                        }
                        // TODO: 06.07.2023  оправлем ощибку на почту 
                        МетодПосылаемОшибкиНапочту(БуерДляОшибок);
                        Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
                    }
                });

            Log.d(this.getClass().getName(), " БуерДляОшибок   " +БуерДляОшибок.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
///////
        }
    }

    protected void МетодПосылаемОшибкиНапочту(@NonNull StringBuffer БуерДляОшибок) {
        try{
            Integer   ПубличноеID  = preferences.getInt("ПубличноеID",0);
                БуерДляОшибок.append("\n")
                        .append(" текущий пользователь : ").append("\n")
                        .append(ПубличноеID).append("\n")
                        .append(" время отправки: ").append("\n")
                        .append(new Date())
                        .append("\n");
            // TODO: 06.07.2023  оправлем ощибки на ПОЧТУ
            // TODO: 06.07.2023  оправлем ощибки на ПРЧТУ
                new Class_Sendiing_Errors(this)
                        .МетодПослываемОшибкиАдминистаторуПо(БуерДляОшибок,this,ПубличноеID,   sqLiteDatabase );


            Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


}//конец public class MainActivity_Recyclerview extends AppCompatActivity {
