package com.example.susan.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Susan on 2018-01-31.
 */

public class DataView {
    private String descriptionText;
    private String constraintsText;
    private String valueText;
    private String flagText;

    public void setDescriptionText(String text){
        descriptionText = text;
    }

    public void setConstraintsText(String text) {
        constraintsText= text;
    }

    public void setValueText(String text) {
        valueText = text;
    }

    public void setFlagTV(String text) {
        flagText = text;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public String getConstraintsText() {
        return constraintsText;
    }

    public String getValueText() {
        return valueText;
    }

    public String getFlagText() {
        return flagText;
    }
}