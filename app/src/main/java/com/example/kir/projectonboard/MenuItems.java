package com.example.kir.projectonboard;

/**
 * Created by Kir on 30/6/2015.
 */
public class MenuItems {
    private double price;
    private String name;
    private String desc;

    public MenuItems(double price, String name, String desc) {
        this.price = price;
        this.name = name;
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
