package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private ArrayList<Menu> menuList;

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDay, tvDish1, tvDish2, tvDish3;
        public Button btnDetail;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tv_day);
            tvDish1 = itemView.findViewById(R.id.tv_dish1);
            tvDish2 = itemView.findViewById(R.id.tv_dish2);
            tvDish3 = itemView.findViewById(R.id.tv_dish3);
            btnDetail = itemView.findViewById(R.id.btn_detail);
        }
    }

    public MenuAdapter(ArrayList<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        Menu menu = menuList.get(position);

        // Use getters to access menu data
        holder.tvDay.setText(menu.getDay());
        holder.tvDish1.setText(menu.getDish1());
        holder.tvDish2.setText(menu.getDish2());
        holder.tvDish3.setText(menu.getDish3());

        // Handle button click
        holder.btnDetail.setOnClickListener(v -> {
            // Navigate to the details page
            // You can add logic to navigate to a new Activity or Fragment here
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
