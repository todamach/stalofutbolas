package com.agmis.stalofutbolas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewGameActivityFragment extends Fragment {

    private String nameString;
    private TextView nameTextView;
    private Button newGameButton;

    public NewGameActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_new_game, container, false);
        nameTextView = (TextView) rootView.findViewById(R.id.nameTextView);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        nameString = prefs.getString(NameActivityFragment.NAMEKEY, "null");
        nameTextView.setText(nameString);

        newGameButton = (Button) rootView.findViewById(R.id.newGameButton);

        newGameButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Atidaryti žaidimo pridėjimo activity..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), VersusActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
