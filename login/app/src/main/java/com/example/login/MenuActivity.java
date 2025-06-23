package com.example.login;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private ArrayList<Menu> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu); // Thay thế bằng layout của bạn

        // Ánh xạ RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo danh sách menu
        menuList = new ArrayList<>();

        // Khởi tạo Adapter
        menuAdapter = new MenuAdapter(menuList);
        recyclerView.setAdapter(menuAdapter);

        // Lấy dữ liệu từ Firebase
        getMenuData();
    }

    private void getMenuData() {
        // Kết nối đến Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference menuRef = database.getReference("menu");

        // Lắng nghe sự thay đổi dữ liệu từ Firebase
        menuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Lấy dữ liệu từ Firebase
                menuList.clear();  // Xóa dữ liệu cũ trước khi thêm dữ liệu mới
                for (DataSnapshot daySnapshot : dataSnapshot.getChildren()) {
                    String day = daySnapshot.getKey();  // Lấy ngày trong tuần
                    Menu menu = daySnapshot.getValue(Menu.class);  // Lấy thực đơn cho ngày đó
                    if (menu != null) {
                        menu.setDay(day);  // Gán ngày cho thực đơn
                        menuList.add(menu);  // Thêm vào danh sách
                    }
                }
                menuAdapter.notifyDataSetChanged();  // Cập nhật RecyclerView
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Nếu có lỗi khi lấy dữ liệu
                Toast.makeText(MenuActivity.this, "Lỗi tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
