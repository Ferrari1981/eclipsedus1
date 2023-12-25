package com.dsy.dsu.CommitingPrices.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsy.dsu.CommitingPrices.Model.SendAndGetData.GetJsonOt1cComminhgPrices;
import com.dsy.dsu.CommitingPrices.ViewModel.ModelFactory;
import com.dsy.dsu.CommitingPrices.ViewModel.Modell;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragmentCommingPrices extends Fragment {

    @Inject
    ObjectMapper getHiltJaksonObjectMapper;





    public FragmentCommingPrices() {
        // Required empty public constructor
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{

// TODO: 25.12.2023 код создание Фабрики VieModel
            Modell commitPricesViewModel = new ViewModelProvider(this,  new ModelFactory(5l,getContext())).get(Modell.class );

            // TODO: 25.12.2023  предварительный код  получение данныз от 1с


         /*InputStream inputStream1сСогласования=
                    cComminhgPrices.startingGetJsonOt1cComminhgPrices(getContext(),"http://80.70.108.165:55255/dds_copy/ru",5,getHiltJaksonObjectMapper);*/

       /*     String string1сСогласования=
                    new GetJsonOt1cComminhgPrices().startingGetStringOt1cComminhgPrices(getContext(),"http://192.168.254.218/dds_copy/hs/jsonto1ccena/listofdocuments",18,getHiltJaksonObjectMapper);*/

        /*    InputStream inputStream1сСогласования = getResources().openRawResource(R.raw.dsu1_keys);

        cComminhgPrices.DeserializerJson1cComminhgPrices(getContext(),inputStream1сСогласования,getHiltJaksonObjectMapper);*/





            // TODO: 25.12.2023  получение данныз от 1с согласования цен String
            StartingLiveDataJsonString startingLiveDataJsonString =new StartingLiveDataJsonString(commitPricesViewModel,getContext());
            // TODO: 25.12.2023 запуска callback
            startingLiveDataJsonString.getLiveDataCallBacks(this);


            // TODO: 25.12.2023  запускаем получение Данных
            commitPricesViewModel.livedatastartSetJsonSting("http://192.168.254.218/dds_copy/hs/jsonto1ccena/listofdocuments",5,getHiltJaksonObjectMapper);




            Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try{

        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comming_prices, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try{
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
}