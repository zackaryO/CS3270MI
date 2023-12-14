package com.example.cs3270mi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView txtBMI;
    private TextView txtBFP;
    private double weight = 0;
    private double height = 0;
    private double age = 0;
    private double BMI;
    private double BFP;
    private double sex = 0; // 0 = male, 1 = female
    private View root;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultsFragment() {
        // Required empty public constructor
    }

    public void Values(String sWeight, String sHeight, String sAge, String sSex) {
        weight = Double.parseDouble(sWeight);
        height = Double.parseDouble(sHeight);
        age = Double.parseDouble(sAge);
        sex = Double.parseDouble(sSex);
        CalcBMI(weight, height);
        CalcBFP(BMI, age, sex);
        txtBMI.setText(BMI + "");
        txtBFP.setText(BFP + "");

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultsFragment newInstance(String param1, String param2) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_results, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        txtBMI = root.findViewById(R.id.txtBMI);
        txtBFP = root.findViewById(R.id.txtBFP);


        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        weight = Double.parseDouble(prefs.getString("weight", "0"));
        height = Double.parseDouble(prefs.getString("height", "0"));
        age = Double.parseDouble(prefs.getString("age", "0"));
        sex = Double.parseDouble(prefs.getString("sex", "0"));
        int savBMI = prefs.getInt("BMI", 0);
        int savBFP = prefs.getInt("BFP", 0);

       BMI = Double.valueOf(savBMI)/100;
       BFP = Double.valueOf(savBFP)/100;
        txtBMI.setText(BMI + "");
        txtBFP.setText(BFP + "");

    }

    public void CalcBMI(double weight, double height) {
        double hSqr = Math.pow(height, 2);
        BMI = weight / hSqr * 703;
    }

    public void CalcBFP(double BMI, double age, double sex) {

        BFP = (1.20 * BMI) + (0.23 * age) - (10.8 * sex) - 5.4;

    }


    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();


        int savBMI = (int) (BMI * 100);
        int savBFP = (int) (BFP * 100);


        prefsEditor.putString("weight", String.valueOf(weight));
        prefsEditor.putString("height", String.valueOf(height));
        prefsEditor.putString("age", String.valueOf(age));
        prefsEditor.putString("sex", String.valueOf(sex));
        prefsEditor.putInt("BMI", savBMI);
        prefsEditor.putInt("BFP", savBFP);

        prefsEditor.commit();
    }

}