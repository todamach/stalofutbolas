package com.agmis.stalofutbolas;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewGameActivityFragment extends Fragment {

    private String nameString;
    private TextView nameTextView;

    public NewGameActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_new_game, container, false);
        nameTextView = (TextView) rootView.findViewById(R.id.nameTextView);
        Intent iin = getActivity().getIntent();
        Bundle bundle = iin.getExtras();

        if(bundle != null){
            nameString = (String) bundle.get(NameActivityFragment.NAMEKEY);
            nameTextView.setText(nameString);
        }

        return rootView;
    }
}
