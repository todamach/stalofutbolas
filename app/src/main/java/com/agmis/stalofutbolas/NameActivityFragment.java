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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NameActivityFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner nameSpinner;
    private Button nextButton;
    private int selectedPosition;
    private String selectedName;
    public final static String NAMEKEY = "nameKey";

    private final static String PLAYERS = "players";
    private final static String USERID = "userId";
    private final static String USERNAME = "username";
    private final static String SCORE = "score";

    private List<Player> listOfPlayers = new ArrayList<>();

    public NameActivityFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_name, container, false);

        String placeholderJsonString = "{\"players\":[{\"userId\":1,\"username\":\"PlayerA\",\"score\":100},{\"userId\":2,\"username\":\"PlayerB\",\"score\":200},{\"userId\":3,\"username\":\"PlayerC\",\"score\":300}, {\"userId\":4,\"username\":\"PlayerD\",\"score\":400}]}";
        parseJsonToPlayers(placeholderJsonString);

        //String[] placeholderNameArray = new String[]{"Rinktis...", "Petras", "Jonas", "Tomas", "Povilas", "Petras", "Jonas", "Tomas", "Povilas", "Petras", "Jonas", "Tomas", "Povilas"};
        nameSpinner = (Spinner) rootView.findViewById(R.id.name_spinner);

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, listOfPlayers);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameSpinner.setAdapter(adapter);
        nameSpinner.setOnItemSelectedListener(this);

        nextButton = (Button) rootView.findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(selectedName == null || selectedName == "Rinktis..."){
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
        Player player = (Player) parent.getSelectedItem();
        selectedName = player.getUsername();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void parseJsonToPlayers(String playersJsonString){
        try {
            JSONObject playersJsonObject = new JSONObject(playersJsonString);
            JSONArray playersArray = playersJsonObject.getJSONArray(PLAYERS);

            for(int i = 0; i < playersArray.length(); i++){

                JSONObject playerInfo = playersArray.getJSONObject(i);

                Player player = new Player();
                player.setId((int) playerInfo.get(USERID));
                player.setUsername((String) playerInfo.get(USERNAME));
                player.setScore((int) playerInfo.get(SCORE));

                listOfPlayers.add(player);
            }

        }catch(JSONException e){
            e.printStackTrace();
        }
    }



}
