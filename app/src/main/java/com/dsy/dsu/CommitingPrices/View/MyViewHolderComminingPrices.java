package com.dsy.dsu.CommitingPrices.View;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;


public class MyViewHolderComminingPrices extends RecyclerView.ViewHolder {
    // TODO: 28.02.2022
    private TextView textView1, textView2, textView3, textView4, textView5Намеклатура, textorganizationvalue, textvalueSUM;
    private      TextView textViewКонтрагент,textViewЦФО,textViewДДС,TextViewНамелклатура;
    private MaterialCardView cardviewmatireacommitpay;
    private MaterialButton КнопкаСогласованиеОтказ,КнопкаУспешноеСогласования;
    private TableLayout tableLayoutcommitpayfiles,tableLayoutcommitpay;
    protected ProgressBar progressbarfilepay;

    private  Context context;

    // TODO: 02.03.2022
    public MyViewHolderComminingPrices(@NonNull View itemView, @NotNull Context context) {
        super(itemView);
        try{
            // TODO: 02.03.2022
            МетодИнициализацииКомпонетовЗаданияCardView(itemView);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 14.03.2022
    private void МетодИнициализацииКомпонетовЗаданияCardView(@NonNull View itemView) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }
}