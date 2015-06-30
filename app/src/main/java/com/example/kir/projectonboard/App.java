package com.example.kir.projectonboard;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Kir on 30/6/2015.
 */
public class App extends Application {



    public static ArrayList<Umbrella> umbrellas = new ArrayList<Umbrella>();
    public  static ArrayList<Orders>  orders=new ArrayList<Orders>();

    public static ArrayList<Orders> getOrders() {
        return orders;
    }

    public static void setOrders(ArrayList<Orders> orders) {
        App.orders = orders;
    }

    @Override
    public void onCreate() {
        super.onCreate();

       // ArrayList<Umbrella> umbrellas = new ArrayList<Umbrella>();
        for (int i=0;i<20;i++){
            umbrellas.add(new Umbrella(true,i));
        }
      //  umbrellas.add(new Umbrella(false,21));
    }


    public static ArrayList<Umbrella> getUmbrellas() {
        return umbrellas;
    }

    public static void setUmbrellas(ArrayList<Umbrella> umbrellaz) {
        umbrellas = umbrellaz;
    }
}
