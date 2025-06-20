package com.example.tcc_ql_monan;

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

public class Sua_TT_Mon_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua_ttmon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editfood), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageButton Back = findViewById(R.id.btn_Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại Activity trước
                Intent intent = new Intent(Sua_TT_Mon_Activity.this, QL_Mon_An_Activity.class);
                startActivity(intent);
            }
        });
        Button CFeditfood = findViewById(R.id.btn_CFeditfood);

        CFeditfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị thông báo
                Toast.makeText(Sua_TT_Mon_Activity.this, "Sửa thông tin món ăn thành công", Toast.LENGTH_SHORT).show();

                // Chuyển về màn hình quản lý món ăn
                Intent intent = new Intent(Sua_TT_Mon_Activity.this, QL_Mon_An_Activity.class);
                startActivity(intent);
            }
        });
    }
}
