package com.example.kir.projectonboard;

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
import android.widget.TextView;

import java.util.ArrayList;


public class OrderOverview extends ActionBarActivity implements View.OnLongClickListener{
    int verticalThickness;
    Orders order;
    LinearLayout llOrdersMenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_overview);
        getSupportActionBar().hide();
        verticalThickness = Methods.calculateComponentSize(220, 600, getResources());
        llOrdersMenuItems= (LinearLayout) findViewById(R.id.llOrdersMenuItems);
        order=App.getOrders().get(Preferences.loadPrefsInt("SELECTED_ORDER",0,getApplicationContext()));
        AddBanButtonsCardView(llOrdersMenuItems,order.getMenuItems());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void AddBanButtonsCardView(LinearLayout BanLayout,
                                      ArrayList<MenuItems> lista) {
        for (int i = 0; i < lista.size(); i++) {
            CardView cvRestaurant = new CardView(this);
            cvRestaurant.setCardElevation((float) 18.0);
            cvRestaurant.setRadius((float) 15.0);
            TextView tvRestaurantName = new TextView(this);
            cvRestaurant.setId(i);

            cvRestaurant.setCardBackgroundColor(Color.parseColor("#c75c5c"));
            tvRestaurantName.setBackgroundColor(Color.parseColor("#c75c5c"));
            cvRestaurant.setCardElevation((float) 5.0);

            String s = ""+order.getMenuItems().get(i).getName()+" \t "+order.getMenuItems().get(i).getPrice();
            tvRestaurantName.setText(s);
            tvRestaurantName.setTypeface(null, Typeface.BOLD);
            tvRestaurantName.setGravity(Gravity.CENTER);
            tvRestaurantName.setTextColor(Color.parseColor("#4f5d73"));
            cvRestaurant.setOnLongClickListener(this);
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
    public boolean onLongClick(View v) {
           order.removeMenuItem(v.getId());
       // llOrdersMenuItems.removeAllViews();
        ArrayList<Orders> temp = new ArrayList<Orders>();
        temp=App.getOrders();
        temp.remove(Preferences.loadPrefsInt("SELECTED_ORDER",0,getApplicationContext()));
        temp.add(Preferences.loadPrefsInt("SELECTED_ORDER",0,getApplicationContext()),order);
        App.setOrders(temp);
     // llOrdersMenuItems=  (LinearLayout) findViewById(R.id.llOrdersMenuItems);
       //       llOrdersMenuItems.removeView(v);
        //App.setOrders();
        //AddBanButtonsCardView(llOrdersMenuItems, order.getMenuItems());
        return false;
    }
}
