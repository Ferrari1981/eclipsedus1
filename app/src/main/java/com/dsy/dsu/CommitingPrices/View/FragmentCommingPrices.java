package com.dsy.dsu.CommitingPrices.View;

import android.content.Context;
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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.dsy.dsu.CommitingPrices.Model.startingViewModels.StartingLiveDataJsonByte;
import com.dsy.dsu.CommitingPrices.Model.startingViewModels.StartingLiveDataJsonString;
import com.dsy.dsu.CommitingPrices.ViewModel.ModelComminingPrisesByte;
import com.dsy.dsu.CommitingPrices.ViewModel.ModelComminingPrisesString;
import com.dsy.dsu.CommitsPayments.Fragment1_List_CommitPay;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragmentCommingPrices extends Fragment {

    @Inject
    ObjectMapper getHiltJaksonObjectMapper;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private LifecycleOwner lifecycleOwner;

    private RecyclerView recycleview_comminingpprices;
    private  BiznesLogicainnerFragment biznesLogicainnerFragment;


    private BiznesLogicainnerFragment.MyRecycleViewIsNullAdapter myRecycleViewIsNullAdapter;
    private BiznesLogicainnerFragment.MyViewHolder myViewHolder;

    private Animation animationДляСогласования;


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



            // TODO: 26.12.2023
            biznesLogicainnerFragment.startingInsertRecyreViewIsNull();
            biznesLogicainnerFragment.  МетодИнициализацииRecycleViewДляЗадач();

            recycleview_comminingpprices.requestLayout();
            recycleview_comminingpprices.refreshDrawableState();

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
    class BiznesLogicainnerFragment{


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
     private void getmodelByte() {
         try{
             // ModelComminingPrisesString modelComminingPrisesString =((MainActivityCommitingPrices)getActivity()).modelComminingPrisesString;
             ModelComminingPrisesByte modelComminingPrisesByte =((MainActivityCommitingPrices)getActivity()).modelComminingPrisesByte;


             // TODO: 25.12.2023  предварительный код  получение данныз от 1с
             // TODO: 25.12.2023  получение данныз от 1с согласования цен String
             StartingLiveDataJsonByte startingLiveDataJsonByte =new StartingLiveDataJsonByte(modelComminingPrisesByte,getContext());
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


        void startingInsertRecyreViewIsNull() {
            try {

                if (myRecycleViewIsNullAdapter==null) {
                    ArrayList<String>arrayListIsNull1cData=new ArrayList<>();
                    arrayListIsNull1cData.add("IsNull1cPayCommit");
                    myRecycleViewIsNullAdapter = new MyRecycleViewIsNullAdapter(arrayListIsNull1cData,getContext());
                    myRecycleViewIsNullAdapter.notifyDataSetChanged();
                    recycleview_comminingpprices.setAdapter(myRecycleViewIsNullAdapter);
                    recycleview_comminingpprices.getAdapter().notifyDataSetChanged();
                }
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"myRecycleViewIsNullAdapter  "
                        + myRecycleViewIsNullAdapter);


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






















        // TODO: 27.12.2023 Recyreview is null

        // TODO: 09.11.2023 ВТОРОЯ Rereview
        class MyRecycleViewIsNullAdapter extends RecyclerView.Adapter<MyViewHolder> {
            private ArrayList<String> arrayListIsNull1cData=new ArrayList<>();
            Context context;
            public MyRecycleViewIsNullAdapter(@NotNull ArrayList<String>arrayListIsNull1cData,@NotNull Context context) {
                // super();
                this.arrayListIsNull1cData = arrayListIsNull1cData;
                this.context = context;
                Log.i(this.getClass().getName(), " arrayListIsNull1cData.size() " + arrayListIsNull1cData.size());

            }


            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, @NonNull int position, @NonNull List<Object> payloads) {
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


                    ///if (asyncTaskLoader.isStarted() ) {
                        viewГлавныйВидДляRecyclleViewДляСогласованияISNull = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.simple_for_commitpay_cardview1empty_in_prossering, parent, false);


                   // }else {
                        viewГлавныйВидДляRecyclleViewДляСогласованияISNull = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.simple_for_commitpay_dont_jsonot1c, parent, false);

                   // }

                    // TODO: 22.03.2022
                    myViewHolder = new MyViewHolder(viewГлавныйВидДляRecyclleViewДляСогласованияISNull,getContext());
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


        // TODO: 27.12.2023 VIEWhoder

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
            Context context;

            // TODO: 02.03.2022
            public MyViewHolder(@NonNull View itemView,@NotNull Context context) {
                super(itemView);
                try{
                    this.context=context;
                    // TODO: 02.03.2022
                    МетодИнициализацииКомпонетовЗаданияCardView(itemView);
                    // TODO: 01.03.2022
                    Log.d(this.getClass().getName(), "  private class MyViewHolderДляЧата extends RecyclerView.ViewHolder  itemView   " + itemView);
                } catch (Exception e) {
                    e.printStackTrace();
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
                    //if ( asyncTaskLoader!=null &&  asyncTaskLoader.isReset()) {
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
                   // }

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








    }///todo  class BiznesLogicainnerFragment

}