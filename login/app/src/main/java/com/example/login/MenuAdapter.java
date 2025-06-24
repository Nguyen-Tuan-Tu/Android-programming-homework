package com.example.login;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private List<Menu> menuList;

    public MenuAdapter(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        Menu menu = menuList.get(position);
        holder.dayTextView.setText(menu.getDay());
        holder.nameTextView.setText(menu.getName());
        holder.foodTextView.setText(String.join(", ", menu.getFood()));
        holder.notesTextView.setText(String.join(", ", menu.getNotes()));

        // Bắt sự kiện khi click vào item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MenuDetailActivity.class);
            // truyền key `day` để truy vấn chi tiết
            intent.putExtra("menu_day", menu.getDay());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView dayTextView, nameTextView, foodTextView, notesTextView;

        public MenuViewHolder(View itemView) {
            super(itemView);
            dayTextView  = itemView.findViewById(R.id.tv_day);
            nameTextView = itemView.findViewById(R.id.tv_name);
            foodTextView = itemView.findViewById(R.id.tv_food);
            notesTextView= itemView.findViewById(R.id.tv_notes);
        }
    }
}
