package com.exercise.stupa.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.exercise.stupa.R;
import com.exercise.stupa.object.Schedule;

import java.util.List;
import java.util.Map;

/**
 * Created by anjas on 02/05/17.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter{

    private Activity context;
    private List<String> laptops;
    private Map<String, List<Schedule>> laptopCollections;

    public ExpandableListAdapter(Activity context, List<String> laptops, Map<String, List<Schedule>> laptopCollections) {
        this.context = context;
        this.laptops = laptops;
        this.laptopCollections = laptopCollections;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return laptopCollections.get(laptops.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String name = ((Schedule) getChild(groupPosition, childPosition)).getName();
        final String time = ((Schedule) getChild(groupPosition, childPosition)).getTime();
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, null);
        }

        TextView nameView = (TextView) convertView.findViewById(R.id.item_name);
        TextView timeView = (TextView) convertView.findViewById(R.id.time);
        nameView.setText(name);
        timeView.setText(time);

        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return laptopCollections.get(laptops.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return laptops.get(groupPosition);
    }

    public int getGroupCount() {
        return laptops.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String laptopName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.category);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
