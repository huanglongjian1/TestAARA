package com.android.testmvp.test4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.testmvp.R;

public class Blank2Fragment extends Fragment {
    private Button mBtnInputFragment3, mBtnBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank2, container, false);
        mBtnInputFragment3 = view.findViewById(R.id.btn_input_fragment3);
        mBtnBack = view.findViewById(R.id.back);
        mBtnInputFragment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_two_to_three); //进入第三个碎片
            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).popBackStack(); //返回上一个碎片
            }
        });
        return view;
    }

}