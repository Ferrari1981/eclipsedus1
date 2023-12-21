package com.dsy.dsu.CommitingPrices.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.dsy.dsu.R;

import dagger.hilt.android.scopes.ActivityScoped;

public class MainActivityCommitingPrices extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Activity activity;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_commiting_prices);
        // TODO: 21.12.2023
    }


}//todo  end  public class MainActivityCommitingPrices extends AppCompatActivity




// TODO: 21.12.2023 class bicces logic
class Bl{
    private Activity activity;
    private Context context;


    public Bl(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }
}