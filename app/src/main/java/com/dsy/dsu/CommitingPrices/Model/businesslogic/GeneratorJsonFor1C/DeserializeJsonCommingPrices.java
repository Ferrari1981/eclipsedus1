package com.dsy.dsu.CommitingPrices.Model.businesslogic.GeneratorJsonFor1C;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import java.io.IOException;

public class DeserializeJsonCommingPrices extends StdDeserializer<JsonNode> {
    Context context;

    public DeserializeJsonCommingPrices(StdDeserializer<?> src) {
        super(src);
    }


    @Override
    public JsonNode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        try{
      // TokenBuffer tokenBuffer= ctxt.bufferAsCopyOfValue(p);
    /*     JsonToken jsonToken= p.getLastClearedToken();.*/
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
        return null;
    }
}
