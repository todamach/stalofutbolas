package com.agmis.stalofutbolas;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private Button nameActivityButton;
    private Button gameActivityButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        nameActivityButton = (Button) rootView.findViewById(R.id.nameActivityButton);

        nameActivityButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NameActivity.class);
                startActivity(intent);
            }
        });

        gameActivityButton = (Button) rootView.findViewById(R.id.gameActivityButton);

        gameActivityButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewGameActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
