package com.example.susan.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Susan on 2018-01-31.
 */

public class DataView extends LinearLayout {
    LinearLayout layout, subLayout;
    TextView descriptionTV, constraintsTV, valueTV, flagTV;
    Context mContext = null;

    DataView(Context context) {
        super(context);
        mContext = context;
    }

    DataView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DataView);

        String descriptionText = a.getString(R.styleable.DataView_descriptionText);
        String constraintsText = a.getString(R.styleable.DataView_constraintText);
        String valueText = a.getString(R.styleable.DataView_valueText);
        String flagText = a.getString(R.styleable.DataView_flagText);

        String service = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(service);

        layout = (LinearLayout) li.inflate(R.layout.data_layout, this, true);

        descriptionTV = (TextView) layout.findViewById(R.id.description);
        constraintsTV = (TextView) layout.findViewById(R.id.constraints);
        valueTV = (TextView) layout.findViewById(R.id.value);
        flagTV = (TextView) layout.findViewById(R.id.flag);

        descriptionTV.setText(descriptionText);
        constraintsTV.setText(constraintsText);
        valueTV.setText(valueText);
        flagTV.setText(flagText);

        a.recycle();
    }

    DataView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    public void setDescriptionText(String text){
        descriptionTV.setText(text);
    }

    public void setConstraintsText(String text) {
        constraintsTV.setText(text);
    }

    public void setValueText(String text) {
        valueTV.setText(text);
    }

    public void setFlagTV(String text) {
        flagTV.setText(text);
    }
}

