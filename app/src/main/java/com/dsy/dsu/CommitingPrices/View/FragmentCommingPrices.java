package com.dsy.dsu.CommitingPrices.View;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.dsy.dsu.CommitingPrices.Model.startingViewModels.StartingLiveDataJsonByte;
import com.dsy.dsu.CommitingPrices.Model.startingViewModels.StartingLiveDataJsonString;
import com.dsy.dsu.CommitingPrices.ViewModel.ModelComminingPrisesByte;
import com.dsy.dsu.CommitingPrices.ViewModel.ModelComminingPrisesString;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragmentCommingPrices extends Fragment {

    @Inject
    ObjectMapper getHiltJaksonObjectMapper;

    private RecyclerView recycleview_comminingpprices;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private LifecycleOwner lifecycleOwner;

    private  RecyclerViewProcessorIsNull myRecycleViewIsNullAdapter;

    private  BiznesLogicainnerFragment biznesLogicainnerFragment;

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

   biznesLogicainnerFragment.startingInsertRecyreViewIsNull();

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
                ConcurrentHashMap<String,Boolean> concurrentHashMapIsNull=new ConcurrentHashMap();
                concurrentHashMapIsNull.put("RecyclerViewProcessorIsNull",false);

                myRecycleViewIsNullAdapter = new RecyclerViewProcessorIsNull(concurrentHashMapIsNull );

                    myRecycleViewIsNullAdapter.notifyDataSetChanged();

                    recycleview_comminingpprices.setAdapter(myRecycleViewIsNullAdapter);

                recycleview_comminingpprices.getAdapter().notifyDataSetChanged();

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



    }


    // TODO: 09.11.2023 ВТОРОЯ Rereview
      class RecyclerViewProcessorIsNull extends RecyclerView.Adapter<MyViewHolderComminingPrices> {

        private Context context;
        private   MyViewHolderComminingPrices myViewHolderComminingPrices;
        private ConcurrentHashMap<String,Boolean> concurrentHashMapIsNull ;


        public RecyclerViewProcessorIsNull(@NotNull ConcurrentHashMap<String,Boolean> concurrentHashMapIsNull ) {
            // TODO: 26.12.2023
            try{
                this.  concurrentHashMapIsNull=concurrentHashMapIsNull;
                this.context = context;
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        " concurrentHashMapIsNull " +concurrentHashMapIsNull);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }


        @Override
        public void onBindViewHolder(@NonNull MyViewHolderComminingPrices holder, @NonNull int position, @NonNull List<Object> payloads) {
            try {
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"   onBindViewHolder  position" + position);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            super.onBindViewHolder(holder, position, payloads);
        }







        @Override
        public void setHasStableIds(boolean hasStableIds) {
            super.setHasStableIds(hasStableIds);
        }

        @Override
        public void onViewRecycled(@NonNull MyViewHolderComminingPrices holder) {
            super.onViewRecycled(holder);
        }

        @Override
        public boolean onFailedToRecycleView(@NonNull MyViewHolderComminingPrices holder) {
            // TODO: 03.11.2023 Parent
            return super.onFailedToRecycleView(holder);

        }

        @Override
        public void onViewAttachedToWindow(@NonNull MyViewHolderComminingPrices holder) {
            super.onViewAttachedToWindow(holder);

        }

        @Override
        public void onViewDetachedFromWindow(@NonNull MyViewHolderComminingPrices holder) {
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
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

            return super.getItemViewType(position);
        }


        @NonNull
        @Override
        public MyViewHolderComminingPrices  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View ViewIsNUll = null;
            try {
                Boolean StatusProccessrNull=  concurrentHashMapIsNull.get("RecyclerViewProcessorIsNull");

                if (StatusProccessrNull ) {
                    ViewIsNUll = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.simple_for_commitpay_cardview1empty_in_prossering, parent, false);

                }else {
                    ViewIsNUll = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.simple_for_commitpay_dont_jsonot1c, parent, false);

                }
                // TODO: 22.03.2022 is nnull
                myViewHolderComminingPrices = new MyViewHolderComminingPrices(ViewIsNUll);
                // TODO: 26.12.2023
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + "   ViewIsNUll" + ViewIsNUll + " StatusProccessrNull " +
                        " " +StatusProccessrNull);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return myViewHolderComminingPrices;

        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolderComminingPrices holder, int position) {
            try {

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " position " +position);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
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
            int КоличесвоСтрок=0;
            try{
                // TODO: 26.12.2023 count rows
                КоличесвоСтрок = concurrentHashMapIsNull.size();
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " КоличесвоСтрок " +КоличесвоСтрок);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            // TODO: 28.02.2022
            return КоличесвоСтрок ;
        }
    }
    // TODO: 26.12.2023 END

      class MyViewHolderComminingPrices extends RecyclerView.ViewHolder {
        // TODO: 28.02.2022
        private TextView textView1, textView2, textView3, textView4, textView5Намеклатура, textorganizationvalue, textvalueSUM;
        private      TextView textViewКонтрагент,textViewЦФО,textViewДДС,TextViewНамелклатура;
        private MaterialCardView cardviewmatireacommitpay;
        private MaterialButton КнопкаСогласованиеОтказ,КнопкаУспешноеСогласования;
        private TableLayout tableLayoutcommitpayfiles,tableLayoutcommitpay;
        protected ProgressBar progressbarfilepay;


        // TODO: 02.03.2022
        public MyViewHolderComminingPrices(@NonNull View itemView) {
            super(itemView);
            // TODO: 02.03.2022
            МетодИнициализацииКомпонетовЗаданияCardView(itemView);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        }

        // TODO: 14.03.2022
        private void МетодИнициализацииКомпонетовЗаданияCardView(@NonNull View itemView) {

            // if ( asyncTaskLoader!=null &&  asyncTaskLoader.isReset()) {
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

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


        }
    }



}