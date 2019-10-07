package com.example.footy.ui.main;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.footy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TomorrowFragment extends Fragment {


    public TomorrowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_today, container, false);

        TextView textView = view.findViewById(R.id.tv_test);
        textView.setText("Tomorrow :)");

        return view;

    }

}
