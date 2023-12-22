package com.dsy.dsu.CommitingPrices.Model.businesslogic;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract  class GetJsonOt1cComminhgPricesParent {

  public abstract InputStream startingGetJsonOt1cComminhgPrices(@NotNull Context context, @NotNull String adress, @NotNull Integer PublicId);

  public abstract String startingGetStringOt1cComminhgPrices(@NotNull Context context, @NotNull String adress, @NotNull Integer PublicId);

  public  abstract byte[] GenetarJsonGet1cComminhgPrices( @NotNull Context context, @NotNull LinkedHashMap<String,Long> linkedHashMapОтпавркаНа1с);



  public  abstract Integer DeserializerJson1cComminhgPrices( @NotNull Context context, @NotNull InputStream inputStream);


}
