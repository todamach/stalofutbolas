package com.agmis.stalofutbolas.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by sasly on 2015-10-29.
 */
public class CustomAdapter extends ArrayAdapter<String> {
    public List<Integer> disabledItems = new ArrayList<>();

    public CustomAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv = (TextView) view;
        if (disabledItems.contains(position)) {
            // Set the disable item text color
            tv.setTextColor(Color.GRAY);
        } else {
            tv.setTextColor(Color.BLACK);
        }
        return view;
    }

    @Override
    public boolean isEnabled(int position) {

        if (disabledItems.contains(position)) {
            return false;
        } else {
            return true;
        }
    }

    public void disableItem(int index) {
        if (!disabledItems.contains(index))
            disabledItems.add(index);
    }

    public void enableItem(int index) {
        if (disabledItems.contains(index))
            disabledItems.remove((Object) index);
    }
}