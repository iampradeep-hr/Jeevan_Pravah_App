package com.eklavya.eRakt.fragments;


import android.os.Bundle;

import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.eklavya.eRakt.R;

public class AboutUs extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aboutus, container, false);
        getActivity().setTitle("About us");

        TextView textView = view.findViewById(R.id.txtv);
        Linkify.addLinks(textView, Linkify.ALL);
        return  view;
    }
}
