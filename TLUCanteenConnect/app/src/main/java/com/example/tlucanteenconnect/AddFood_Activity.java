package com.example.tlucanteenconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddFood_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addfood);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.themmon), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageButton Back = findViewById(R.id.btn_Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại Activity trước
                Intent intent = new Intent(AddFood_Activity.this, ManageFood_Activity.class);
                startActivity(intent);
            }
        });
        Button CFaddfood = findViewById(R.id.btn_CFeditfood);

        CFaddfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị thông báo
                Toast.makeText(AddFood_Activity.this, "Thêm món ăn thành công", Toast.LENGTH_SHORT).show();

                // Chuyển về màn hình quản lý món ăn
                Intent intent = new Intent(AddFood_Activity.this, ManageFood_Activity.class);
                startActivity(intent);
            }
        });
    }
}
