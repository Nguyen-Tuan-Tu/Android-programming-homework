package com.example.tlucanteenconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminMore_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_more);

        Button Managefood = findViewById(R.id.btn_managefood);
        Managefood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại Activity trước
                Intent intent = new Intent(AdminMore_Activity.this, ManageFood_Activity.class);
                startActivity(intent);
            }
        });
        Button ManageOder = findViewById(R.id.btn_ManageOder);
        ManageOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại Activity trước
                Intent intent = new Intent(AdminMore_Activity.this, ManageOder_Activity.class);
                startActivity(intent);
            }
        });
    }
}
