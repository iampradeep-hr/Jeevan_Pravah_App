package com.eklavya.eRakt.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eklavya.eRakt.R;
import com.eklavya.eRakt.activities.CustomPbar;
import com.eklavya.eRakt.adapters.BloodRequestAdapter;
import com.eklavya.eRakt.viewmodels.CustomUserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeView extends Fragment {

    private DatabaseReference donor_ref;
    FirebaseAuth mAuth;
    private BloodRequestAdapter restAdapter;
    private List<CustomUserData> postLists;
    private CustomPbar pbar;

    public HomeView() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_view_fragment, container, false);
        RecyclerView recentPosts = (RecyclerView) view.findViewById(R.id.recyleposts);

        recentPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        donor_ref = FirebaseDatabase.getInstance().getReference();
        postLists = new ArrayList<>();

        pbar=new CustomPbar(getContext());


        mAuth = FirebaseAuth.getInstance();
        getActivity().setTitle("Home");

        restAdapter = new BloodRequestAdapter(postLists);
        RecyclerView.LayoutManager pmLayout = new LinearLayoutManager(getContext());
        recentPosts.setLayoutManager(pmLayout);
        recentPosts.setItemAnimator(new DefaultItemAnimator());
        recentPosts.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recentPosts.setAdapter(restAdapter);

        AddPosts();
        return view;

    }
    private void AddPosts()
    {
        Query allposts = donor_ref.child("posts");
        pbar.showPbar();
        Log.d("track","pbar started");
        allposts.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {

                    for (DataSnapshot singlepost : dataSnapshot.getChildren()) {
                        CustomUserData customUserData = singlepost.getValue(CustomUserData.class);
                        Log.d("track","pbar reading data");

                        postLists.add(customUserData);
                        restAdapter.notifyDataSetChanged();
                    }
                    pbar.hidePbar();
                    Log.d("track","pbar ended");

                }
                else
                {
                    pbar.hidePbar();
                    Log.d("track","pbar ended");

                    Toast.makeText(getActivity(), "Database is empty now!",
                            Toast.LENGTH_LONG).show();
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.d("User", databaseError.getMessage());

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
