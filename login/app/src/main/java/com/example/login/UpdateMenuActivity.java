package com.example.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateMenuActivity extends AppCompatActivity {

    private EditText etMenuName, etNotes;
    private MultiAutoCompleteTextView multiSpinnerFoods;
    private Spinner spinnerDays;
    private Button btnUpdateMenu;
    private String menuDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu);

        // Khởi tạo các view
        etMenuName = findViewById(R.id.et_menu_name);
        multiSpinnerFoods = findViewById(R.id.multi_spinner_foods);
        etNotes = findViewById(R.id.et_notes);
        spinnerDays = findViewById(R.id.spinner_days);
        btnUpdateMenu = findViewById(R.id.btn_update_menu);

        // Lấy dữ liệu từ Intent
        menuDay = getIntent().getStringExtra("menu_day");
        String menuName = getIntent().getStringExtra("menu_name");
        String foods = getIntent().getStringExtra("food");
        String notes = getIntent().getStringExtra("notes");

        // Hiển thị dữ liệu lên giao diện
        etMenuName.setText(menuName);
        multiSpinnerFoods.setText(foods); // Đảm bảo rằng đây là chuỗi hợp lệ cho MultiAutoCompleteTextView
        etNotes.setText(notes);

        // Nút cập nhật thực đơn
        btnUpdateMenu.setOnClickListener(v -> {
            // Lấy dữ liệu từ các view
            String updatedMenuName = etMenuName.getText().toString();
            String updatedFoods = multiSpinnerFoods.getText().toString();
            String updatedNotes = etNotes.getText().toString();

            // Kiểm tra nếu dữ liệu trống
            if (updatedMenuName.isEmpty() || updatedFoods.isEmpty() || updatedNotes.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cập nhật vào Firebase
            DatabaseReference menuRef = FirebaseDatabase.getInstance().getReference("menu").child(menuDay);

            menuRef.child("name").setValue(updatedMenuName)
                    .addOnSuccessListener(aVoid -> {
                        // Cập nhật món ăn
                        menuRef.child("food").setValue(updatedFoods.split(","))
                                .addOnSuccessListener(aVoid1 -> {
                                    // Cập nhật ghi chú
                                    menuRef.child("notes").setValue(updatedNotes.split(","))
                                            .addOnSuccessListener(aVoid2 -> {
                                                // Cập nhật thành công
                                                Toast.makeText(UpdateMenuActivity.this, "Cập nhật thực đơn thành công!", Toast.LENGTH_SHORT).show();
                                                finish();
                                            })
                                            .addOnFailureListener(e -> {
                                                Toast.makeText(UpdateMenuActivity.this, "Lỗi khi cập nhật ghi chú: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            });
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(UpdateMenuActivity.this, "Lỗi khi cập nhật món ăn: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(UpdateMenuActivity.this, "Lỗi khi cập nhật tên thực đơn: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
