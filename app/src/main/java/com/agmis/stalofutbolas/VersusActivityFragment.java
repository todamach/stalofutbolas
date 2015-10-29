package com.agmis.stalofutbolas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.agmis.stalofutbolas.adapter.CustomAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * Created by sasly on 2015-10-28.
 */
public class VersusActivityFragment extends Fragment {

    @Bind({R.id.bluePlayer1,R.id.bluePlayer2,R.id.purplePlayer1,R.id.purplePlayer2})
    public List<Spinner> playerSpinnerList;

    @Bind(R.id.blueScore)
    public Spinner blueScore;
    @Bind(R.id.purpleScore)
    public Spinner purpleScore;

    private FragmentActivity activity;

    String[] allScores;
    String[] allPlayers;

    List<String> purpleScores;
    List<String>  blueScores;

    private List<CustomAdapter> spinnersAdapters = new ArrayList<>();
    private ArrayAdapter<CharSequence> scoresPurpleAdapter;
    private ArrayAdapter<CharSequence> scoresBlueAdapter;


    public int[] lastSelected = {-1,-1,-1,-1};

    public VersusActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_versus, container, false);
        ButterKnife.bind(this, view);
        activity = getActivity();
        allScores = getResources().getStringArray(R.array.scores);
        allPlayers = getResources().getStringArray(R.array.players);


        for(int i=0; i< playerSpinnerList.size(); i++){
            spinnersAdapters.add(new CustomAdapter(activity,R.layout.spinner_item));
            spinnersAdapters.get(i).addAll(allPlayers);
            spinnersAdapters.get(i).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            playerSpinnerList.get(i).setAdapter(spinnersAdapters.get(i));
        }

        scoresBlueAdapter = new ArrayAdapter<>(activity,R.layout.spinner_item);
        scoresBlueAdapter.addAll(allScores);
        scoresBlueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        scoresPurpleAdapter = new ArrayAdapter<>(activity,R.layout.spinner_item);
        scoresPurpleAdapter.addAll(allScores);
        scoresPurpleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        blueScore.setAdapter(scoresBlueAdapter);
        purpleScore.setAdapter(scoresPurpleAdapter);

        return view;
    }


    @OnClick(R.id.submitResults)
    public void OnSubmitResultsClick() {
//        String text = playerSpinnerList.getSelectedItem().toString() + " and " +
//                bluePlayer2.getSelectedItem().toString() + " VS " +
//                purplePlayer1.getSelectedItem().toString() + " and " +
//                purplePlayer2.getSelectedItem().toString() + "\n" +
//                blueScore.getSelectedItem().toString() + " : " + purpleScore.getSelectedItem().toString();
//
//        Toast.makeText(activity, text, Toast.LENGTH_LONG).show();
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

    @OnItemSelected(R.id.bluePlayer1)
    public void OnBluePlayer1Selected(AdapterView<?> parent, View view, int pos, long id){
        EnableLastSelection(pos,0);
        DisableListItem(pos, 0);

    }
    @OnItemSelected(R.id.bluePlayer2)
    public void OnBluePlayer2Selected(AdapterView<?> parent, View view, int pos, long id){
        EnableLastSelection(pos, 1);
        DisableListItem(pos,1);

    }
    @OnItemSelected(R.id.purplePlayer1)
    public void OnPurplePlayer1Selected(AdapterView<?> parent, View view, int pos, long id){
        EnableLastSelection(pos, 2);
        DisableListItem(pos,2);

    }
    @OnItemSelected(R.id.purplePlayer2)
    public void OnPurplePlayer2Selected(AdapterView<?> parent, View view, int pos, long id){
        EnableLastSelection(pos,3);
        DisableListItem(pos, 3);

    }

    public void DisableListItem(int pos, int ignoreAdapter){
        for(int i=0; i < 4; i++){
            if(i==ignoreAdapter)
                continue;
            spinnersAdapters.get(i).disableItem(pos);
            spinnersAdapters.get(i).notifyDataSetChanged();
        }
        lastSelected[ignoreAdapter] =pos;
    }
    public void EnableLastSelection(int pos, int ignoreAdapter){
        for(int i=0; i < 4; i++){
            if(i==ignoreAdapter)
                continue;

            if(lastSelected[ignoreAdapter] != -1) {
                spinnersAdapters.get(i).enableItem(lastSelected[ignoreAdapter]);
                spinnersAdapters.get(i).notifyDataSetChanged();
            }
        }
    }
}
