package com.example.cs3270mi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ActionFragment.ChangeActionListener {

    private FragmentManager fm;
    private ResultsFragment resultsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fm = getSupportFragmentManager();

        fm.beginTransaction()
                .replace(R.id.fragAContainer, new ActionFragment(), "action")
                .replace(R.id.fragBContainer, new ResultsFragment(), "results")
                .commit();
    }


    @Override
    public void Values(String weight, String height, String age, String sex) {
        if (resultsFragment == null) {
            resultsFragment = (ResultsFragment) fm.findFragmentByTag("results");
        }
        if (resultsFragment != null) {
            resultsFragment.Values(weight, height, age, sex);

        }
    }



}