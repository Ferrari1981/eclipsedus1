package com.dsy.dsu.CommitingPrices.Model.businesslogic;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract  class GetJsonOt1cComminhgPricesParent {

  abstract InputStream startingGetJsonOt1cComminhgPrices(@NotNull Context context, @NotNull String adress, @NotNull Integer PublicId);


  abstract byte[] GenetarJsonGet1cComminhgPrices( @NotNull Context context, @NotNull LinkedHashMap<String,Long> linkedHashMapОтпавркаНа1с);



}
