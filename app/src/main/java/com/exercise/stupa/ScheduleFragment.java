package com.exercise.stupa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.exercise.stupa.adapter.ExpandableListAdapter;
import com.exercise.stupa.object.Schedule;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    ExpandableListView expListView;
    List<String> groupList;
    List<Schedule> childList;
    Map<String, List<Schedule>> myCollection;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        expListView = (ExpandableListView) view.findViewById(R.id.list);
        createGroupList();
        createCollection();
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                getActivity(), groupList, myCollection);
        expListView.setAdapter(expListAdapter);

        return view;
    }

    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Monday");
        groupList.add("Tuesday");
        groupList.add("Wednesday");
        groupList.add("Thursday");
        groupList.add("Friday");
        groupList.add("Saturday");
    }

    private void createCollection() {
        // preparing laptops collection(child)
        Schedule[] monday = {
                new Schedule("Ceremonial", "07.00 – 08.20"),
                new Schedule("Citizenship Education", "08.25 – 09.05"),
                new Schedule("BREAK", "09.05 – 09.35"),
                new Schedule("Mathematics", "09.35 – 10.55"),
                new Schedule("English", "11.00 – 11.40"),
                new Schedule("BREAK", "11.40 – 12.40"),
                new Schedule("Art", "12.40 – 14.00")
        };
        Schedule[] tuesday = {
                new Schedule("Mathematics", "07.00 – 08.20"),
                new Schedule("Guidance Counseling", "08.25 – 09.05"),
                new Schedule("BREAK", "09.05 – 09.35"),
                new Schedule("Natural Sciences", "09.35 – 10.55"),
                new Schedule("Social Sciences", "11.00 – 11.40"),
                new Schedule("BREAK", "11.40 – 12.40"),
                new Schedule("Skills", "12.40 – 14.00")
        };
        Schedule[] wednesday = {
                new Schedule("Indonesian", "07.00 – 08.20"),
                new Schedule("Social Sciences", "08.25 – 09.05"),
                new Schedule("BREAK", "09.05 – 09.35"),
                new Schedule("Information Technology", "09.35 – 10.55"),
                new Schedule("Natural Sciences", "11.00 – 11.40"),
                new Schedule("BREAK", "11.40 – 12.40"),
                new Schedule("Javanese", "12.40 – 14.00")
        };
        Schedule[] thursday = {
                new Schedule("English", "07.00 – 08.20"),
                new Schedule("Natural Sciences", "08.25 – 09.05"),
                new Schedule("BREAK", "09.05 – 09.35"),
                new Schedule("Mathematics", "09.35 – 10.55"),
                new Schedule("Citizenship Education", "11.00 – 11.40")
        };
        Schedule[] friday = {
                new Schedule("Islamic Studies", "07.00 – 08.20"),
                new Schedule("Javanese", "08.25 – 09.05"),
                new Schedule("BREAK", "09.05 – 09.35"),
                new Schedule("Social Sciences", "09.35 – 10.55")
        };
        Schedule[] saturday = {
                new Schedule("Sports", "07.00 – 08.20"),
                new Schedule("Indonesian", "08.25 – 09.05"),
                new Schedule("BREAK", "09.05 – 09.35"),
                new Schedule("Information Technology", "09.35 – 10.55")
        };

        myCollection = new LinkedHashMap<String, List<Schedule>>();

        for (String laptop : groupList) {
            if (laptop.equals("Monday")) {
                loadChild(monday);
            } else if (laptop.equals("Tuesday"))
                loadChild(tuesday);
            else if (laptop.equals("Wednesday"))
                loadChild(wednesday);
            else if (laptop.equals("Thursday"))
                loadChild(thursday);
            else if (laptop.equals("Friday"))
                loadChild(friday);
            else if (laptop.equals("Saturday"))
                loadChild(saturday);

            myCollection.put(laptop, childList);
        }
    }

    private void loadChild(Schedule[] laptopModels) {
        childList = new ArrayList<Schedule>();
        for (Schedule model : laptopModels)
            childList.add(model);
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

}
