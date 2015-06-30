package com.example.kir.projectonboard;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by Kir on 30/6/2015.
 */
public class Methods {
    //// Method that calculates the height of a component based on screen DPI
    public static int calculateComponentSize(int firstNumber, int secondNumber, Resources resources) {
        DisplayMetrics metrics = resources.getDisplayMetrics();
        ////320 moto g 10-10
        return (firstNumber * metrics.densityDpi) / secondNumber;
    }
}
