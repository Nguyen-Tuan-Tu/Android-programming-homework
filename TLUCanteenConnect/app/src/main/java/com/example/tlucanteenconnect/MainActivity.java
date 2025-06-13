package com.example.tlucanteenconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Đảm bảo bạn gọi findViewById trong phương thức onCreate
        Button startButton = findViewById(R.id.startButton);

        // Thiết lập sự kiện click cho Button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển sang MenuActivity
                Intent intent = new Intent(MainActivity.this, Menu_dexuat_activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
    }
}
