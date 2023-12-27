package com.dsy.dsu.CommitingPrices.View;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.dsy.dsu.CommitingPrices.Model.Handler.HandlerCommintPrices;
import com.dsy.dsu.CommitingPrices.Model.startingViewModels.StartingLiveDataJsonByte;
import com.dsy.dsu.CommitingPrices.Model.startingViewModels.StartingLiveDataJsonString;
import com.dsy.dsu.CommitingPrices.ViewModel.ModelComminingPrisesByte;
import com.dsy.dsu.CommitingPrices.ViewModel.ModelComminingPrisesString;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.common.api.Api;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;

@AndroidEntryPoint
public class FragmentCommingPrices extends Fragment {

    @Inject
    ObjectMapper getHiltJaksonObjectMapper;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private LifecycleOwner lifecycleOwner;

    private RecyclerView recycleview_comminingpprices;
    private  BiznesLogicainnerFragment biznesLogicainnerFragment;

    private  MyRecycleViewIsNullAdapters myRecycleViewIsNullAdapters;


    private Animation animationДляСогласования;

    Handler handler;


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
            Bundle data=      getArguments();
            fragmentManager = getActivity(). getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            lifecycleOwner=this;
            animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row);//R.anim.layout_animal_commit


           biznesLogicainnerFragment=      new BiznesLogicainnerFragment();
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
        View viewCommintPrices=null;
        try{
                viewCommintPrices = inflater.inflate(R.layout.activity_main_fragmentcommitprices, container, false);

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
        //return inflater.inflate(R.layout.fragment_comming_prices, container, false);
        return viewCommintPrices;
    }













    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try{
            recycleview_comminingpprices = view.findViewById(R.id.recycleview_comminingpprices);



              // TODO: 27.12.2023  начинаем запуск is null
            biznesLogicainnerFragment.startIsNullRecyreView( );

            biznesLogicainnerFragment.  МетодИнициализацииRecycleViewДляЗадач();

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " recycleview_comminingpprices " +recycleview_comminingpprices);


    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    @Override
    public void onStart() {
        super.onStart();
 try{

     // TODO: 26.12.2023
     biznesLogicainnerFragment.getmodelByte();

        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                " recycleview_comminingpprices " +recycleview_comminingpprices);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    // TODO: 26.12.2023  бизнес логика самого Фрагмента CommintPrices
    public class BiznesLogicainnerFragment{


     // TODO: 26.12.2023 получение данных в виде String
     private void getmodelString() {
         try{
             ModelComminingPrisesString modelComminingPrisesString =((MainActivityCommitingPrices)getActivity()).modelComminingPrisesString;
             // TODO: 25.12.2023  предварительный код  получение данныз от 1с
             // TODO: 25.12.2023  получение данныз от 1с согласования цен String
             StartingLiveDataJsonString startingLiveDataJsonString =new StartingLiveDataJsonString(modelComminingPrisesString,getContext());
             // TODO: 25.12.2023 запуска callback
             startingLiveDataJsonString.getLiveDataCallBacks(getActivity());

             // TODO: 25.12.2023  запускаем получение Данных
             modelComminingPrisesString.livedatastartSetJsonSting("http://192.168.254.218/dds_copy/hs/jsonto1ccena/listofdocuments",getHiltJaksonObjectMapper);

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


     // TODO: 26.12.2023 получение данных в виде String
     public void getmodelByte() {
         try{
             // ModelComminingPrisesString modelComminingPrisesString =((MainActivityCommitingPrices)getActivity()).modelComminingPrisesString;
             ModelComminingPrisesByte modelComminingPrisesByte =((MainActivityCommitingPrices)getActivity()).modelComminingPrisesByte;


             // TODO: 25.12.2023  предварительный код  получение данныз от 1с
             // TODO: 25.12.2023  получение данныз от 1с согласования цен String
             StartingLiveDataJsonByte startingLiveDataJsonByte =new StartingLiveDataJsonByte(modelComminingPrisesByte,getContext(),   biznesLogicainnerFragment);
             // TODO: 25.12.2023 запуска callback
             startingLiveDataJsonByte.getLiveDataCallBacks(getActivity());


             // TODO: 25.12.2023  запускаем получение Данных
             modelComminingPrisesByte.livedatastartSetJsonByte("http://192.168.254.218/dds_copy/hs/jsonto1ccena/listofdocuments",getHiltJaksonObjectMapper);

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


// TODO: 26.12.2023 download one NULL data from 1c  Согласование Цен


        public void startIsNullRecyreView( ) {
            try {
                if (myRecycleViewIsNullAdapters==null) {
                    ArrayList<Boolean> arrayListIsNull1cData = new ArrayList<>();
                    arrayListIsNull1cData.add(true);
                    myRecycleViewIsNullAdapters = new MyRecycleViewIsNullAdapters(arrayListIsNull1cData, getContext(),biznesLogicainnerFragment);
                    myRecycleViewIsNullAdapters.notifyDataSetChanged();
                    recycleview_comminingpprices.setAdapter(myRecycleViewIsNullAdapters);
                    recycleview_comminingpprices.getAdapter().notifyDataSetChanged();
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "myRecycleViewIsNullAdapters  "
                            + myRecycleViewIsNullAdapters);
                }

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"recycleview_comminingpprices  "
                        + recycleview_comminingpprices);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        public void completeIsNullRecyreView() {
            try{
                if (myRecycleViewIsNullAdapters!=null) {
                    ArrayList<Boolean> arrayListIsNull1cData=new ArrayList<>();
                    arrayListIsNull1cData.add(false);
                    myRecycleViewIsNullAdapters.arrayListIsNull1cData=arrayListIsNull1cData;
                    myRecycleViewIsNullAdapters.notifyDataSetChanged();
                    RecyclerView.Adapter recyclerViewAdapter=         recycleview_comminingpprices.getAdapter();
                    recycleview_comminingpprices.swapAdapter(recyclerViewAdapter,true);
                    recycleview_comminingpprices.getAdapter().notifyDataSetChanged();
                }
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " myRecycleViewIsNullAdapters " +myRecycleViewIsNullAdapters);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }








        void МетодИнициализацииRecycleViewДляЗадач() {
            try {

                DividerItemDecoration itemDecoration = new DividerItemDecoration(recycleview_comminingpprices.getContext(), DividerItemDecoration.HORIZONTAL);
                GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{0xfff7f7f7, 0xfff7f7f7});
                drawable.setSize(1,1);
                itemDecoration.setDrawable(drawable);
                recycleview_comminingpprices.addItemDecoration(itemDecoration);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycleview_comminingpprices.setLayoutManager(linearLayoutManager);
                recycleview_comminingpprices.startAnimation(animationДляСогласования);
                recycleview_comminingpprices.requestLayout();
                recycleview_comminingpprices.refreshDrawableState();
                // TODO: 28.02.2022
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }
    }///todo  class BiznesLogicainnerFragment

}