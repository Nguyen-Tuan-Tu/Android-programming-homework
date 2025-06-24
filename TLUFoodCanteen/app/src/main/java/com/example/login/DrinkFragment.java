package com.example.login;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DrinkFragment extends Fragment {
    private ImageView btn_back;
    private TextView bn_food;
    public DrinkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drinks, container, false);
        // Inflate the layout for this fragment
        btn_back = view.findViewById(R.id.back_button);
        bn_food = view.findViewById(R.id.btn_food);
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

        if(bn_food!=null){
            bn_food.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getActivity()!=null){
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new FoodFragment())
                                .commit();
                    }
                }
            });
        }
        return view;
    }
}
