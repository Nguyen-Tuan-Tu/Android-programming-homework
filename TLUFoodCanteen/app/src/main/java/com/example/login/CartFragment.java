package com.example.login;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class CartFragment extends Fragment {
    private ImageView btn_back;
    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart, container, false);
        // Inflate the layout for this fragment
        btn_back = view.findViewById(R.id.back_button);
        btn_back.bringToFront();
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
            ImageView Burger = view.findViewById(R.id.img_burger_bo); // Sửa lại để dùng view.findViewById()
            if (Burger != null) {
                Burger.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Chuyển sang Activity Menu Đồ uống khi nhấn nút
                        Intent intent = new Intent(getActivity(), OrderActivity.class); // Sử dụng getActivity() thay vì CartFragment.this
                        startActivity(intent);
                        // Nếu bạn muốn kết thúc Activity hiện tại (nếu CartFragment đang hoạt động trong một Activity)
                        getActivity().finish(); // Kết thúc Activity nếu cần thiết
                    }
                });
            }

        }


        return view;

    }

}
