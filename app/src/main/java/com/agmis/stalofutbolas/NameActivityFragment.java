package com.agmis.stalofutbolas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class NameActivityFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner nameSpinner;
    private Button nextButton;
    private int selectedPosition;
    private String selectedName;
    public final static String NAMEKEY = "nameKey";

    public NameActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_name, container, false);

        String[] placeholderNameArray = new String[]{"Rinktis...", "Petras", "Jonas", "Tomas", "Povilas", "Petras", "Jonas", "Tomas", "Povilas", "Petras", "Jonas", "Tomas", "Povilas"};
        nameSpinner = (Spinner) rootView.findViewById(R.id.name_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item);
        adapter.addAll(placeholderNameArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameSpinner.setAdapter(adapter);
        nameSpinner.setOnItemSelectedListener(this);

        nextButton = (Button) rootView.findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(selectedPosition == 0 || selectedName == null){
                    Toast.makeText(v.getContext(), "Reikia pasirinkti vardą!", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(NAMEKEY, selectedName);
                    editor.apply();

                    Intent intent = new Intent(v.getContext(), NewGameActivity.class);
                    startActivity(intent);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPosition = position;
        selectedName = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
