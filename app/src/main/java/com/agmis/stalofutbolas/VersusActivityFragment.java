package com.agmis.stalofutbolas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.agmis.stalofutbolas.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * Created by sasly on 2015-10-28.
 */
public class VersusActivityFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    @Bind(R.id.bluePlayer1)
    public Spinner bluePlayer1;
    @Bind(R.id.bluePlayer2)
    public Spinner bluePlayer2;
    @Bind(R.id.purplePlayer1)
    public Spinner purplePlayer1;
    @Bind(R.id.purplePlayer2)
    public Spinner purplePlayer2;

    @Bind(R.id.blueScore)
    public Spinner blueScore;
    @Bind(R.id.purpleScore)
    public Spinner purpleScore;

    private FragmentActivity activity;

    String[] allScores;
    String[] allPlayers;

    List<String> purpleScores;
    List<String>  blueScores;

    private ArrayAdapter<CharSequence> playersAdapter;
    private ArrayAdapter<CharSequence> scoresPurpleAdapter;
    private ArrayAdapter<CharSequence> scoresBlueAdapter;

    private String lastBluePlayer1;
    private String lastBluePlayer2;
    private String lastPurplePlayer1;
    private String lastPurplePlayer2;

    public VersusActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_versus, container, false);
        ButterKnife.bind(this, view);
        activity = getActivity();

        String[] placeholderNameArray = new String[]{"Rinktis...", "Petras", "Jonas", "Tomas", "Povilas", "Tadas"};
        List<String> placeholderList = new ArrayList<String>(Arrays.asList(placeholderNameArray));

        allScores = getResources().getStringArray(R.array.scores);
        allPlayers = getResources().getStringArray(R.array.players);


        playersAdapter = new ArrayAdapter<>(activity, R.layout.spinner_item);
        playersAdapter.addAll(placeholderList);
        playersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        scoresBlueAdapter = new ArrayAdapter<>(activity,R.layout.spinner_item);
        blueScores = new LinkedList<>(Arrays.asList(allScores));
        scoresBlueAdapter.addAll(blueScores);
        scoresBlueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        scoresPurpleAdapter = new ArrayAdapter<>(activity,R.layout.spinner_item);
        purpleScores = new LinkedList<>(Arrays.asList(allScores));
        scoresPurpleAdapter.addAll(purpleScores);
        scoresPurpleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bluePlayer1.setAdapter(playersAdapter);
        bluePlayer2.setAdapter(playersAdapter);
        purplePlayer1.setAdapter(playersAdapter);
        purplePlayer2.setAdapter(playersAdapter);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String nameString = prefs.getString(NameActivityFragment.NAMEKEY, "null");
        bluePlayer1.setSelection(placeholderList.indexOf(nameString));

        lastBluePlayer1 = nameString;
        playersAdapter.remove(nameString);

        bluePlayer1.setOnItemSelectedListener(this);
        bluePlayer2.setOnItemSelectedListener(this);
        purplePlayer1.setOnItemSelectedListener(this);
        purplePlayer2.setOnItemSelectedListener(this);

        blueScore.setAdapter(scoresBlueAdapter);
        purpleScore.setAdapter(scoresPurpleAdapter);

        return view;
    }


    @OnClick(R.id.submitResults)
    public void OnSubmitResultsClick() {
        String text = bluePlayer1.getSelectedItem().toString() + " and " +
                bluePlayer2.getSelectedItem().toString() + " VS " +
                purplePlayer1.getSelectedItem().toString() + " and " +
                purplePlayer2.getSelectedItem().toString() + "\n" +
                blueScore.getSelectedItem().toString() + " : " + purpleScore.getSelectedItem().toString();

        Toast.makeText(activity, text, Toast.LENGTH_LONG).show();
    }

    @OnItemSelected(R.id.blueScore)
    public void OnBlueScoreSelected(AdapterView<?> parent, View view, int pos, long id) {
        int selectedScore = Integer.parseInt(((TextView) view).getText().toString());
        scoresPurpleAdapter.clear();
        scoresPurpleAdapter.addAll(Arrays.copyOfRange(allScores, 0, allScores.length - selectedScore));
        scoresPurpleAdapter.notifyDataSetChanged();
    }

    @OnItemSelected(R.id.purpleScore)
    public void OnPurpleScoreSelected(AdapterView<?> parent, View view, int pos, long id) {
        int selectedScore = Integer.parseInt(((TextView) view).getText().toString());
        scoresBlueAdapter.clear();
        scoresBlueAdapter.addAll(Arrays.copyOfRange(allScores, 0, allScores.length - selectedScore));
        scoresBlueAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (view != null) {
            if (view.getId() == R.id.bluePlayer1) {
                playersAdapter.add(lastBluePlayer1);
                lastBluePlayer1 = parent.getSelectedItem().toString();
                playersAdapter.remove(lastBluePlayer1);
            } else if (view.getId() == R.id.bluePlayer2) {
                playersAdapter.add(lastBluePlayer2);
                lastBluePlayer2 = parent.getSelectedItem().toString();
                playersAdapter.remove(lastBluePlayer2);
            } else if (view.getId() == R.id.purplePlayer1) {
                playersAdapter.add(lastPurplePlayer1);
                lastPurplePlayer1 = parent.getSelectedItem().toString();
                playersAdapter.remove(lastPurplePlayer1);
            } else if (view.getId() == R.id.purplePlayer2) {
                playersAdapter.add(lastPurplePlayer2);
                lastPurplePlayer2 = parent.getSelectedItem().toString();
                playersAdapter.remove(lastPurplePlayer2);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
