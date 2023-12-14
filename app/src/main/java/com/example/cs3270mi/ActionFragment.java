package com.example.cs3270mi;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View root;
    private ChangeActionListener mCallBack;
    private Button btnCalculate;
    private RadioGroup radioGroup;
    private String weight;
    private String height;
    private String age;
   private String sex;



    public interface ChangeActionListener{
        void Values(String weight, String height, String age, String sex);
    }

    public ActionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActionFragment newInstance(String param1, String param2) {
        ActionFragment fragment = new ActionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try{
            mCallBack = (ChangeActionListener) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+
                    " must implement ChangeActionListener");
        }
    }


    // weight
    private final TextWatcher amountWatcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String val = s.toString();
            if(val != null) {
                weight = s.toString();
            }
//            else {val1 = 0;}
        }
    };


    // height
    private final TextWatcher amountWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String val = s.toString();
            if(val != null) {
                height = s.toString();;
            }
//            else {val1 = 0;}
        }
    };

    // age
    private final TextWatcher amountWatcher3 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String val = s.toString();
            if(val != null) {
                age = s.toString();
            }
//            else {val1 = 0;}
        }
    };



    @Override
    public void onResume() {
        super.onResume();
        EditText edit1 = root.findViewById(R.id.txtWeightLbs);
        edit1.addTextChangedListener(amountWatcher1);

        EditText edit2 = root.findViewById(R.id.txtHeightInc);
        edit2.addTextChangedListener(amountWatcher2);

        EditText edit3 = root.findViewById(R.id.txtAge);
        edit3.addTextChangedListener(amountWatcher3);

        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        weight = prefs.getString("weight", "0");
        height = prefs.getString("height", "0");
        age = prefs.getString("age", "0");
        sex = prefs.getString("sex", "0");

        edit1.setText(weight);
        edit2.setText(height);
        edit3.setText(age);

        radioGroup = root.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Respond to selection change
                switch (checkedId) {
                    case R.id.rbtnFemale:
                        Log.d("radio", "selected female");
                        sex = "1";
                        break;
                    case R.id.rbtnMale:
                        // Do something for option 2
                        Log.d("radio", "selected male");
                        sex = "0";
                        break;
                    default:
                        break;

                }
            }
        });


        btnCalculate = root.findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetVals();
            }
        });
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
        return root = inflater.inflate(R.layout.fragment_action, container, false);
    }

    public void GetVals(){
        mCallBack.Values(weight,height,age,sex);
    }
    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();

        prefsEditor.putString("weight", weight);
        prefsEditor.putString("height", height);
        prefsEditor.putString("age", age);
        prefsEditor.putString("sex", sex);

        prefsEditor.commit();
    }
}
