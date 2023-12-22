package com.dsy.dsu.CommitingPrices.Model.businesslogic.GeneratorJsonFor1C;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GeneratorJsonFor1cCommitingPrices extends JsonSerializer<Bundle> {

    Context context;

    public GeneratorJsonFor1cCommitingPrices(Context context) {
        this.context = context;
    }


    @Override
    public void serialize(Bundle value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

 try{

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }
}
