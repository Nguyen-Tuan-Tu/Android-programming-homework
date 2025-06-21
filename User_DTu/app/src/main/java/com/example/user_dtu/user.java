package com.example.user_dtu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

public class user extends Fragment {
    private ImageView btn_back;
    private LinearLayout  btn_taikhoan;
    private LinearLayout btn_lichsu;
    public user() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.user, container, false);
        // Inflate the layout for this fragment
        btn_back = view.findViewById(R.id.back_button);
        btn_taikhoan = view.findViewById(R.id.btn_account);
        btn_lichsu = view.findViewById(R.id.btn_history);
        if(btn_back!=null){
            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getActivity()!=null){
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new StartFragment())
                                .commit();
                    }
                }
            });
        }

        if(btn_taikhoan!=null){
            btn_taikhoan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getActivity()!=null){
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new ProfileFragment())
                                .commit();
                    }
                }
            });
        }

        if(btn_lichsu!=null){
            btn_lichsu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getActivity()!=null){
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new historyFragment())
                                .commit();
                    }
                }
            });
        }
        return view;
    }
}