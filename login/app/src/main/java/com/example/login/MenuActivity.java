package com.example.login;

import android.content.Intent;
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
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<Menu> menuList;
    private DatabaseReference menuRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Khởi tạo RecyclerView và adapter
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        menuList = new ArrayList<>();
        menuAdapter = new MenuAdapter(menuList);
        recyclerView.setAdapter(menuAdapter);

        // Khởi tạo Firebase Database reference
        menuRef = FirebaseDatabase.getInstance().getReference("menu");

        // Lấy dữ liệu từ Firebase lần đầu tiên khi Activity được tạo
        loadMenuData();

        // Xử lý sự kiện khi nhấn nút "Thêm thực đơn"
        findViewById(R.id.btn_add_menu).setOnClickListener(v -> {
            // Chuyển đến Activity thêm thực đơn
            Intent intent = new Intent(MenuActivity.this, AddMenuActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Lấy lại dữ liệu khi Activity quay lại từ AddMenuActivity
        loadMenuData();
    }

    // Phương thức để lấy và cập nhật dữ liệu từ Firebase
    private void loadMenuData() {
        menuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                menuList.clear();
                // Lặp qua các ngày trong tuần và lấy thực đơn
                for (DataSnapshot daySnapshot : dataSnapshot.getChildren()) {
                    String day = daySnapshot.getKey();  // "monday", "tuesday", ...
                    String name = daySnapshot.child("name").getValue(String.class);

                    List<String> food = new ArrayList<>();
                    for (DataSnapshot foodSnapshot : daySnapshot.child("food").getChildren()) {
                        food.add(foodSnapshot.getValue(String.class));
                    }

                    List<String> notes = new ArrayList<>();
                    for (DataSnapshot noteSnapshot : daySnapshot.child("notes").getChildren()) {
                        notes.add(noteSnapshot.getValue(String.class));
                    }

                    // Tạo đối tượng Menu và thêm vào list
                    Menu menu = new Menu(day, name, food, notes);
                    menuList.add(menu);
                }
                menuAdapter.notifyDataSetChanged(); // Cập nhật giao diện
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi nếu có
                Toast.makeText(MenuActivity.this, "Lỗi khi tải thực đơn!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
