package com.dsy.dsu.CommitsPayments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.BusinessLogicAll.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.BusinessLogicAll.Class_Get_Json_1C;
import com.dsy.dsu.Dashboard.MainActivity_Dashboard;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.dsy.dsu.Services.ServiceForCommitPay;
import com.dsy.dsu.Services.Service_Notificatios_Для_Согласования;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.jakewharton.rxbinding4.view.RxView;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;


public class Fragment1_List_CommitPay extends Fragment   {

    // TODO: 10.03.2022  согласования фрагмент
    private SubClassФрагмент1Согласование BisnesLogica1Согласование;
    private SubClassФрагмент1Согласование.MyRecycleViewAdapter myRecycleViewAdapter;

    private SubClassФрагмент1Согласование.MyRecycleViewIsNullAdapter myRecycleViewIsNullAdapter;
    private SubClassФрагмент1Согласование.MyViewHolder myViewHolder;
    private JsonNode jsonNode1сСогласованияAllRows;
    private FragmentManager fragmentManagerДляЗадачи;
    private FragmentTransaction fragmentTransactionляЗадачи;
    private TextView textViewТекущаяЗадача;
    private Integer ПубличныйidPay;
    private LinearLayout linearLayout_commitpay;
    private Fragment fragment_ТекущийФрагментСогласованиеСписок;
    private BottomNavigationView bottomnavigationActivicommit_search;
    private BottomNavigationItemView bottomNavigationBack;
    private BottomNavigationItemView bottomNavigationAsync;

    private BottomNavigationItemView bottomNavigationSearch;

    private RecyclerView recyclerViewСогласование1С;
    private ProgressBar progressBarCommitPay;


    private  Boolean  РезультатИзмененияСтатусаСогласованияОтказаИлиУспешноеСогласования;

    private Service_Notificatios_Для_Согласования.LocalBinderДляСогласования binderСогласования1C;

    private Boolean ФлагПрошлаХотябыОднаПопыткаПолучитьДанные=false;
   private  Animation animationДляСогласования;
   private AsyncTaskLoader <JsonNode> asyncTaskLoader;


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private LifecycleOwner lifecycleOwner;


    private Animation animation1;


    private  Handler handler;

    private  BinderService1cCommitPay binderService1cCommitPay;

    private   MainActivity_CommitPay   mainActivity_commitPayInterface;
    private     SearchView searchview_commitpay;

    private     RelativeLayout relativeLayout_recyreview;


  private        Intent intentsendJsonNodeToService= null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        Bundle data=      getArguments();

            fragmentManager = getActivity(). getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            lifecycleOwner=this;
            // TODO: 03.10.2023
            ClassBizneLogica classBizneLogica=new ClassBizneLogica();

            classBizneLogica.методСлушательФрагментовBinder();


            metodRebootIntefaceBinder();

            //todo метод  ИНИЦИАЛИЗАЦИИ RECYCLEVIEW ДЛЯ АКТИВТИ ЗАДАЧИ МЕТОДЫ
            BisnesLogica1Согласование = new SubClassФрагмент1Согласование(getContext(), getActivity());


            ПубличныйidPay = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
         /// ПубличныйidPay = 14;
        ///    ПубличныйidPay=96;
            // TODO: 15.08.2023
            Log.d(this.getClass().getName(),"\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///
    }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            recyclerViewСогласование1С = view.findViewById(R.id.recycleviewcommitpay);
            textViewТекущаяЗадача = view.findViewById(R.id.TextView);
            fragmentManagerДляЗадачи = getActivity().getSupportFragmentManager();
            linearLayout_commitpay= view.findViewById(R.id.linearLayout_commitpay);
                searchview_commitpay =(SearchView) view.findViewById(R.id.searchview_commitpay);
                searchview_commitpay.setVisibility(View.INVISIBLE);
                searchview_commitpay.setEnabled(false);
                searchview_commitpay.setIconifiedByDefault(true); //iconify the widget
                searchview_commitpay.setSubmitButtonEnabled(true);
            bottomnavigationActivicommit_search = view.findViewById(R.id.bottomnavigationActivicommit_search);
            bottomnavigationActivicommit_search.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationBack = bottomnavigationActivicommit_search.findViewById(R.id.bottomNavigationBack);
            bottomNavigationBack.setTitle("Выйти");
            bottomNavigationAsync = bottomnavigationActivicommit_search.findViewById(R.id.bottomNavigationAsync);
            bottomNavigationAsync.setTitle("Обновить");
            bottomNavigationSearch = bottomnavigationActivicommit_search.findViewById(R.id.bottomNavigationSearch);
            bottomNavigationSearch.setTitle("Поиск");
            progressBarCommitPay = view.findViewById(R.id.prograessbarcommitpaydown); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            recyclerViewСогласование1С = view.findViewById(R.id.recycleviewcommitpay);
            relativeLayout_recyreview= view.findViewById(R.id.relativeLayout_recyreview);
            bottomnavigationActivicommit_search.getOrCreateBadge(R.id.id_commitasync).setVisible(true);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
             ///animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row);
            // animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row_vibrator1);
             animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row);//R.anim.layout_animal_commit
            animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_tabellist);
           // animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_scrolls);
            BisnesLogica1Согласование.metodStartingRecyreViewElseData1C();
            BisnesLogica1Согласование.МетодИнициализацииRecycleViewДляЗадач();
            BisnesLogica1Согласование. МетодHandlerCallBack();
            BisnesLogica1Согласование.   metodSetNameCommitHeaders();
            BisnesLogica1Согласование.МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(jsonNode1сСогласованияAllRows);
            BisnesLogica1Согласование.МетодКпопкаВозвращениеНазадИзСогласованиии();
            BisnesLogica1Согласование.МетодКпопкаПринидительноОбмена();
            BisnesLogica1Согласование.МетодКликПоПоиску();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///
        }
    }




    // TODO: 12.03.2022

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=null;
        try{
// TODO: 14.03.2022
          /// viewДляПервойКнопкиСогласованиея = inflater.inflate(R.layout.activity_main_fragment1_for_commipay, container, false);
            view = inflater.inflate(R.layout.activity_main_fragment1_for_commipay_search, container, false);
            // TODO: 12.03.2022
            // TODO: 17.04.2023 LOG
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + "\n" + " view " +view);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        try{

            BisnesLogica1Согласование.metodGetDataOt1cCommitPay();

            BisnesLogica1Согласование.МетодСлушательObserverДляRecycleView();

            BisnesLogica1Согласование. МетодПерегрузкаRecyceView();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "ПубличныйidPay " + ПубличныйidPay + "jsonNode1сСогласованияAllRows " + jsonNode1сСогласованияAllRows);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 12.03.2022  метод с бизнес логикой
    @Override
    public void onResume() {
        super.onResume();
        try {


            if (jsonNode1сСогласованияAllRows!=null) {

                BisnesLogica1Согласование.МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(jsonNode1сСогласованияAllRows);

                BisnesLogica1Согласование. МетодПерегрузкаRecyceView();

            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "ПубличныйidPay " + ПубличныйidPay + "jsonNode1сСогласованияAllRows " + jsonNode1сСогласованияAllRows);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    @Override
    public void onStop() {
        super.onStop();
        try{
            BisnesLogica1Согласование.методRebootDisaynRecyreViewonStopOrAsync(jsonNode1сСогласованияAllRows);

            BisnesLogica1Согласование. МетодПерегрузкаRecyceView();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
             mainActivity_commitPayInterface = (MainActivity_CommitPay)context;

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "ПубличныйidPay " + ПубличныйidPay + "binderСогласования1C " + binderСогласования1C);

        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

}

    private void metodRebootIntefaceBinder() {
        try{
        if (mainActivity_commitPayInterface!=null  && binderСогласования1C==null) {
            if (mainActivity_commitPayInterface.bringBackString()!=null) {
                binderСогласования1C=    mainActivity_commitPayInterface.bringBackString();
            }
        }
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                + "ПубличныйidPay " + ПубличныйidPay + "binderСогласования1C " + binderСогласования1C);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }







    // TODO: 10.03.2022 БИЗНЕС-КОД ПЕРЕНЕСЕН ИЗ АКТИВТИ
    private  class SubClassФрагмент1Согласование {
        Context context;
        Activity activity;
        public SubClassФрагмент1Согласование(Context context, Activity activity) {
            this.context = context;
            this.activity = activity;
            Log.d(this.getClass().getName(), "  public SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1(Context context, Activity activity)   " + context);
        }
        void МетодСлушательObserverДляRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
            try {
                if (myRecycleViewAdapter!=null) {
                    myRecycleViewAdapter.registerAdapterDataObserver(      new RecyclerView.AdapterDataObserver() {
                        @Override
                        public void onChanged() {
                            super.onChanged();
                            try {
                                Log.d(this.getClass().getName(), "onChanged ");
                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                        @Override
                        public void onItemRangeChanged(int positionStart, int itemCount) {
                            super.onItemRangeChanged(positionStart, itemCount);
                            // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
                            try {
                                Log.d(this.getClass().getName(), "onItemRangeChanged ");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                        @Override
                        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                            super.onItemRangeChanged(positionStart, itemCount, payload);
                            try{
                                Log.d(this.getClass().getName(), "onItemRangeChanged ");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                        @Override
                        public void onItemRangeInserted(int positionStart, int itemCount) {
                            super.onItemRangeInserted(positionStart, itemCount);
                            try{
                                Log.d(this.getClass().getName(), "onItemRangeInserted ");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }

                        @Override
                        public void onItemRangeRemoved(int positionStart, int itemCount) {
                            super.onItemRangeRemoved(positionStart, itemCount);
                            try{
                                Log.d(this.getClass().getName(), "onItemRangeRemoved ");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }

                        @Override
                        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                            try{
                                Log.d(this.getClass().getName(), "     onItemRangeMoved ");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 02.03.2022 выход

        private  void МетодКпопкаВозвращениеНазадИзСогласованиии()
                throws ExecutionException, InterruptedException {
            try {
                Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеНазадИзСогласованиии" );
                bottomNavigationBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        handler.post(()->{
                            Intent Интент_BackВозвращаемАктивти = new Intent();
                            Bundle data1C = new Bundle();
                            Интент_BackВозвращаемАктивти.putExtras(data1C);
                            Интент_BackВозвращаемАктивти.setClass(getContext(), MainActivity_Dashboard.class); // Т
                            Интент_BackВозвращаемАктивти.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеНазадИзСогласованиии" );
                            startActivity( Интент_BackВозвращаемАктивти);

                            // TODO: 17.04.2023 LOG
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

                        });





              /*          // TODO Запусукаем Фргамент НАстройки  dashbord
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

                        }*/






                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 02.03.2022  принудительный обмен с 1с
        private  void МетодКпопкаПринидительноОбмена()
                throws ExecutionException, InterruptedException {
            // TODO: 02.03.2022
            try {
                // TODO: 02.03.2022
                Log.d(this.getClass().getName(), "  принудительный обмен  МетодКпопкаВозвращениеНазадИзСогласованиии" );
                // TODO: 09.03.2022

                RxView.clicks(  bottomNavigationAsync)
                        .throttleFirst(3,TimeUnit.SECONDS)
                        .filter(s -> !s.toString().isEmpty())
                        .map(new Function<Unit, Object>() {
                            @Override
                            public Object apply(Unit unit) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                                return    bottomNavigationAsync;
                            }
                        })
                        .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                throwable.printStackTrace();
                                Log.e(getContext().getClass().getName(),
                                        "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                throwable.printStackTrace();
                                Log.e(getContext().getClass().getName(),
                                        "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return false;
                            }
                        })
                        .subscribe( GetNameSingleAsync1c-> {
                            ///todo revboot

                            // TODO: 08.11.2023
                            BisnesLogica1Согласование.  metodEndingRecyreViewElseData1C();
                            onResume();
                            BisnesLogica1Согласование. МетодПерегрузкаRecyceView();

                           BisnesLogica1Согласование. МетодISNUllПолучениеДанныхОт1сДляСогласования(); ;
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " bottomNavigationSearch " +bottomNavigationSearch );

                        });

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        private  void МетодКликПоПоиску()
                throws ExecutionException, InterruptedException {
            // TODO: 02.03.2022
            try {


                RxView.clicks(  bottomNavigationSearch)
                        .throttleFirst(1,TimeUnit.SECONDS)
                        .filter(s -> !s.toString().isEmpty())
                        .map(new Function<Unit, Object>() {
                            @Override
                            public Object apply(Unit unit) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                                return    bottomNavigationSearch;
                            }
                        })
                        .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                throwable.printStackTrace();
                                Log.e(getContext().getClass().getName(),
                                        "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                throwable.printStackTrace();
                                Log.e(getContext().getClass().getName(),
                                        "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return false;
                            }
                        })
                        .subscribe( GetNameSingleNewFile1c-> {

                            if (jsonNode1сСогласованияAllRows!=null) {
                                if (searchview_commitpay.isEnabled()) {
                                    searchview_commitpay.setVisibility(View.INVISIBLE);
                                    searchview_commitpay.setEnabled(false);
                                    ViewGroup.LayoutParams params= searchview_commitpay.getLayoutParams();
                                    params.height=0;
                                    searchview_commitpay.setLayoutParams(params);

                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                }else {
                                    searchview_commitpay.setVisibility(View.VISIBLE);
                                    searchview_commitpay.setEnabled(true);
                                    ViewGroup.LayoutParams params= searchview_commitpay.getLayoutParams();
                                    params.height=80;
                                    searchview_commitpay.setLayoutParams(params);

                                    // TODO: 21.11.2023 Enadble Filter

                                    new AdapterSerachViewPay(searchview_commitpay).setAdapterSerachViewPay();

                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                }

                                searchview_commitpay.startAnimation(animation1);
                                searchview_commitpay.requestLayout();
                                searchview_commitpay.refreshDrawableState();

                                recyclerViewСогласование1С.requestLayout();
                                recyclerViewСогласование1С.refreshDrawableState();

                                relativeLayout_recyreview.requestLayout();
                                relativeLayout_recyreview.refreshDrawableState();

                                linearLayout_commitpay.requestLayout();
                                linearLayout_commitpay.refreshDrawableState();
                            }


                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " bottomNavigationSearch " +bottomNavigationSearch );

                        });
                // TODO: 09.03.2022
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




        // TODO: 04.03.2022 прозвомжность Заполения RecycleView
        void МетодЗаполенияRecycleViewДляЗадач() {
            try {
                if (myRecycleViewAdapter==null) {
                    myRecycleViewAdapter = new MyRecycleViewAdapter(jsonNode1сСогласованияAllRows);
                    myRecycleViewAdapter.notifyDataSetChanged();
                    recyclerViewСогласование1С.setAdapter(myRecycleViewAdapter);
                    recyclerViewСогласование1С.getAdapter().notifyDataSetChanged();
                }

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"jsonNode1сСогласованияAllRows  "
                        + jsonNode1сСогласованияAllRows);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        void МетодЗаполенияRecycleViewIsNUllДляЗадач() {
            try {
                if (myRecycleViewIsNullAdapter==null) {
                    ArrayList<String>arrayListIsNull1cData=new ArrayList<>();
                    arrayListIsNull1cData.add("IsNull1cPayCommit");
                    myRecycleViewIsNullAdapter = new MyRecycleViewIsNullAdapter(arrayListIsNull1cData);
                    myRecycleViewIsNullAdapter.notifyDataSetChanged();
                    recyclerViewСогласование1С.setAdapter(myRecycleViewIsNullAdapter);
                    recyclerViewСогласование1С.getAdapter().notifyDataSetChanged();
                }
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"jsonNode1сСогласованияAllRows  "
                        + jsonNode1сСогласованияAllRows);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



        // TODO: 04.03.2022 прозвомжность инициализации RecycleView
        void МетодИнициализацииRecycleViewДляЗадач() {
            try {

              /*  DividerItemDecoration dividerItemDecorationHor=
                        new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);*/


                DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerViewСогласование1С.getContext(), DividerItemDecoration.HORIZONTAL);

                GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{0xfff7f7f7, 0xfff7f7f7});
                drawable.setSize(1,1);
                itemDecoration.setDrawable(drawable);

                /*            dividerItemDecorationHor.setDrawable(getContext().getDrawable(R.drawable.divider_for_order_transport1));///R.dimen.activity_horizontal_margin*/
                recyclerViewСогласование1С.addItemDecoration(itemDecoration);

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewСогласование1С.setLayoutManager(linearLayoutManager);//TODO new LinearLayoutManager(getContext()) // TODO: 28.02.2022 создаем наш первый RecyclerView recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerViewСогласование1С.startAnimation(animationДляСогласования);
                // TODO: 28.02.2022
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласованияAllRows " +jsonNode1сСогласованияAllRows);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

        // TODO: 05.03.2022 метод создание кнопок снизу навигатор
        private void metodStartingRecyreViewElseData1C() {
            try{
                if (jsonNode1сСогласованияAllRows!=null) {
                    BisnesLogica1Согласование.  МетодЗаполенияRecycleViewДляЗадач();
                } else {
                    BisnesLogica1Согласование.      МетодЗаполенияRecycleViewIsNUllДляЗадач();
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///
            }
        }

        private void metodEndingRecyreViewElseData1C() {
            try{
                if (jsonNode1сСогласованияAllRows!=null) {
                    BisnesLogica1Согласование.   методRebootDisaynRecyreViewonStopOrAsync(jsonNode1сСогласованияAllRows);
                } else {
                    BisnesLogica1Согласование.      методRebootRecyreviewDontJsonNULL();
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///
            }
        }

        // TODO: 14.03.2022

        private void МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(@NonNull JsonNode jsonNode1сСогласованияRow)
               {
            try {
                // TODO: 09.03.2022
                if (jsonNode1сСогласованияRow!=null) {
                    if (  jsonNode1сСогласованияRow.size()>0) {
                        bottomnavigationActivicommit_search.getOrCreateBadge(R.id.bottomNavigationAsync).setBackgroundColor(Color.parseColor("#008080"));
                        bottomnavigationActivicommit_search.getOrCreateBadge(R.id.bottomNavigationAsync).setNumber(jsonNode1сСогласованияRow.size());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    } else {
                        bottomnavigationActivicommit_search.getOrCreateBadge(R.id.bottomNavigationAsync).setBackgroundColor(Color.RED)        ;
                        bottomnavigationActivicommit_search.getOrCreateBadge(R.id.bottomNavigationAsync).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    }
                }else {
                    bottomnavigationActivicommit_search.getOrCreateBadge(R.id.bottomNavigationAsync).setBackgroundColor(Color.RED)        ;
                    bottomnavigationActivicommit_search.getOrCreateBadge(R.id.bottomNavigationAsync).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                }
                bottomnavigationActivicommit_search.requestLayout();
                bottomnavigationActivicommit_search.refreshDrawableState();

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




















        // TODO: 15.03.2022  перенесееный код
        // TODO: 28.02.2022 начало  MyViewHolderДляЧата
        protected class MyViewHolder extends RecyclerView.ViewHolder {// TODO: 28.02.2022 начало  MyViewHolderДляЧата
            // TODO: 28.02.2022
            private       TextView textView1, textView2, textView3, textView4, textView5Намеклатура, textorganizationvalue, textvalueSUM;
            private      TextView textViewКонтрагент,textViewЦФО,textViewДДС,TextViewНамелклатура;
            private      MaterialCardView cardviewmatireacommitpay;
            private      MaterialButton КнопкаСогласованиеОтказ,КнопкаУспешноеСогласования;
            private     TableLayout tableLayoutcommitpayfiles,tableLayoutcommitpay;
            protected ProgressBar progressbarfilepay;

            // TODO: 02.03.2022
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                try{
                // TODO: 02.03.2022
                МетодИнициализацииКомпонетовЗаданияCardView(itemView);
                // TODO: 01.03.2022
                Log.d(this.getClass().getName(), "  private class MyViewHolderДляЧата extends RecyclerView.ViewHolder  itemView   " + itemView);
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }

            // TODO: 14.03.2022
            private void МетодИнициализацииКомпонетовЗаданияCardView(@NonNull View itemView) {
                try {
                    if ( asyncTaskLoader!=null &&  asyncTaskLoader.isReset()) {
                        Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);
                        textView1 = itemView.findViewById(R.id.text0_valuepay);
                        textView2 = itemView.findViewById(R.id.text1_valuepay);
                        textView3 = itemView.findViewById(R.id.text2_valuepay);
                        textView4 = itemView.findViewById(R.id.text3_valuepay);
                        textView5Намеклатура = itemView.findViewById(R.id.text4_valuepay);
                        textorganizationvalue = itemView.findViewById(R.id.textorganizationvalue);
                       textvalueSUM = itemView.findViewById(R.id.textvalueSUM);
                        textViewКонтрагент = itemView.findViewById(R.id.text2_polamoneybudchetheader);
                        textViewЦФО = itemView.findViewById(R.id.text1_polamoneybudchetheader);
                        textViewДДС = itemView.findViewById(R.id.text3_polamoneybudchetheader);
                        TextViewНамелклатура = itemView.findViewById(R.id.text4_polamoneybudchetheader);
                        Log.d(this.getClass().getName(), " materialCardView  textView2 " + textView4 + "  cardviewmatireacommitpay " + cardviewmatireacommitpay);
                        cardviewmatireacommitpay = itemView.findViewById(R.id.cardviewmatireacommitpay);
                        КнопкаСогласованиеОтказ = itemView.findViewById(R.id.BottomFor_task_deseble_otkas_commitpay);
                        КнопкаУспешноеСогласования = itemView.findViewById(R.id.BottomFor_task_complete_result_success_commitpay);
                        tableLayoutcommitpayfiles = itemView.findViewById(R.id.tableLayoutcommitpayfiles);
                        tableLayoutcommitpay = itemView.findViewById(R.id.tableLayoutcommitpay);
                        progressbarfilepay = itemView.findViewById(R.id.progressbarfilepay);
                    }

                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 materialCardView   " + cardviewmatireacommitpay);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                /////
            }
        } // TODO: 28.02.2022 конец  MyViewHolderДляЧата
        // TODO: 28.02.2022 конец  MyViewHolderДляЧата


        // TODO: 28.02.2022 ViewHolder

        @SuppressLint("SuspiciousIndentation")
        class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
           private JsonNode jsonNode1сСогласования;
           private JsonNode jsonNode1сСогласованияRow;

            public MyRecycleViewAdapter(@NotNull JsonNode jsonNode1сСогласования) {
                // super();
                if (jsonNode1сСогласования!=null) {
                    this.jsonNode1сСогласования = jsonNode1сСогласования;
                }
                Log.i(this.getClass().getName(), " jsonNode1сСогласования  " + jsonNode1сСогласования );

            }


            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder,  @NonNull int position, @NonNull List<Object> payloads) {
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position);
                try {
                    if (  jsonNode1сСогласования.size()>0) {

                           jsonNode1сСогласованияRow = jsonNode1сСогласования.get(position); // Here's

                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"JsonRowДанныеСогласование1С "
                                + jsonNode1сСогласованияRow +
                                " jsonNode1сСогласованияRow " + jsonNode1сСогласованияRow     + " position " +position);
                    }
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"jsonNode1сСогласования "
                            + jsonNode1сСогласования + " position " +position);
                    // TODO: 30.03.2022
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                super.onBindViewHolder(holder, position, payloads);
            }







            @Override
            public void setHasStableIds(boolean hasStableIds) {
                super.setHasStableIds(hasStableIds);
            }

            @Override
            public void onViewRecycled(@NonNull MyViewHolder holder) {
                super.onViewRecycled(holder);
            }

            @Override
            public boolean onFailedToRecycleView(@NonNull MyViewHolder holder) {
                // TODO: 03.11.2023 Parent
                return super.onFailedToRecycleView(holder);

            }

            @Override
            public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
                super.onViewAttachedToWindow(holder);

            }

            @Override
            public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
                super.onViewDetachedFromWindow(holder);
            }

            @Override
            public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

                recyclerView.removeAllViews();

                recyclerView.getRecycledViewPool().clear();
                super.onAttachedToRecyclerView(recyclerView);
            }

            @Override
            public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
                super.onDetachedFromRecyclerView(recyclerView);
            }

            @Override
            public int getItemViewType(int position) {
                Log.i(this.getClass().getName(), "      holder.textView1  position " + position);
                try {
                    // TODO: 30.03.2022
                    Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
                    // TODO: 30.03.2022
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

                return super.getItemViewType(position);
            }


            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View viewГлавныйВидДляRecyclleViewДляСогласования = null;
                try {
                    if (jsonNode1сСогласования!=null) {
                            if (jsonNode1сСогласования.size()>0) {
                             /*   viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.simple_for_commitpay_cardview1, parent, false);//todo old simple_for_takst_cardview1*/
                             /*   viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.simple_for_commitpay_cardview_file, parent, false);//todo old simple_for_takst_cardview1*/
                              /*  viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.simple_for_commitpay_cardview_grid_file, parent, false);//todo old simple_for_takst_cardview1*/
                                viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.simple_for_commitpay_cardview_grid_file, parent, false);//todo old simple_for_takst_cardview1

                            } else {
                                viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.simple_for_commitpay_cardview1empty, parent, false);//todo old simple_for_takst_cardview1
                            }

                    }else{
                        if (asyncTaskLoader.isStarted()) {
                            viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.simple_for_commitpay_cardview1empty_in_prossering, parent, false);
                        } else {
                            viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.simple_for_commitpay_dont_jsonot1c, parent, false);
                        }
                    }

                    // TODO: 22.03.2022
                    myViewHolder = new SubClassФрагмент1Согласование.MyViewHolder(viewГлавныйВидДляRecyclleViewДляСогласования);
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "   myViewHolder" + myViewHolder);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return myViewHolder;

            }

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                try {
                    Log.i(this.getClass().getName(), "   создание согласования"
                            + myViewHolder
                            + " jsonNode1сСогласования " + jsonNode1сСогласования);
                    // TODO: 03.11.2023  работает приналичии Данных с 1С
                    if (jsonNode1сСогласования.size()>0 &&  asyncTaskLoader.isReset()) {

                        МетодБиндингаСозданиеНомерДокумента(holder, jsonNode1сСогласованияRow);
                        МетодБиндингаСозданиеСФО(holder, jsonNode1сСогласованияRow);
                        МетодБиндингаСозданиеОрганизация(holder, jsonNode1сСогласованияRow);
                        МетодБиндингаСозданиеКонтрагент(holder, jsonNode1сСогласованияRow);
                        МетодБиндингаСозданиеСумма(holder, jsonNode1сСогласованияRow);
                        МетодБиндингаСозданиеДДС(holder, jsonNode1сСогласованияRow);
                        МетодБиндингаСозданиеНомелклатура(holder, jsonNode1сСогласованияRow);
                        МетодКпопкаОтказаСогласования(holder);
                        МетодКпопкаСогласованияУспешное(holder);
                        МетодForPrograBarInner(holder);


                        // TODO: 03.11.2023 дополнитешльный механизм добаляем файлы с 1С
                        downloadFileFrom1C(holder, jsonNode1сСогласованияRow);

                        // TODO: 03.11.2023  метод повторгого переопределения Кнопок снизу
                        BisnesLogica1Согласование.МетодКпопкаСоЗачкомКраснымДополнительныйСтатус( jsonNode1сСогласования);

                    }else{
                        // TODO: 01.03.2022
                        Log.i(this.getClass().getName(), "  Нет данных для занрузки   subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1Согласование. " +
                                " МетодРелистарцииЛокальногоБродкастераПослеСменыСтатусаСогласования(holder);  holder " +holder);
                    }

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " jsonNode1сСогласования " +jsonNode1сСогласования);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }



// TODO: 03.11.2023  метод загрузки файлов с 1с
            void  downloadFileFrom1C(@NonNull MyViewHolder holder,@NotNull JsonNode jsonNode1сСогласованияRow){
                // TODO: 03.11.2023 advanset code Downloaf Image rom Commit Pay
                // TODO: 03.11.2023  запускаем получние отображения File from 1c
                ClassBizneLogica.FileFrom1CCommitPay fileFrom1CCommitPay1=new ClassBizneLogica().new FileFrom1CCommitPay(holder ,getContext()) ;
                // TODO: 08.11.2023 строчка добавление файлов FILE  от 1С
                fileFrom1CCommitPay1.startFileFrom1CCommitPay(jsonNode1сСогласованияRow,holder);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"   holder.tableLayoutcommitpayfiles "
                        +   holder.tableLayoutcommitpayfiles
                        + " jsonNode1сСогласованияRow " +jsonNode1сСогласованияRow );


            }



            ///todo первый метод #1

            private void МетодБиндингаСозданиеНомерДокумента(@NonNull MyViewHolder holder,@NonNull JsonNode jsonNode1сСогласованияRow) {
                try {
                    if (jsonNode1сСогласованияRow!=null && holder.textView1!=null ) {///"Ndoc"

                    String    ПерваяСтрочкаЗначения=    jsonNode1сСогласованияRow.get("Ndoc").asText().trim();

                         holder.textView1.setText(ПерваяСтрочкаЗначения);
                        holder.КнопкаУспешноеСогласования.setTag(ПерваяСтрочкаЗначения);
                        holder.КнопкаСогласованиеОтказ.setTag(ПерваяСтрочкаЗначения);
                        holder.textView1.setTag(ПерваяСтрочкаЗначения);
                        Log.i(this.getClass().getName(), "  Ndoc   holder.КнопкаУспешноеСогласования " +  holder.КнопкаУспешноеСогласования.getTag()+
                                "     holder.КнопкаСогласованиеОтказ " +    holder.КнопкаСогласованиеОтказ.getTag() );

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

            }

            ///todo первый метод #2

            private void МетодБиндингаСозданиеДДС(@NonNull MyViewHolder holder,@NonNull JsonNode jsonNode1сСогласованияRow) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

                    if (jsonNode1сСогласованияRow!=null &&   holder.textView2!=null ) {

                        String ПерваяСтрочкаЗначения = jsonNode1сСогласованияRow.get("articleDDS").asText();
                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), "  articleDDS ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                        // TODO: 28.02.2022
                        holder.textView2.setText(ПерваяСтрочкаЗначения);
                        holder.textView2.setTag(ПерваяСтрочкаЗначения);
                        holder.textView2.requestLayout();
                        holder.textView2.forceLayout();
                        holder.textView2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                holder.textView2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                int height =  holder.textView2.getHeight();
                                int width =  holder.textView2.getWidth();
                                holder.textViewДДС.setHeight( height);
                                holder.textViewДДС.requestLayout();
                                holder.textViewДДС.forceLayout();
                            }
                        });

                    }

                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

            }
            ///todo первый метод #3

            private void МетодБиндингаСозданиеКонтрагент(@NonNull MyViewHolder holder,@NonNull JsonNode jsonNode1сСогласованияRow) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

                    if (jsonNode1сСогласования!=null &&  holder.textView3!=null ) {
                        //TODO
                        String ПерваяСтрочкаЗначения = jsonNode1сСогласованияRow.get("counterparty").asText();
                        // TODO: 02.03.2022

                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), " organization ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                        // TODO: 28.02.2022
                        holder.textView3.setText(ПерваяСтрочкаЗначения);
                        holder.textView3.setTag(ПерваяСтрочкаЗначения);
                        holder.textView3.requestLayout();
                        holder.textView3.forceLayout();
                        holder.textView3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                holder.textView3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                int height =  holder.textView3.getHeight();
                                int width =  holder.textView3.getWidth();
                                holder.textViewКонтрагент.setHeight( height);
                                holder.textViewКонтрагент.requestLayout();
                                holder.textViewКонтрагент.forceLayout();
                            }
                        });

                    }

                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }




            ///todo первый метод #4

            private void МетодБиндингаСозданиеСФО(@NonNull MyViewHolder holder,@NonNull JsonNode jsonNode1сСогласованияRow) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (holder.textView4!=null && jsonNode1сСогласованияRow!=null ) {
                        //todo
                        String ПерваяСтрочкаЗначения = jsonNode1сСогласованияRow.get("CFO").asText();
                        // TODO: 02.03.2022

                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), " CFO ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                        // TODO: 28.02.2022

                        //TODo
                        holder.textView4.setText(ПерваяСтрочкаЗначения);
                        holder.textView4.setTag(ПерваяСтрочкаЗначения);
                        holder.textView4.requestLayout();
                        holder.textView4.forceLayout();
                        holder.textView4.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                holder.textView4.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                int height =  holder.textView4.getHeight();
                                int width =  holder.textView4.getWidth();
                                holder.textViewЦФО.setHeight( height);
                                holder.textViewЦФО.requestLayout();
                                holder.textViewЦФО.forceLayout();
                            }
                        });





                    }

                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }


            ///todo первый метод #7

            private void МетодБиндингаСозданиеНомелклатура(@NonNull MyViewHolder holder,@NonNull JsonNode jsonNode1сСогласованияRow) {
                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (jsonNode1сСогласованияRow!=null) {
                        StringBuffer stringBufferArrayNamelk=new StringBuffer();
                        final Integer[] ИНдексТекущий = {1};
                       // String ПерваяСтрочкаЗначения = jsonNode1сСогласованияRow.get("nomenclature").asText()

                  jsonNode1сСогласованияRow.get("nomenclature")
                          .elements()
                          .forEachRemaining(new Consumer<JsonNode>() {
                      @Override
                      public void accept(JsonNode jsonNode) {
                     JsonNode jsonNodesArrayНамелклатура= jsonNode.deepCopy();
                          jsonNodesArrayНамелклатура.forEach(new Consumer<JsonNode>() {
                              @Override
                              public void accept(JsonNode jsonNode) {
                                TextNode textNode=  jsonNode.deepCopy();
                                  if (!textNode.isNull()) {
                                      stringBufferArrayNamelk.append(textNode.asText());
                                      if(ИНдексТекущий[0]< jsonNode1сСогласованияRow.get("nomenclature").size()){
                                          // TODO: 24.11.2023
                                          ИНдексТекущий[0]++;
                                          stringBufferArrayNamelk.append("\n");
                                      }

                                      Log.d(getContext().getClass().getName(), "\n"
                                              + " время: " + new Date()+"\n+" +
                                              " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                              " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                                  }
                                  // TODO: 24.11.2023
                              }
                          });


                      }
                  });

                        // TODO: 24.11.2023 слашателя

                        listerDizayinNamelklatyra(holder.textView5Намеклатура, holder.TextViewНамелклатура);

                        holder.textView5Намеклатура.setText(stringBufferArrayNamelk);
                        holder.textView5Намеклатура.setTag(stringBufferArrayNamelk);

                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date()+"\n+" +
                                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                "  nomenclature stringBufferArrayNamelk " + stringBufferArrayNamelk);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

            }

            private void listerDizayinNamelklatyra(TextView holder, TextView holder1) {
                holder.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        holder.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int height =  holder.getHeight();
                        int width =  holder.getWidth();
                        holder1.setHeight( height);
                        holder1.requestLayout();
                        holder1.forceLayout();
                    }
                });
            }
            // TODO: 14.03.2022  Успешное Согласования

            private void МетодКпопкаСогласованияУспешное(@NonNull MyViewHolder holder)
                    throws ExecutionException, InterruptedException {
                try {
                    Log.d(this.getClass().getName(), "   КнопкаУспешноеСогласования    Успехх Согласования 2 " );



                    // TODO: 10.11.2023 клик по файлов
                    RxView.clicks(   holder.КнопкаУспешноеСогласования)
                            .throttleFirst(5,TimeUnit.SECONDS)
                            .filter(s -> !s.toString().isEmpty())
                            .map(new Function<Unit, Object>() {
                                @Override
                                public Object apply(Unit unit) throws Throwable {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                                    return    holder.КнопкаУспешноеСогласования;
                                }
                            })
                            .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Throwable {
                                    throwable.printStackTrace();
                                    Log.e(getContext().getClass().getName(),
                                            "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            })
                            .onErrorComplete(new Predicate<Throwable>() {
                                @Override
                                public boolean test(Throwable throwable) throws Throwable {
                                    throwable.printStackTrace();
                                    Log.e(getContext().getClass().getName(),
                                            "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    return false;
                                }
                            })
                            .subscribe( GetNameSingleNewFile1c-> {

                                MaterialButton materialButtonКнопкаУспешноеСогласования=  (MaterialButton)   GetNameSingleNewFile1c;

                                materialButtonКнопкаУспешноеСогласования.animate().rotationX(40l);

                                String ПолученыйНомерДокусментаДляОтправки=      materialButtonКнопкаУспешноеСогласования.getTag().toString().trim()   ;

                                Intent заданиеНаВыполение=new Intent();
                                заданиеНаВыполение.setAction( "ЗапускаемСогласованиеОтказИлилУспешное");
                                Bundle bundleДляПередачиВСлужбуСогласования=new Bundle();
                                bundleДляПередачиВСлужбуСогласования.putInt("PROCESS_IDСогласования", Integer.parseInt("28"));
                                bundleДляПередачиВСлужбуСогласования.putString("ПолученныйНомерДокументаСогласования", ПолученыйНомерДокусментаДляОтправки);
                                bundleДляПередачиВСлужбуСогласования.putInt("ПередаемСтатусСогласования", 2);///TODO выполнил Согласования
                                bundleДляПередачиВСлужбуСогласования.putInt("ПубличныйidPay", ПубличныйidPay);///TODO выполнил Согласования
                                заданиеНаВыполение.putExtras(bundleДляПередачиВСлужбуСогласования);

                                holder. КнопкаУспешноеСогласования.setTag(bundleДляПередачиВСлужбуСогласования);

                                Log.d(context.getClass().getName(), "заданиеНаВыполение "
                                        + заданиеНаВыполение+ "bundleДляПередачиВСлужбуСогласования "+bundleДляПередачиВСлужбуСогласования);

                                ///TODO выполнил Согласования
                                ProccesingCancelOrOKPay proccesingCancelOrOKPay=new ProccesingCancelOrOKPay();
                                proccesingCancelOrOKPay.proccerCancelOrOKPay(заданиеНаВыполение,  materialButtonКнопкаУспешноеСогласования);



                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

                                    });



/*
                    holder.КнопкаУспешноеСогласования.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            v.animate().rotationX(40l);

                            MaterialButton materialButtonКнопкаУспешноеСогласования=  (MaterialButton)     v;
                            String ПолученыйНомерДокусментаДляОтправки=      materialButtonКнопкаУспешноеСогласования.getTag().toString().trim()   ;

                            Intent заданиеНаВыполение=new Intent();
                            заданиеНаВыполение.setAction( "ЗапускаемСогласованиеОтказИлилУспешное");
                            Bundle bundleДляПередачиВСлужбуСогласования=new Bundle();
                            bundleДляПередачиВСлужбуСогласования.putInt("PROCESS_IDСогласования", Integer.parseInt("28"));
                            bundleДляПередачиВСлужбуСогласования.putString("ПолученныйНомерДокументаСогласования", ПолученыйНомерДокусментаДляОтправки);
                            bundleДляПередачиВСлужбуСогласования.putInt("ПередаемСтатусСогласования", 2);///TODO выполнил Согласования
                            bundleДляПередачиВСлужбуСогласования.putInt("ПубличныйidPay", ПубличныйidPay);///TODO выполнил Согласования
                            заданиеНаВыполение.putExtras(bundleДляПередачиВСлужбуСогласования);

                            holder. КнопкаУспешноеСогласования.setTag(bundleДляПередачиВСлужбуСогласования);

                            Log.d(context.getClass().getName(), "заданиеНаВыполение "
                                    + заданиеНаВыполение+ "bundleДляПередачиВСлужбуСогласования "+bundleДляПередачиВСлужбуСогласования);

                                 ///TODO выполнил Согласования
                            ProccesingCancelOrOKPay proccesingCancelOrOKPay=new ProccesingCancelOrOKPay();
                            proccesingCancelOrOKPay.proccerCancelOrOKPay(заданиеНаВыполение,  v);
                            // TODO: 08.11.2023 выполения

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        }
                    });*/
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            private void МетодForPrograBarInner(@NonNull MyViewHolder holder)
                     {
                try {
                    holder.  progressbarfilepay.setVisibility(View.INVISIBLE);

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            // TODO: 01.08.2022





//TODO вторая кнопка
            // TODO: 14.03.2022  отказа Согласования
            private void МетодКпопкаОтказаСогласования(@NonNull MyViewHolder holder)
                    throws ExecutionException, InterruptedException {
                try {
                    // TODO: 02.03.2022
                    Log.d(this.getClass().getName(), "  КнопкаСогласованиеОтказ    отказ 1  " );


                    RxView.clicks(   holder.КнопкаСогласованиеОтказ)
                            .throttleFirst(5,TimeUnit.SECONDS)
                            .filter(s -> !s.toString().isEmpty())
                            .map(new Function<Unit, Object>() {
                                @Override
                                public Object apply(Unit unit) throws Throwable {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                                    return    holder.КнопкаУспешноеСогласования;
                                }
                            })
                            .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Throwable {
                                    throwable.printStackTrace();
                                    Log.e(getContext().getClass().getName(),
                                            "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            })
                            .onErrorComplete(new Predicate<Throwable>() {
                                @Override
                                public boolean test(Throwable throwable) throws Throwable {
                                    throwable.printStackTrace();
                                    Log.e(getContext().getClass().getName(),
                                            "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    return false;
                                }
                            })
                            .subscribe( GetNameSingleNewFile1c-> {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

                                MaterialButton materialButtonКнопкаУспешноеСогласования=  (MaterialButton)  GetNameSingleNewFile1c;
                                materialButtonКнопкаУспешноеСогласования.animate().rotationX(40l);

                                String ПолученыйНомерДокусментаДляОтправки=  materialButtonКнопкаУспешноеСогласования.getTag().toString().trim()    ;

                                Intent заданиеНаВыполение=new Intent();
                                // TODO: 17.11.2021
                                заданиеНаВыполение.setAction( "ЗапускаемСогласованиеОтказИлилУспешное");
                                Bundle bundleДляПередачиВСлужбуСогласования=new Bundle();
                                bundleДляПередачиВСлужбуСогласования.putInt("PROCESS_IDСогласования", Integer.parseInt("28"));
                                bundleДляПередачиВСлужбуСогласования.putString("ПолученныйНомерДокументаСогласования",ПолученыйНомерДокусментаДляОтправки );
                                bundleДляПередачиВСлужбуСогласования.putInt("ПередаемСтатусСогласования", 1);///TODO выполнил Согласования
                                bundleДляПередачиВСлужбуСогласования.putInt("ПубличныйidPay", ПубличныйidPay);//ПубличныйidPay

                                заданиеНаВыполение.putExtras(bundleДляПередачиВСлужбуСогласования);

                                holder. КнопкаСогласованиеОтказ.setTag(bundleДляПередачиВСлужбуСогласования);

                                ///TODO выполнил ОТКАЗ canxel
                                ProccesingCancelOrOKPay proccesingCancelOrOKPay=new ProccesingCancelOrOKPay();
                                proccesingCancelOrOKPay.proccerCancelOrOKPay(заданиеНаВыполение,  materialButtonКнопкаУспешноеСогласования);
                                // TODO: 08.11.2023 ОТКАЗ canxel

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );


                            });


            /*

                    holder. КнопкаСогласованиеОтказ.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            v.animate().rotationX(40l);

                            MaterialButton materialButtonКнопкаУспешноеСогласования=  (MaterialButton)     v;
                            String ПолученыйНомерДокусментаДляОтправки=  materialButtonКнопкаУспешноеСогласования.getTag().toString().trim()    ;

                            Intent заданиеНаВыполение=new Intent();
                            // TODO: 17.11.2021
                            заданиеНаВыполение.setAction( "ЗапускаемСогласованиеОтказИлилУспешное");
                            Bundle bundleДляПередачиВСлужбуСогласования=new Bundle();
                             bundleДляПередачиВСлужбуСогласования.putInt("PROCESS_IDСогласования", Integer.parseInt("28"));
                            bundleДляПередачиВСлужбуСогласования.putString("ПолученныйНомерДокументаСогласования",ПолученыйНомерДокусментаДляОтправки );
                            bundleДляПередачиВСлужбуСогласования.putInt("ПередаемСтатусСогласования", 1);///TODO выполнил Согласования
                             bundleДляПередачиВСлужбуСогласования.putInt("ПубличныйidPay", ПубличныйidPay);//ПубличныйidPay

                            заданиеНаВыполение.putExtras(bundleДляПередачиВСлужбуСогласования);

                            holder. КнопкаСогласованиеОтказ.setTag(bundleДляПередачиВСлужбуСогласования);



                            ///TODO выполнил ОТКАЗ canxel
                            ProccesingCancelOrOKPay proccesingCancelOrOKPay=new ProccesingCancelOrOKPay();
                             proccesingCancelOrOKPay.proccerCancelOrOKPay(заданиеНаВыполение,  v);
                            // TODO: 08.11.2023 ОТКАЗ canxel

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

                        }
                    });*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }




            ///todo первый метод #3

            private void МетодБиндингаСозданиеОрганизация(@NonNull MyViewHolder holder,@NonNull JsonNode jsonNode1сСогласованияRow) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

                    if (jsonNode1сСогласованияRow!=null &&   holder.textorganizationvalue !=null ) {
                        //TODO
                        String ПерваяСтрочкаЗначения = jsonNode1сСогласованияRow.get("organization").asText();
                        // TODO: 02.03.2022

                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), " organization ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                        // TODO: 28.02.2022
                        holder.textorganizationvalue.setText(ПерваяСтрочкаЗначения);
                        holder.textorganizationvalue.setTag(ПерваяСтрочкаЗначения);
                    }

                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }

            ///todo первый метод #5








            private void МетодБиндингаСозданиеСумма(@NonNull MyViewHolder holder,@NonNull JsonNode jsonNode1сСогласованияRow) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

                    if (jsonNode1сСогласованияRow!=null &&  holder.textvalueSUM !=null ) {
                        //TODO
                        String ПерваяСтрочкаЗначения = jsonNode1сСогласованияRow.get("sum").asText();
                        // TODO: 02.03.2022

                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), "  sum ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                        // TODO: 28.02.2022
                        holder.textvalueSUM.setText(ПерваяСтрочкаЗначения.toString()+ " руб");
                        holder.textvalueSUM.setTag(ПерваяСтрочкаЗначения);
                    }

                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }

























            // TODO: 29.03.2022  слутаеть смены статуса







            @Override
            public long getItemId(int position) {
                // TODO: 04.03.2022

                Log.i(this.getClass().getName(), "     getItemId holder.position " + position);

                return super.getItemId(position);

            }




            @Override
            public int getItemCount() {
                // TODO: 02.03.2022
                int КоличесвоСтрок;
                if (jsonNode1сСогласования.size()>0) {
                    КоличесвоСтрок = jsonNode1сСогласования.size();
                    Log.d(this.getClass().getName(), "jsonNode1сСогласования.size() " + jsonNode1сСогласования.size() + " КоличесвоСтрок " +КоличесвоСтрок);
                } else {
                    КоличесвоСтрок=1;
                    Log.d(this.getClass().getName(), "jsonNode1сСогласования.size() " + jsonNode1сСогласования.size() + " холостой ход КоличесвоСтрок " +КоличесвоСтрок);
                }
                Log.d(this.getClass().getName(), "jsonNode1сСогласования.size() " + jsonNode1сСогласования.size() + " КоличесвоСтрок " +КоличесвоСтрок);

                // TODO: 28.02.2022
                return КоличесвоСтрок ;
            }
      }//TODO  конец два11


// TODO: 09.11.2023 ВТОРОЯ Rereview
class MyRecycleViewIsNullAdapter extends RecyclerView.Adapter<MyViewHolder> {
  private   ArrayList<String>arrayListIsNull1cData=new ArrayList<>();
    public MyRecycleViewIsNullAdapter(@NotNull ArrayList<String>arrayListIsNull1cData) {
        // super();
        this.arrayListIsNull1cData = arrayListIsNull1cData;
        Log.i(this.getClass().getName(), " arrayListIsNull1cData.size() " + arrayListIsNull1cData.size());

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,  @NonNull int position, @NonNull List<Object> payloads) {
        Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position);
        try {
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"arrayListIsNull1cData "
                    + arrayListIsNull1cData + " position " +position);
            // TODO: 30.03.2022
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        super.onBindViewHolder(holder, position, payloads);
    }







    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull MyViewHolder holder) {
        // TODO: 03.11.2023 Parent
        return super.onFailedToRecycleView(holder);

    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.removeAllViews();

        recyclerView.getRecycledViewPool().clear();
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public int getItemViewType(int position) {
        Log.i(this.getClass().getName(), "      holder.textView1  position " + position);
        try {
            // TODO: 30.03.2022
            Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
            // TODO: 30.03.2022

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }

        return super.getItemViewType(position);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewГлавныйВидДляRecyclleViewДляСогласованияISNull = null;
        try {


                if (asyncTaskLoader.isStarted() ) {
                    viewГлавныйВидДляRecyclleViewДляСогласованияISNull = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.simple_for_commitpay_cardview1empty_in_prossering, parent, false);


                }else {
                    viewГлавныйВидДляRecyclleViewДляСогласованияISNull = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.simple_for_commitpay_dont_jsonot1c, parent, false);

                }

            // TODO: 22.03.2022
            myViewHolder = new SubClassФрагмент1Согласование.MyViewHolder(viewГлавныйВидДляRecyclleViewДляСогласованияISNull);
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "   viewГлавныйВидДляRecyclleViewДляСогласованияISNull" + viewГлавныйВидДляRecyclleViewДляСогласованияISNull);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " position " +position);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public long getItemId(int position) {
        // TODO: 04.03.2022

        Log.i(this.getClass().getName(), "     getItemId holder.position " + position);

        return super.getItemId(position);

    }




    @Override
    public int getItemCount() {
        // TODO: 02.03.2022
        int КоличесвоСтрок;
        if (arrayListIsNull1cData.size()>0) {
            КоличесвоСтрок = arrayListIsNull1cData.size();
            Log.d(this.getClass().getName(), "arrayListIsNull1cData.size() " + arrayListIsNull1cData.size() + " КоличесвоСтрок " +КоличесвоСтрок);
        } else {
            КоличесвоСтрок=1;
            Log.d(this.getClass().getName(), "arrayListIsNull1cData.size() " + arrayListIsNull1cData.size() + " холостой ход КоличесвоСтрок " +КоличесвоСтрок);
        }
        Log.d(this.getClass().getName(), "arrayListIsNull1cData.size() " + arrayListIsNull1cData.size() + " КоличесвоСтрок " +КоличесвоСтрок);

        // TODO: 28.02.2022
        return КоличесвоСтрок ;
    }
       }//TODO  конец два22



        // TODO: 12.07.2022  метод получение данных от 1С для согласования
        private void metodGetDataOt1cCommitPay() {
            try {
                asyncTaskLoader=new AsyncTaskLoader<JsonNode>(getContext()) {
                    @Override
                    protected void onStartLoading() {
                        super.onStartLoading();
                        try{
                        progressBarCommitPay.setVisibility(View.VISIBLE);
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        // TODO: 28.02.2022*/
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    }

                    @Nullable
                    @Override
                    public JsonNode loadInBackground() {
                        JsonNode jsonNode1сСогласования = null;
                        try{
                //TODO получаем данные для соглачования
                                //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С
                             jsonNode1сСогласования =
                                        new Class_Get_Json_1C(getContext() ,"http://uat.dsu1.ru:55080/dds/hs/jsonto1c/listofdocuments")
                                                .МетодПингаИПОлучениеДанныхОт1сДляСогласования(getContext(),ПубличныйidPay);//ПубличныйidPay*/



/*
[{"Ndoc":"000021992","CFO":"База (Управление ул. Проездная, 18/27)","organization":"СОЮЗ АВТОДОР ООО","counterparty":"СИТИЛИНК ООО","sum":6,"articleDDS":"2.2.04. Оргтехника","nomenclature":[{"nomen":"Тест 1"},{"nomen":"Тест 2"},{"nomen":"Тест 3"}],"filenames":[{"ВinNameFile":"Текстовый документ","expansion":"txt"}]},
                            {"Ndoc":"000021993","CFO":"База (Управление ул. Проездная, 18/27)","organization":"СОЮЗ АВТОДОР ООО","counterparty":"ИП Пряслов Алексей Александрович","sum":50,"articleDDS":"2.2.05. Прочие (инвестиционная деятельность)","nomenclature":[{"nomen":"Тест картридж"}],"filenames":[{"ВinNameFile":"Справочник","expansion":"xlsx"}]}]*/



                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  jsonNode1сСогласования  " + jsonNode1сСогласования);




                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласования " +jsonNode1сСогласования);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                        return jsonNode1сСогласования;
                    }
                };
                asyncTaskLoader.startLoading();
                asyncTaskLoader.forceLoad();
                asyncTaskLoader.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener<JsonNode>() {
                    @Override
                    public void onLoadComplete(@NonNull Loader<JsonNode> loader, @Nullable JsonNode data) {
                        try{
                            if (data!=null) {
                                jsonNode1сСогласованияAllRows=null;
                                // TODO: 21.11.2023 set data
                                jsonNode1сСогласованияAllRows=   data;
                                asyncTaskLoader.reset();

                                BisnesLogica1Согласование.metodStartingRecyreViewElseData1C();

                            }else {
                                asyncTaskLoader.reset();
                            }
                            // TODO: 08.11.2023
                            BisnesLogica1Согласование.  metodEndingRecyreViewElseData1C();
                            onResume();
                            BisnesLogica1Согласование. МетодПерегрузкаRecyceView();

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласованияAllRows " +jsonNode1сСогласованияAllRows);
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }


                });

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }





// TODO: 21.11.2023  метод получение данныых Синхронно
private void МетодISNUllПолучениеДанныхОт1сДляСогласования() {
    try {
        asyncTaskLoader=new AsyncTaskLoader<JsonNode>(getContext()) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                try{
                    progressBarCommitPay.setVisibility(View.VISIBLE);
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            @Nullable
            @Override
            public JsonNode loadInBackground() {
                JsonNode jsonNode1сСогласования = null;
                try{
                    //TODO получаем данные для соглачования
                    //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С
                    jsonNode1сСогласования =
                            new Class_Get_Json_1C(getContext() ,"http://uat.dsu1.ru:55080/dds/hs/jsonto1c/listofdocuments")
                                    .МетодПингаИПОлучениеДанныхОт1сДляСогласования(getContext(),ПубличныйidPay);//ПубличныйidPay*/


                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  jsonNode1сСогласования  " + jsonNode1сСогласования);


/*
[{"Ndoc":"000021992","CFO":"База (Управление ул. Проездная, 18/27)","organization":"СОЮЗ АВТОДОР ООО","counterparty":"СИТИЛИНК ООО","sum":6,"articleDDS":"2.2.04. Оргтехника","nomenclature":[{"nomen":"Тест 1"},{"nomen":"Тест 2"},{"nomen":"Тест 3"}],"filenames":[{"ВinNameFile":"Текстовый документ","expansion":"txt"}]},
                            {"Ndoc":"000021993","CFO":"База (Управление ул. Проездная, 18/27)","organization":"СОЮЗ АВТОДОР ООО","counterparty":"ИП Пряслов Алексей Александрович","sum":50,"articleDDS":"2.2.05. Прочие (инвестиционная деятельность)","nomenclature":[{"nomen":"Тест картридж"}],"filenames":[{"ВinNameFile":"Справочник","expansion":"xlsx"}]}]*/

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласования " +jsonNode1сСогласования);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return jsonNode1сСогласования;
            }
        };
        asyncTaskLoader.startLoading();
        asyncTaskLoader.forceLoad();
        asyncTaskLoader.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener<JsonNode>() {
            @Override
            public void onLoadComplete(@NonNull Loader<JsonNode> loader, @Nullable JsonNode data) {
                try{

// TODO: 21.11.2023 получаем данные сихронно для Async
                    if (data!=null) {
                        jsonNode1сСогласованияAllRows=null;
                        // TODO: 21.11.2023 set data
                        jsonNode1сСогласованияAllRows=   data;

                        BisnesLogica1Согласование.metodStartingRecyreViewElseData1C();
                    }
                    // TODO: 21.11.2023
                    asyncTaskLoader.reset();

                    // TODO: 08.11.2023
                    BisnesLogica1Согласование.  metodEndingRecyreViewElseData1C();
                    onResume();
                    BisnesLogica1Согласование. МетодПерегрузкаRecyceView();



                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласованияAllRows " +jsonNode1сСогласованияAllRows);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                            Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


        });

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

}
















        void МетодПерегрузкаRecyceView() {
            try {
                bottomnavigationActivicommit_search.requestLayout();
                bottomnavigationActivicommit_search.forceLayout();
                recyclerViewСогласование1С.requestLayout();
                recyclerViewСогласование1С.forceLayout();
                linearLayout_commitpay.requestLayout();
                linearLayout_commitpay.forceLayout();

                progressBarCommitPay.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



        private void методЗакрываемКлавитатуру( ) {
            try{


                searchview_commitpay.clearFocus();

                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
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





        private void методRebootDisaynRecyreViewonStopOrAsync(@NonNull  JsonNode jsonNode1сСогласованияAllRows) {
            try{
                if (jsonNode1сСогласованияAllRows!=null) {
                if (myRecycleViewAdapter!=null) {
                    myRecycleViewAdapter.jsonNode1сСогласования=jsonNode1сСогласованияAllRows;
                    myRecycleViewAdapter.notifyDataSetChanged();
                    RecyclerView.Adapter recyclerViewAdapter=         recyclerViewСогласование1С.getAdapter();
                    recyclerViewСогласование1С.swapAdapter(recyclerViewAdapter,true);
                    recyclerViewСогласование1С.getAdapter().notifyDataSetChanged();
                }
                }
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " jsonNode1сСогласованияAllRows " +jsonNode1сСогласованияAllRows);
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

        private void методClearRecyreView() {

            try{
                    if (recyclerViewСогласование1С!=null) {
                        recyclerViewСогласование1С.getRecycledViewPool().clear();
                        recyclerViewСогласование1С.removeAllViews();
                    }
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " jsonNode1сСогласованияAllRows " +jsonNode1сСогласованияAllRows);
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


        private void методRebootRecyreviewDontJsonNULL() {
            try{
                if (myRecycleViewIsNullAdapter!=null) {
                    myRecycleViewIsNullAdapter.notifyDataSetChanged();
                    RecyclerView.Adapter recyclerViewAdapter=         recyclerViewСогласование1С.getAdapter();
                    recyclerViewСогласование1С.swapAdapter(recyclerViewAdapter,true);
                    recyclerViewСогласование1С.getAdapter().notifyDataSetChanged();
                }
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " jsonNode1сСогласованияAllRows " +jsonNode1сСогласованияAllRows);
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





        void МетодHandlerCallBack() {
            handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    try {
                        Log.d(this.getClass().getName(), " msg  " + msg);
                        Bundle bundle = msg.getData();
                        Log.d(this.getClass().getName(), " bundle  " + bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return true;
                }
            });
        }



        private void metodSetNameCommitHeaders() {
            try{
            textViewТекущаяЗадача.setText("Список согласований".toUpperCase());
            textViewТекущаяЗадача.startAnimation(animation1);
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласованияAllRows " +jsonNode1сСогласованияAllRows);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }
        
        
        
        
        
        
        
        
        
        

    }

    // TODO: 09.11.2023  второй тиип

    // TODO: 03.10.2023  class  bixneslogica


    class ClassBizneLogica {

        private void методСлушательФрагментовBinder() {
            try {
                fragmentManager.setFragmentResultListener("callbackbinderdashbord", lifecycleOwner, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        if (requestKey.equalsIgnoreCase("callbackbinderdashbord")) {
                            try {
                                binderСогласования1C = (Service_Notificatios_Для_Согласования.LocalBinderДляСогласования) result.getBinder("binderСогласования1C");

                                // TODO: 21.08.2023
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                        "  binderСогласования1C " + binderСогласования1C);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(getContext().getClass().getName(),
                                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
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
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 03.11.2023  класс добпдонительного добвления поля Файлыв Более Онлгого
        class FileFrom1CCommitPay {
            private SubClassФрагмент1Согласование.MyViewHolder holder;
            private Context context;

            public FileFrom1CCommitPay(@NonNull SubClassФрагмент1Согласование.MyViewHolder holder, @NonNull Context context) {
                this.holder = holder;
                this.context = context;
            }

            void startFileFrom1CCommitPay(@NotNull JsonNode jsonNode1сСогласованияRow,@NonNull SubClassФрагмент1Согласование.MyViewHolder holder) {
                try{
                    // TODO: 03.11.2023 Parent
                    TableLayout tableLayoutcommitpayfiles = (TableLayout) holder.tableLayoutcommitpayfiles;
                    // TODO: 10.11.2023  Сама Вставка Данных Файлов От 1с Согласование
                    ProccesingCancelOrOKPay  proccesingCancelOrOKPay =new ProccesingCancelOrOKPay() ;

                    proccesingCancelOrOKPay.new AddFilesot1CPaycommitting()
                            .addfilessot1CPaycommitting(  tableLayoutcommitpayfiles,  jsonNode1сСогласованияRow,holder);

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                            " jsonNode1сСогласованияAllRows " +jsonNode1сСогласованияAllRows);

                    // TODO: 10.11.2023  после всего добавлени выкобчаем програсс бар
                    progressBarCommitPay.setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            }


            // TODO: 09.11.2023  insert from Paren Row





            //todo end class FileFrom1CCommitPay
        }//todo end class FileFrom1CCommitPay




        //todo  buness logic Fragment List Commit Pay
    }//todo  buness logic Fragment List Commit Pay


    // TODO: 08.11.2023  класс не посредственого выоления операции cancel или  OK 
    @SuppressLint("SuspiciousIndentation")
    class ProccesingCancelOrOKPay {
        // TODO: 08.11.2023 метод

        void proccerCancelOrOKPay(@NonNull Intent заданиеНаВыполение,@NonNull View v) {
            try{
            final StringBuffer[] ОТветОт1СОперациисДанными = {new StringBuffer()};
                // TODO: 14.11.2023  ОПРАВЛЯЕМ ОТКАЗ иилиОК  в сошгоасованиеию СОГЛАСОВПНИЕ ИЛИ ОТКАЗ
            Maybe. fromAction(new Action() {
                        @Override
                        public void run() throws Throwable {
                              ОТветОт1СОперациисДанными[0] =
                                    binderСогласования1C.getService().
                                            МетодЗапускаСогласованияВнутриСлужбы(заданиеНаВыполение, getContext());


                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ОТветОт1СОперациисДанными " + ОТветОт1СОперациисДанными[0]);

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ОТветОт1СОперациисДанными " + ОТветОт1СОперациисДанными[0]);


                        }
                    }).subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new MaybeObserver<Object>() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ОТветОт1СОперациисДанными " + ОТветОт1СОперациисДанными[0]);

                        }

                        @Override
                        public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Object o) {
                               onComplete();
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ОТветОт1СОперациисДанными " + ОТветОт1СОперациисДанными[0]);

                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ОТветОт1СОперациисДанными " + ОТветОт1СОперациисДанными[0]);

                        }

                        @Override
                        public void onComplete() {

                            if (ОТветОт1СОперациисДанными[0].length()>0) {
                                // TODO: 08.11.2023 после успешно операции перепоудчаем даные  1с Сограсование
                                onStart();

                            }else{
                                Toast.makeText(getActivity(), "Операция  не прошла !!! "    , Toast.LENGTH_SHORT).show();
                                Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                            }
                            // TODO: 26.12.2022  конец основгого кода
                            v.animate().rotationX(0);

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " ОТветОт1СОперациисДанными[0] " + ОТветОт1СОперациисДанными[0]);
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        }


        // TODO: 10.11.2023 get Binary
        @CheckResult
        byte[] proccerGetBinaty1c(@NonNull Intent заданиеНаВыполение,@NonNull View v) {
            byte[] getFileNewOt1cPayCommit = null;
            try{
            // TODO: 10.11.2023 получаем файл от 1с Соглосования Binaty
            getFileNewOt1cPayCommit =
                    binderСогласования1C.getService().
                            МетодПолучаемNewFile1CСогласованияЧерезСлужбу(заданиеНаВыполение, getContext());


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + "\n" + "   getFileNewOt1cPayCommit " +   getFileNewOt1cPayCommit);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ОgetFileNewOt1cPayCommit[0] " + getFileNewOt1cPayCommit);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
            return    getFileNewOt1cPayCommit ;
        }

















        // TODO: 10.11.2023  класс Самой вставки файлов от 1с
        class AddFilesot1CPaycommitting{
     
            void addfilessot1CPaycommitting(@NotNull TableLayout tableLayoutcommitpayfiles,@NonNull JsonNode jsonNode1сСогласованияRow,
                                            @NonNull SubClassФрагмент1Согласование.MyViewHolder holder){
                try{
                if (tableLayoutcommitpayfiles.getChildCount() == 0) {

                    // TODO: 10.11.2023  Заполняем ДАнные Из Массива Файлов
                    ArrayNode datasetArray = (ArrayNode)jsonNode1сСогласованияRow.get("filenames");

                    datasetArray.forEach(new Consumer<JsonNode>() {
                        @Override
                        public void accept(JsonNode jsonNodeМассивФайлы1cBinaty) {


                            // TODO: 10.11.2023 ццикл массив  крутим файцлы вставки
                            ArrayFileNewPay1c(      jsonNodeМассивФайлы1cBinaty,  tableLayoutcommitpayfiles,holder);

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                    " jsonNode1сСогласованияAllRows " +jsonNode1сСогласованияAllRows);
                        }
                    });

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                            " jsonNode1сСогласованияAllRows " +jsonNode1сСогласованияAllRows);

                } else {

                    tableLayoutcommitpayfiles.refreshDrawableState();
                    tableLayoutcommitpayfiles.requestLayout();

                }
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " jsonNode1сСогласования " +jsonNode1сСогласованияAllRows);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }


            // TODO: 10.11.2023  класс крутим файлы от 1с
            void  ArrayFileNewPay1c(@NotNull  JsonNode      МассивИменСограсований,
                                    @NonNull TableLayout tableLayoutcommitpayfiles,
                                    @NonNull SubClassФрагмент1Согласование.MyViewHolder holder){
               try{


                   String НазваниеТекущегот1С    = МассивИменСограсований.get("ВinNameFile").asText().trim();
                   String РасширенияФайла=   МассивИменСограсований.get("expansion").asText().trim();

                           // TODO: 10.11.2023  добалвем новую строчку
                       AddFileFromPayCommiting addFileFromPayCommiting=new AddFileFromPayCommiting();

                           addFileFromPayCommiting.addingNewFilePay(tableLayoutcommitpayfiles,getContext(),НазваниеТекущегот1С,РасширенияФайла,holder);

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " jsonNode1сСогласованияAllRows " +jsonNode1сСогласованияAllRows+ " НазваниеТекущегот1С " +НазваниеТекущегот1С+" РасширенияФайла " +РасширенияФайла);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
                
            }








/////todo END CLASS  class AddFilesot1CPaycommitting
        }/////todo END CLASS  class AddFilesot1CPaycommitting


        // TODO: 08.11.2023 ЗАПОЛЕНИЕ ФАЛОМИ


        class AddFileFromPayCommiting{
            // TODO: 08.11.2023 метод додабвление файлов от 1с
            void   addingNewFilePay(@NotNull  TableLayout tableLayoutcommitpayfiles,
                                    @NotNull Context context,
                                    @NotNull String НазваниеТекущегот1С,
                                    @NotNull String РасширенияФайла,
                                    @NonNull SubClassФрагмент1Согласование.MyViewHolder holder){
                try{
                    // TODO: 03.11.2023 Childern
                    MaterialCardView materialCardViewRowpaycommit = (MaterialCardView) LayoutInflater.from(context).inflate(R.layout.simpleforfileaycommit3, null);

                    // TODO: 03.11.2023 Childern
                    TableLayout tablelayoutRowpaycommit = materialCardViewRowpaycommit.findViewById(R.id.tablelayoutRowpaycommit);


                    TableRow tableRowpaycommit = (TableRow) tablelayoutRowpaycommit.findViewById(R.id.tableRowpaycommit);



                    // TODO: 03.11.2023 Set Datas NEW
                    metodAddTExtView1cPayCommit(НазваниеТекущегот1С, tablelayoutRowpaycommit, tableRowpaycommit,РасширенияФайла);

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                            " НазваниеТекущегот1С " +НазваниеТекущегот1С+" РасширенияФайла " +РасширенияФайла);

                    // TODO: 10.11.2023  меняем Дизан textview FILE
                    metodAddTExtViewChengeDisainPayCommit(tableRowpaycommit);


                    // TODO: 10.11.2023  Клик по TExtView  FIle
                    metodClicksForTextViewPayCommit(tableRowpaycommit,holder);



                    // TODO: 03.11.2023 Delete Datas
                    tablelayoutRowpaycommit.recomputeViewAttributes(tableRowpaycommit);
                    tablelayoutRowpaycommit.removeViewInLayout(tableRowpaycommit);
                    tablelayoutRowpaycommit.removeView(tableRowpaycommit);
                    tableRowpaycommit.setId(new Random().nextInt());
                    tablelayoutRowpaycommit.recomputeViewAttributes(tableRowpaycommit);

                    // TODO: 03.11.2023 Set Animaziy
                     tableRowpaycommit.startAnimation(animation1);

                    // TODO: 03.11.2023 Final Add Row in Parent Tableyout
                    metodParentAddRowFinal(tableRowpaycommit, tableLayoutcommitpayfiles);



                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                            " НазваниеТекущегот1С " +НазваниеТекущегот1С);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            private void metodParentAddRowFinal(TableRow rowПервыеДанные,
                                                @NonNull TableLayout tableLayoutРодительская) {
                try {
                    tableLayoutРодительская.removeView(rowПервыеДанные);
                    tableLayoutРодительская.removeViewInLayout(rowПервыеДанные);
                    tableLayoutРодительская.addView(rowПервыеДанные);
                    tableLayoutРодительская.requestLayout();
                    tableLayoutРодительская.refreshDrawableState();

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
        }//todo end classs class AddFileFromPayCommiting

        private void metodAddTExtView1cPayCommit(@NonNull String НазваниеТекущегот1С,
                                                 @NonNull TableLayout tablelayoutRowpaycommit,
                                                 @NonNull TableRow tableRowpaycommit,
                                                 @NotNull String РасширенияФайла) {
            try{
            TextView textnameRowpaycommit = tableRowpaycommit.findViewById(R.id.textnameRowpaycommit);
            TextView textvalueRowpaycommit = tableRowpaycommit.findViewById(R.id.textvalueRowpaycommit);

            // TODO: 10.11.2023 добалвем назваение файла
            textvalueRowpaycommit.setText(НазваниеТекущегот1С.trim());
                textvalueRowpaycommit.setTooltipText(НазваниеТекущегот1С.trim());
                // TODO: 13.11.2023 название расфирения
                textnameRowpaycommit.setHint("Файл "+"("+РасширенияФайла+")");
                textnameRowpaycommit.setTooltipText(РасширенияФайла.trim());

            // TODO: 03.11.2023 Tag
            Bundle bundleChildreRow = new Bundle();
            bundleChildreRow.putString("ВinNameFile", НазваниеТекущегот1С.trim());
            bundleChildreRow.putString("expansion", РасширенияФайла.trim());

            tablelayoutRowpaycommit.setTag(bundleChildreRow);
            textvalueRowpaycommit.setTag(bundleChildreRow);
            textnameRowpaycommit.setTag(bundleChildreRow);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }
        // TODO: 10.11.2023 меняем дизацйн ноых файлов

        private void metodAddTExtViewChengeDisainPayCommit(@NonNull TableRow tableRowpaycommit) {
            try{
                TextView textnameRowpaycommit = tableRowpaycommit.findViewById(R.id.textnameRowpaycommit);
                TextView textvalueRowpaycommit = tableRowpaycommit.findViewById(R.id.textvalueRowpaycommit);

            //    textvalueRowpaycommit.setTextColor(Color.parseColor("#787070"));

                textvalueRowpaycommit.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        textvalueRowpaycommit.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int height =  textvalueRowpaycommit.getHeight();
                        int width =  textvalueRowpaycommit.getWidth();
                        textnameRowpaycommit.setHeight( height);
                        textnameRowpaycommit.requestLayout();
                        textnameRowpaycommit.forceLayout();



                        String s=textvalueRowpaycommit.getText().toString();
                        SpannableString ss=new SpannableString(s);
                        ss.setSpan(new UnderlineSpan(), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        textvalueRowpaycommit.setText(ss);

                        textvalueRowpaycommit.startAnimation(animation1);


                    }
                });




                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 11.11.2023  програсс бакр при загрузке файла 1с согооасования

        @UiThread
        private void metodPrograssbarDowloadFile1cPayCommit(@NonNull SubClassФрагмент1Согласование.MyViewHolder holder,
                                                            @NotNull Boolean Flag ) {
            try{

                if (Flag) {
                        holder.progressbarfilepay.setVisibility(View.VISIBLE);
                } else {
                        holder.progressbarfilepay.setVisibility(View.INVISIBLE);
                }
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 10.11.2023  метод  клик по файлва Для Отправки

        private void metodClicksForTextViewPayCommit(@NonNull TableRow tableRowpaycommit,
                                                     @NonNull SubClassФрагмент1Согласование.MyViewHolder holder) {
            try{

                TextView textnameRowpaycommit = tableRowpaycommit.findViewById(R.id.textnameRowpaycommit);
                TextView textvalueRowpaycommit = tableRowpaycommit.findViewById(R.id.textvalueRowpaycommit);
                // TODO: 10.11.2023 клик по файлов
                 RxView.clicks(textvalueRowpaycommit)
                        .throttleFirst(5,TimeUnit.SECONDS)
                        .filter(s -> !s.toString().isEmpty())
                        .map(new Function<Unit, Object>() {
                            @Override
                            public Object apply(Unit unit) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        +"  s.toString(); " + unit.toString());
                                return textvalueRowpaycommit.getText().toString();
                            }
                        })
                  .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                      @Override
                      public void accept(Throwable throwable) throws Throwable {
                          throwable.printStackTrace();
                          Log.e(getContext().getClass().getName(),
                                  "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                          " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                          new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                  this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                  Thread.currentThread().getStackTrace()[2].getLineNumber());
                      }
                  })
                  .onErrorComplete(new Predicate<Throwable>() {
                      @Override
                      public boolean test(Throwable throwable) throws Throwable {
                          throwable.printStackTrace();
                          Log.e(getContext().getClass().getName(),
                                  "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                          " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                          new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                  this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                  Thread.currentThread().getStackTrace()[2].getLineNumber());
                          return false;
                      }
                  })
                        .subscribe( GetNameSingleNewFile1c->{

                            metodPrograssbarDowloadFile1cPayCommit(holder,true );
                            tableRowpaycommit.startAnimation(animation1);

                            handler.postDelayed(()->{
                                try{

                                  metodCompleteStartDownloadfILE1C(holder, textvalueRowpaycommit, GetNameSingleNewFile1c);

                                metodPrograssbarDowloadFile1cPayCommit(holder,false );
                                tableRowpaycommit.clearAnimation();
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(getContext().getClass().getName(),
                                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            },250);




                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                        });


                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 13.11.2023 метод Загрузки Старта Новго Файла От 1с Complete 
        private void metodCompleteStartDownloadfILE1C(@NonNull SubClassФрагмент1Согласование. MyViewHolder holder,
                                                      @NonNull  TextView textvalueRowpaycommit,
                                                      @NonNull Object GetNameSingleNewFile1c) {
            try{
                final byte[][] getFileNewOt1cPayCommit = {null};
                Completable.fromSupplier(new Supplier<byte[]>() {
                            @Override
                            public byte[] get() throws Throwable {

                                getFileNewOt1cPayCommit[0] =     metodSubscrionGets1cСограсование(textvalueRowpaycommit, GetNameSingleNewFile1c.toString(),holder);

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        +"  GetNameSingleNewFile1c " + GetNameSingleNewFile1c +" getFileNewOt1cPayCommit " + getFileNewOt1cPayCommit[0]);

                                return getFileNewOt1cPayCommit[0];
                            }
                        })
                        .blockingSubscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                                       Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                       v2.vibrate(VibrationEffect.createOneShot(80, VibrationEffect.EFFECT_HEAVY_CLICK));

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        +"  GetNameSingleNewFile1c " + GetNameSingleNewFile1c +" время " + new Date().toLocaleString());
                            }

                            @Override
                            public void onComplete() {
                                // TODO: 13.11.2023  после получениеоперацйии получение файла показыаем програсс баром



                                // TODO: 13.11.2023 МетодЗапускает Если Файл Есть Пднимаем Его Птом
                                reakziyHaSizeFile1cPayCommi(getFileNewOt1cPayCommit[0]);

                                 // TODO: 13.11.2023 ЗАгружаем ФАйл Н адИСк
                                        SuccessGet1CpayCommitProccesing successGet1CpayCommitProccesing =
                                                new SuccessGet1CpayCommitProccesing();

                                String НазваниеТекущегОт1С=       successGet1CpayCommitProccesing.
                                        filesuccessDownDisk1CpayCommitProccesing(getFileNewOt1cPayCommit[0],textvalueRowpaycommit);


                                // TODO: 14.11.2023 Поднимаем Файл с Диска И Показываем Его Пользователю
                                metodUpsAllsFileOt1cUserS(НазваниеТекущегОт1С,(Bundle) textvalueRowpaycommit.getTag());


                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        +"  GetNameSingleNewFile1c " + GetNameSingleNewFile1c +" getFileNewOt1cPayCommit " + getFileNewOt1cPayCommit[0]+
                                        " НазваниеТекущегОт1С " +НазваниеТекущегОт1С);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        +"  GetNameSingleNewFile1c " + GetNameSingleNewFile1c +" время " + new Date().toLocaleString());
                            }
                        });

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




        private void metodUpsAllsFileOt1cUserS(@NonNull  String NameNewDownloadFileOt1c,@NonNull   Bundle bundleChildreRow) {
            // TODO: 14.11.2023 ПОказываем файл пользоватолю
            try {
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        +"  NameNewDownloadFileOt1c " + NameNewDownloadFileOt1c );

                if (NameNewDownloadFileOt1c !=null) {

                    String РасширенияФайлаОт1С = bundleChildreRow.getString("expansion", "");
                    switch (РасширенияФайлаОт1С.trim()){
                        case "xlsx":
                        case "xltx":
                        case "xlsm":
                            class UpFileXlsx extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    //ТекущийФорматДокумента="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                                    ТекущийФорматДокумента="application/vnd.ms-excel";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFileXlsx().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;

                        case "xls":
                        case "xla":
                        case "xlt":
                            class UpFileXlsxSimple extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    ТекущийФорматДокумента="application/vnd.ms-excel";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFileXlsxSimple().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;
                        case "mdb":
                        case "accdb":
                            class UpFileAccess extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    ТекущийФорматДокумента="application/vnd.ms-access";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFileAccess().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;
                        case "ppt":
                        case "pot":
                        case "pps":
                            class UpFilePowerPoint extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    ТекущийФорматДокумента="application/vnd.ms-powerpoint";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFilePowerPoint().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;

                        case "docx":
                        case "dotx":
                        case "docm":
                            class UpFileWord extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    ТекущийФорматДокумента="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFileWord().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;
                        case "doc":
                        case "dot":
                            class UpFileWordSimple extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    ТекущийФорматДокумента="application/msword";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFileWordSimple().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;
                        case "jpg":
                            class UpFileJPG extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    ТекущийФорматДокумента="image/jpg";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFileJPG().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;
                        case "jpeg":
                            class UpFileJPEG extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    ТекущийФорматДокумента="image/jpeg";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFileJPEG().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;
                        case "png":
                            class UpFilePNG extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    ТекущийФорматДокумента="image/png";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFilePNG().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;
                        case "pdf":
                            class UpFilePDF extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    ТекущийФорматДокумента="application/pdf";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFilePDF().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;
                        case "zip":
                            class UpFileZIP extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    ТекущийФорматДокумента="application/zip";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFileZIP().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;
                        case "rar":
                            class UpFileRAR extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    ТекущийФорматДокумента="application/vnd.rar";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFileRAR().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;
                        case "txt":
                            class UpFileTXT extends SuccessGet1CpayCommitProccesing{
                                @Override
                                void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С) {
                                    ТекущийФорматДокумента="text/*";
                                    super.UpFileSuccessOt1cPayCommit(НазваниеТекущегОт1С);
                                }
                            }
                            // TODO: 14.11.2023 staring childer class up file
                            new UpFileTXT().UpFileSuccessOt1cPayCommit(NameNewDownloadFileOt1c);
                            break;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }





        // TODO: 10.11.2023 Метод Подписка Subcrion Получение файла от 1с Сограсование
        private byte[] metodSubscrionGets1cСограсование(@NotNull TextView textvalueRowpaycommit,
                                                      @NotNull Object GetNameSingleNewFile1c,
                                                        @NonNull SubClassФрагмент1Согласование. MyViewHolder holder) {
            byte[] getFileNewOt1cPayCommit=null;
            try{
                // TODO: 26.12.2022  конец основгого кода
                Intent заданиеGetNewFile1C=new Intent();
                // TODO: 17.11.2021
                заданиеGetNewFile1C.setAction( "ЗапускаемПолучениеBinatyОт1с");
                Bundle bundleДляПередачиВСлужбуСогласования=(Bundle)   textvalueRowpaycommit.getTag();

                bundleДляПередачиВСлужбуСогласования.putInt("PROCESS_IDСогласования", Integer.parseInt("28"));
                bundleДляПередачиВСлужбуСогласования.putString("backfile", GetNameSingleNewFile1c.toString().trim() );
                bundleДляПередачиВСлужбуСогласования.putInt("ПередаемСтатусСогласования", 4);///TODO выполнил Согласования
                bundleДляПередачиВСлужбуСогласования.putInt("ПубличныйidPay", ПубличныйidPay);//ПубличныйidPay

                // TODO: 13.11.2023  номер документа
                 String dsu1number=      holder.textView1.getTag().toString().trim();


                bundleДляПередачиВСлужбуСогласования.putString("dsu1number",  dsu1number);//ПубличныйidPay

                заданиеGetNewFile1C.putExtras(bundleДляПередачиВСлужбуСогласования);

                  textvalueRowpaycommit.setTag(bundleДляПередачиВСлужбуСогласования);

                ///TODO выполнил ОТКАЗ canxel
                ProccesingCancelOrOKPay proccesingCancelOrOKPay=new ProccesingCancelOrOKPay();

                 getFileNewOt1cPayCommit = proccesingCancelOrOKPay.proccerGetBinaty1c(заданиеGetNewFile1C, textvalueRowpaycommit);

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        +"  getFileNewOt1cPayCommit " + getFileNewOt1cPayCommit+" время " + new Date().toLocaleString());
                // TODO: 26.12.2022  конец основгого кода
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
            return  getFileNewOt1cPayCommit;
        }



        void reakziyHaSizeFile1cPayCommi(@NotNull byte[] getFileNewOt1cPayCommit){
           try{
            if (getFileNewOt1cPayCommit!=null) {
                if (getFileNewOt1cPayCommit.length>0) {
                    // TODO: 08.11.2023 после успешно операции перепоудчаем даные  1с Сограсование


                }else{
                    Toast.makeText(getActivity(), "Нет файла 1С !!!"    , Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(getActivity(), "Нет файла 1С !!!"    , Toast.LENGTH_SHORT).show();
            }

            // TODO: 26.12.2022  конец основгого кода
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }




    }





    //TODO класс работает когда файл от 1 с пришел
    class  SuccessGet1CpayCommitProccesing{
      protected String ТекущийФорматДокумента=null;
        String filesuccessDownDisk1CpayCommitProccesing(@NotNull byte[] getFileNewOt1cPayCommit,@NotNull TextView textvalueRowpaycommit){
            String NameNewDownloadFileOt1c=null;
            try{
                if (getFileNewOt1cPayCommit!=null) {
                    if (getFileNewOt1cPayCommit.length > 0) {

                        // TODO: 03.11.2023 Tag
                        Bundle bundleChildreRow = (Bundle) textvalueRowpaycommit.getTag();
                    String    НазваниеТекущегОт1С = bundleChildreRow.getString("ВinNameFile", "");
                        String РасширенияФайлаОт1С = bundleChildreRow.getString("expansion", "");


                        if (! НазваниеТекущегОт1С.isEmpty() &&
                                ! РасширенияФайлаОт1С.isEmpty()) {
                            String patchFileName = "SousAvtoFile/AppCommitPays1C/Photos";
                            NameNewDownloadFileOt1c= НазваниеТекущегОт1С.toString() + "." + РасширенияФайлаОт1С;

                            File fileNewPhotoDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                                    + File.separator + patchFileName);

                            File fileNewPhotoFromCameraX = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                                    + File.separator + patchFileName + File.separator + NameNewDownloadFileOt1c);

                            if (fileNewPhotoDirectory.isDirectory() && !fileNewPhotoFromCameraX.exists()) {
                                Uri address = FileProvider.getUriForFile(getContext(), "com.dsy.dsu.provider", fileNewPhotoFromCameraX);
                                ContentResolver contentResolver = getContext().getContentResolver();
                                OutputStream out = contentResolver.openOutputStream(address);
                                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out, 2048);//4096//2048
                                bufferedOutputStream.write(getFileNewOt1cPayCommit);
                                // TODO: 03.10.2023
                                bufferedOutputStream.flush();
                                bufferedOutputStream.close();
                                out.flush();
                                out.close();
                            }

                            // TODO: 14.11.2023
                            if ( !fileNewPhotoFromCameraX.exists()) {
                                NameNewDownloadFileOt1c=null;
                            }
                        }



                        // TODO: 26.12.2022  конец основгого кода
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "getFileNewOt1cPayCommit" +
                                getFileNewOt1cPayCommit + "  textvalueRowpaycommit" + textvalueRowpaycommit);

                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
            return  NameNewDownloadFileOt1c;
        }

        void UpFileSuccessOt1cPayCommit(@NonNull String НазваниеТекущегОт1С){
            try{
                String patchFileName = "SousAvtoFile/AppCommitPays1C/Photos";
                File UpFileOt1cPay = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        + File.separator + patchFileName + File.separator + НазваниеТекущегОт1С);
                // TODO: 03.11.2023 Tag
                if (UpFileOt1cPay.exists()) {

                    Uri address = FileProvider.getUriForFile(getContext(), "com.dsy.dsu.provider", UpFileOt1cPay);

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                   // intent.setDataAndType(address, "text/*");
                    intent.setDataAndType(address, ТекущийФорматДокумента);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent); // Crashes on this line





                    //  File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), НазваниеТекущегОт1С);
                   /* Uri uri = Uri.fromFile(UpFileOt1cPay);
                    Intent intentOpenFile = new Intent(Intent.ACTION_VIEW);
                    intentOpenFile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intentOpenFile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //  intentOpenFile.setType("application/pdf");
                    // intentOpenFile.setDataAndType(uri, "application/vnd.ms-excel");
                    //intentOpenFile.setType( ТекущийФорматДокумента);
                    intentOpenFile.setDataAndType( uri,ТекущийФорматДокумента);
                    intentOpenFile.putExtra(Intent.EXTRA_STREAM, uri);
                    intentOpenFile.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intentOpenFile.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intentOpenFile);*/
                }

                // TODO: 26.12.2022  конец основгого кода
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                " НазваниеТекущегОт1С " + НазваниеТекущегОт1С);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



//TODO  END    class  SuccessGet1CpayCommitProccesing{
    }//TODO  END    class  SuccessGet1CpayCommitProccesing{





    //TODO класс SerachView поски по Соглоаваниям

    class AdapterSerachViewPay{
        private     SearchView searchview_commitpay;

        public AdapterSerachViewPay(SearchView searchview_commitpay) {
            this.searchview_commitpay = searchview_commitpay;
        }

        void  setAdapterSerachViewPay (){
            // TODO: 21.11.2023 Посик
            try{
                if(searchview_commitpay.isEnabled()){
                    searchview_commitpay.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (hasFocus==true) {
                                ((SearchView) v).setQueryHint("Поиск...");
                            }else {
                                ((SearchView) v).setQueryHint(null);
                                BisnesLogica1Согласование.методRebootDisaynRecyreViewonStopOrAsync(jsonNode1сСогласованияAllRows);
                                BisnesLogica1Согласование. МетодПерегрузкаRecyceView();
                                // TODO: 24.11.2023
                                BisnesLogica1Согласование.  методЗакрываемКлавитатуру( );
                            }
                            // TODO: 26.12.2022  конец основгого кода
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                    " searchview_commitpay " + searchview_commitpay);
                        }
                    });
// TODO: 21.11.2023
                    searchview_commitpay.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            // TODO: 24.11.2023
                            Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                            v2.vibrate(VibrationEffect.createOneShot(60, VibrationEffect.EFFECT_HEAVY_CLICK));

                            RunningSearchView runningSearchView=new RunningSearchView( jsonNode1сСогласованияAllRows,query);

                            runningSearchView.   registerBroadCastRexiver ();

                            runningSearchView.startrunningSearchView();

                            // TODO: 26.12.2022  конец основгого кода
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                    " searchview_commitpay " + searchview_commitpay);
                            return true;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {

                            if (newText!=null && newText.length()>0) {
                                onQueryTextSubmit(newText);
                                // TODO: 26.12.2022  конец основгого кода
                                Log.d(this.getClass().getName(), "\n" + " class " +
                                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                        " searchview_commitpay " + searchview_commitpay);
                                return true;
                            }else {
                                // TODO: 26.12.2022  конец основгого кода
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                        " searchview_commitpay " + searchview_commitpay);
                                return false;
                            }

                        }
                    });

                }
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        " searchview_commitpay " + searchview_commitpay);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }






                }//TODO end onSubscribe


        }
// TODO: 21.11.2023  Класа Бизнес ЛОгики  ПОсика При НАжатии

        class RunningSearchView {
       JsonNode jsonNode1сСогласованияAllRows;
            String query;

            public RunningSearchView(@NotNull JsonNode jsonNode1сСогласованияAllRows,String query) {
                this.jsonNode1сСогласованияAllRows = jsonNode1сСогласованияAllRows;
                this.query = query;
            }

           protected void   startrunningSearchView (){
               if (jsonNode1сСогласованияAllRows!=null) {
                   intentsendJsonNodeToService = new Intent("GetSerachJsonCommitPay");
                   intentsendJsonNodeToService.setClass(getContext(),ServiceForCommitPay.class);
                   Bundle bundle=new Bundle();
                   bundle.putSerializable("JsonNode",(Serializable) jsonNode1сСогласованияAllRows);
                   bundle.putString("query",  query);
                   intentsendJsonNodeToService.putExtras(bundle);
               }
               getContext(). startService(intentsendJsonNodeToService);
               // TODO: 26.12.2022  конец основгого кода
               Log.d(this.getClass().getName(), "\n" + " class " +
                       Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
               // TODO: 26.12.2022  конец основгого кода
               Log.d(this.getClass().getName(), "\n" + " class " +
                       Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

            }

            // TODO: 23.11.2023 BroadCast
            protected void  registerBroadCastRexiver (){
                try{
                BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        // Get Пришли ДАнные из службы после посика
                        Bundle bundleGetOtService=         intent.getExtras();
                        Integer КоличествоСтрокПослеПосика=   bundleGetOtService.getInt("message");
                        if (КоличествоСтрокПослеПосика>0) {
                            // TODO: 24.11.2023
                            JsonNode  jsonNode1сСогласованияCallBackService=
                                    (JsonNode)       bundleGetOtService.getSerializable("callbacksearchjsonnode");

                            // TODO: 24.11.2023 reboot Recyreview
                            metodCallBackRebootRecyreView(jsonNode1сСогласованияCallBackService);

                     /*       // TODO: 24.11.2023
                            BisnesLogica1Согласование.   методЗакрываемКлавитатуру( );*/


                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    +"КоличествоСтрокПослеПосика " +КоличествоСтрокПослеПосика);

                        }else {
// TODO: 24.11.2023  меняем дизайн посика когда нет данных
                            metodDontSearchChangeDisaySearch();

                        }
                        // TODO: 24.11.2023 закрывам Служба
                        metodCloseLocalBroastCast(intent  );
                        // TODO: 26.12.2022  конец основгого кода
                        Log.d(context.getClass().getName(), "\n" + " class "
                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    }
                };
                LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver,
                        new IntentFilter("custom-event-name"));

                // TODO: 26.12.2022  конец основгого кода
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                // TODO: 26.12.2022  конец основгого кода
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }

            private void metodCloseLocalBroastCast(@NonNull  Intent intent) {
 try{
                getContext().stopService(intentsendJsonNodeToService);
                LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(null);
                // TODO: 26.12.2022  конец основгого кода
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }


            private void metodDontSearchChangeDisaySearch( ) {
    try{
                int id =  searchview_commitpay.getContext()
                        .getResources()
                        .getIdentifier("android:id/search_src_text", null, null);
                final TextView[] textView = {(TextView) searchview_commitpay.findViewById(id)};
                textView[0].setTextColor(Color.RED);
                textView[0].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));


                handler.postDelayed(() -> {

                    searchview_commitpay.getContext()
                            .getResources()
                            .getIdentifier("android:id/search_src_text", null, null);
                    textView[0] = (TextView) searchview_commitpay.findViewById(id);
                    textView[0].setTextColor(Color.BLACK);
                    textView[0].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    // TODO: 24.11.2023
                }, 500);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }






            // TODO: 24.11.2023 callback
            private void metodCallBackRebootRecyreView(@NonNull    JsonNode  jsonNode1сСогласованияCallBackService) {
                                            try{
                if(jsonNode1сСогласованияCallBackService.size()>0 &&
                        jsonNode1сСогласованияCallBackService.size()<jsonNode1сСогласованияAllRows.size()){
                    // TODO: 24.11.2023
                    BisnesLogica1Согласование.   методClearRecyreView();
                    BisnesLogica1Согласование.методRebootDisaynRecyreViewonStopOrAsync(jsonNode1сСогласованияCallBackService);
                    BisnesLogica1Согласование. МетодПерегрузкаRecyceView();
                }
                // TODO: 26.12.2022  конец основгого кода
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " jsonNode1сСогласованияCallBackService " +jsonNode1сСогласованияCallBackService);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }




        }


}






























