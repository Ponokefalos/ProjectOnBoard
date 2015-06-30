package com.example.kir.projectonboard;

import java.util.ArrayList;

/**
 * Created by Kir on 30/6/2015.
 */


public class Umbrella {
    private boolean status = true;/*true=free false=occ*/
    private int id;
    private ArrayList<Orders> orders = new ArrayList<Orders>();
    private double total=0;

    public void addOrder(Orders order) {
        orders.add(order);
        updateTotal();
    }
    public  void removeOrder (int id){
        orders.remove(id);
        updateTotal();
    }
    public void updateTotal (){
        total=0;
        for (int i=0;i<orders.size();i++){
            total+=orders.get(i).getTotal();
        }

    }

    public Umbrella(boolean status, int id) {
        this.status = status;
        this.id = id;
    }

    public ArrayList<Orders> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Orders> orders) {
        this.orders = orders;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
