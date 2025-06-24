package com.example.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuDetailActivity extends AppCompatActivity {

    private TextView tvDay, tvName, tvFood, tvNotes;
    private Button btnUpdate, btnDelete;
    private DatabaseReference menuRef;
    private String dayKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        tvDay   = findViewById(R.id.tv_detail_day);
        tvName  = findViewById(R.id.tv_detail_name);
        tvFood  = findViewById(R.id.tv_detail_food);
        tvNotes = findViewById(R.id.tv_detail_notes);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        dayKey = getIntent().getStringExtra("menu_day");
        menuRef = FirebaseDatabase.getInstance().getReference("menu").child(dayKey);

        loadDetail();

        btnUpdate.setOnClickListener(v -> {
            // Chuyển đến màn hình UpdateMenuActivity và truyền dữ liệu hiện tại
            Intent intent = new Intent(MenuDetailActivity.this, UpdateMenuActivity.class);
            intent.putExtra("menu_day", dayKey); // Truyền ngày thực đơn
            intent.putExtra("menu_name", tvName.getText().toString()); // Truyền tên thực đơn
            intent.putExtra("food", tvFood.getText().toString()); // Truyền danh sách món ăn
            intent.putExtra("notes", tvNotes.getText().toString()); // Truyền ghi chú
            startActivity(intent);
            finish();
        });



        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận xóa")
                    .setMessage("Bạn có chắc muốn xóa thực đơn này không?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        menuRef.removeValue((error, ref) -> {
                            if (error == null) {
                                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });
    }

    private void loadDetail() {
        menuRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvDay.setText(dayKey);
                String name = snapshot.child("name").getValue(String.class);
                tvName.setText(name);

                List<String> foods = new ArrayList<>();
                for (DataSnapshot f : snapshot.child("food").getChildren()) {
                    foods.add(f.getValue(String.class));
                }
                tvFood.setText(String.join(", ", foods));

                List<String> notes = new ArrayList<>();
                for (DataSnapshot n : snapshot.child("notes").getChildren()) {
                    notes.add(n.getValue(String.class));
                }
                tvNotes.setText(String.join(", ", notes));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MenuDetailActivity.this, "Lỗi khi tải chi tiết", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
