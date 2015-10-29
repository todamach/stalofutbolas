package com.agmis.stalofutbolas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agmis.stalofutbolas.R;
import com.agmis.stalofutbolas.adapter.PlayerListAdapter;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;



public class EloListFragment extends Fragment {

    @Bind(R.id.playerList)
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elolist, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PlayerListAdapter(GetListData());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public String[] GetListData() {
        String[] dummyList = {"Vienas", "Du", "Trys", "Keturi"};
        return dummyList;
    }
}
