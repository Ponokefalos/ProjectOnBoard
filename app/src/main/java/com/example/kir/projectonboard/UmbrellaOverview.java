package com.example.kir.projectonboard;

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
import android.widget.TextView;

import java.util.ArrayList;


public class UmbrellaOverview extends ActionBarActivity implements View.OnLongClickListener, View.OnClickListener {

    int selectedUmbrellaId;
    TextView tvUmbrellaName;
    Umbrella umbrella;
    int verticalThickness;
    LinearLayout llUmbrellaOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_umbrella_overview);
        getSupportActionBar().hide();
        llUmbrellaOverview= (LinearLayout) findViewById(R.id.llUmbrellaOrders);
        selectedUmbrellaId = Preferences.loadPrefsInt("SELECTED_UMBRELLA", 0, getApplicationContext());
        umbrella = App.getUmbrellas().get(selectedUmbrellaId);
        tvUmbrellaName = (TextView) findViewById(R.id.tvUmbrellaTitle);
        verticalThickness = Methods.calculateComponentSize(220, 600, getResources());
        tvUmbrellaName.setText("Umbrella " + (umbrella.getId() + 1) + "      Total: " + umbrella.getTotal());
        MenuItems menuItem=new MenuItems(100,"kafes",null);
        ArrayList <MenuItems> menuItems = new ArrayList<>();
        menuItems.add(menuItem);
        umbrella.addOrder(new Orders(menuItems));
           AddBanButtonsCardView(llUmbrellaOverview, umbrella.getOrders());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_umbrella_overview, menu);
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
                                      ArrayList<Orders> lista) {
        for (int i = 0; i < lista.size(); i++) {
            CardView cvRestaurant = new CardView(this);
            cvRestaurant.setCardElevation((float) 18.0);
            cvRestaurant.setRadius((float) 15.0);
            TextView tvRestaurantName = new TextView(this);
            cvRestaurant.setId(i);

                cvRestaurant.setCardBackgroundColor(Color.parseColor("#c75c5c"));
                tvRestaurantName.setBackgroundColor(Color.parseColor("#c75c5c"));
                cvRestaurant.setCardElevation((float) 5.0);

            String s = ""+umbrella.getOrders().get(i).getTimeDate()+" \t "+umbrella.getTotal();
            tvRestaurantName.setText(s);
            tvRestaurantName.setTypeface(null, Typeface.BOLD);
            tvRestaurantName.setGravity(Gravity.CENTER);
            tvRestaurantName.setTextColor(Color.parseColor("#4f5d73"));
            cvRestaurant.setOnClickListener(this);
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
        umbrella.removeOrder(v.getId());
     //   llUmbrellaOverview.removeAllViews();

     //   AddBanButtonsCardView(llUmbrellaOverview,umbrella.getOrders());
        ArrayList<Umbrella> tempU = new ArrayList<Umbrella>();
        tempU = App.getUmbrellas();
        tempU.remove(selectedUmbrellaId);
        tempU.add(selectedUmbrellaId,umbrella);
        App.setUmbrellas(tempU);

        return false;
    }

    @Override
    public void onClick(View v) {
        App.setOrders(umbrella.getOrders());
        Preferences.savePrefsInt("SELECTED_ORDER", v.getId(), getApplicationContext());
        startActivity(new Intent(UmbrellaOverview.this, OrderOverview.class));
    }
}
