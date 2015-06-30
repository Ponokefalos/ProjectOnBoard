package com.example.kir.projectonboard;

import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Kir on 30/6/2015.
 */
public class Orders {
    private int total;
    private ArrayList<MenuItems> menuItems = new ArrayList<>();
    private  String timeDate;




    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<MenuItems> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem newMenuItem) {
        menuItems.add((MenuItems) newMenuItem);
        updateTotal();


    }

    public Orders(ArrayList<MenuItems> menuItems) {
        this.menuItems = menuItems;
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);

        this.timeDate= ""+hour+":"+minute;
        if (!menuItems.isEmpty()) {
            for (int i = 0; i < menuItems.size(); i++) {
                total += menuItems.get(i).getPrice();

            }
        }
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }
    public void removeMenuItem (int id){
            menuItems.remove(id);
        updateTotal();
    }
    public void updateTotal (){
        total=0;
        for (int i=0;i<menuItems.size();i++){
            total+=menuItems.get(i).getPrice();
        }
    }

}
