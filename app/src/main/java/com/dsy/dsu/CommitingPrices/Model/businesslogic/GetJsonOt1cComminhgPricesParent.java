package com.dsy.dsu.CommitingPrices.Model.businesslogic;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract  class GetJsonOt1cComminhgPricesParent {

  abstract JsonNode startingJsonOt1cComminhgPrices(@NotNull Context context,@NotNull String adress,@NotNull int PublicId);


  abstract byte[] GenetarJsonOt1cComminhgPrices( @NotNull Context context);

}
