package com.example.kir.projectonboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class BeachOverview extends ActionBarActivity implements View.OnClickListener {


    int verticalThickness;
    LinearLayout llUmbrella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beach_overview);
        getSupportActionBar().hide();
        verticalThickness = Methods.calculateComponentSize(220, 600, getResources());
        llUmbrella = (LinearLayout) findViewById(R.id.llUmbrellas);
        AddBanButtonsCardView(llUmbrella, App.getUmbrellas());

    }


    public void AddBanButtonsCardView(LinearLayout BanLayout,
                                      ArrayList<Umbrella> lista) {
        for (int i = 0; i < lista.size(); i++) {
            CardView cvRestaurant = new CardView(this);
            cvRestaurant.setCardElevation((float) 18.0);
            cvRestaurant.setRadius((float) 15.0);
            TextView tvRestaurantName = new TextView(this);
            cvRestaurant.setId(i);
            if (lista.get(i).getStatus() == true) {
                cvRestaurant.setCardBackgroundColor(Color.parseColor("#ffffff"));
                tvRestaurantName.setBackgroundColor(Color.parseColor("#ffffff"));
            } else {
                cvRestaurant.setCardBackgroundColor(Color.parseColor("#c75c5c"));
                tvRestaurantName.setBackgroundColor(Color.parseColor("#c75c5c"));
                cvRestaurant.setCardElevation((float) 5.0);
            }
            String s = "Umbrella " + (lista.get(i).getId() + 1);
            tvRestaurantName.setText(s);
            tvRestaurantName.setTypeface(null, Typeface.BOLD);
            tvRestaurantName.setGravity(Gravity.CENTER);
            tvRestaurantName.setTextColor(Color.parseColor("#4f5d73"));
            cvRestaurant.setOnClickListener(this);
            if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {

                tvRestaurantName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            }

            tvRestaurantName.setHeight(verticalThickness);
            cvRestaurant.addView(tvRestaurantName);
            BanLayout.addView(cvRestaurant);
            LinearLayout.LayoutParams cardLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            //// Set cardView margins depending on ban state


            cardLayoutParams.setMargins(62, 40, 62, 40);
//            cardLayoutParams.setMargins(12, 18, 12, 18);

            cvRestaurant.setLayoutParams(cardLayoutParams);
        }
    }

    @Override
    public void onClick(View v) {

        Preferences.savePrefsInt("SELECTED_UMBRELLA", v.getId(), getApplicationContext());
        startActivity(new Intent(BeachOverview.this, UmbrellaOverview.class));
    }
}
