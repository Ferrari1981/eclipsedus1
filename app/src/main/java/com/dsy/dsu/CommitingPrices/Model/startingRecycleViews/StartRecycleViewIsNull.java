package com.dsy.dsu.CommitingPrices.Model.startingRecycleViews;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CommitsPayments.Fragment1_List_CommitPay;
import com.dsy.dsu.Errors.Class_Generation_Errors;

import java.util.ArrayList;

public class StartRecycleViewIsNull {
  private   Context context;

    public StartRecycleViewIsNull(Context context) {
        this.context = context;
    }

    void  startingRecycleViewIsNull(){
        try {
           /*     ArrayList<String> arrayListIsNull1cData=new ArrayList<>();
                arrayListIsNull1cData.add("IsNull1cPayCommit");
                myRecycleViewIsNullAdapter = new Fragment1_List_CommitPay.SubClassФрагмент1Согласование.MyRecycleViewIsNullAdapter(arrayListIsNull1cData);
                myRecycleViewIsNullAdapter.notifyDataSetChanged();
                recyclerViewСогласование1С.setAdapter(myRecycleViewIsNullAdapter);
                recyclerViewСогласование1С.getAdapter().notifyDataSetChanged();*/
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
