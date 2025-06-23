package com.example.tlucanteenconnect;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class ThongBao extends Fragment {
    private ImageView btn_back;
    public ThongBao() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongbao, container, false);
        // Inflate the layout for this fragment
        btn_back = view.findViewById(R.id.back_button);
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
        return view;
    }
}
