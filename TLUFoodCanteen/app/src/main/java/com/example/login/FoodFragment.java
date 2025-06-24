package com.example.login;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FoodFragment extends Fragment {
    private ImageView btn_back;
    private TextView bn_drink;

    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foods, container, false);
        // Inflate the layout for this fragment
        btn_back = view.findViewById(R.id.back_button);
        bn_drink = view.findViewById(R.id.btn_drink);

        if(btn_back!=null){
            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getActivity()!=null){
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container,new StartFragment())
                                .commit();
                    }
                }
            });
        }

        if(bn_drink!=null){
            bn_drink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getActivity()!=null){
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new DrinkFragment())
                                .commit();
                    }
                }
            });
        }

        ImageView Mitom = view.findViewById(R.id.img_mi_tom); // Sửa lại để dùng view.findViewById()
        if (Mitom != null) {
            Mitom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Chuyển sang Activity Chi Tiết Món Ăn
                    Intent intent = new Intent(getActivity(), ChiTietMonAnActivity.class); // Sử dụng getActivity() thay vì FoodFragment.this
                    startActivity(intent);
                    // Nếu bạn muốn kết thúc Activity hiện tại (nếu FoodFragment đang hoạt động trong một Activity)
                    getActivity().finish(); // Kết thúc Activity nếu cần thiết
                }
            });
        }

        return view;
    }
}
