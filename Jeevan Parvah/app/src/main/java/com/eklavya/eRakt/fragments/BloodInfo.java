package com.eklavya.eRakt.fragments;


import android.os.Bundle;
import com.eklavya.eRakt.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class BloodInfo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bloodinfo, container, false);
        getActivity().setTitle("Information");
        return view;
    }
}
