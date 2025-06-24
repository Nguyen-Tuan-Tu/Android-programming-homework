package com.example.tlucanteenconnect; // Đảm bảo đúng package name của bạn

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationSuccessActivity extends AppCompatActivity {

    private Button btnBackToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_success);

        btnBackToMain = findViewById(R.id.btnBackToMain);

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the main login/home screen (or whatever the "trang chính" is)
                // For now, let's assume it goes back to the MainActivity
                Intent intent = new Intent(RegistrationSuccessActivity.this, DangKiActivity.class); // <-- Dòng này đã được sửa
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear back stack
                startActivity(intent);
                finish(); // Finish this activity
            }
        });
        Button BackToLogin = findViewById(R.id.btnBackToMain);
        BackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity Menu Đồ uống
                Intent intent = new Intent(RegistrationSuccessActivity.this, Login_Activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
    }


    // Optionally override onBackPressed to prevent going back to this screen
    @Override
    public void onBackPressed() {
        // Do nothing or navigate to the main screen
        btnBackToMain.performClick(); // Simulate clicking the back to main button
    }
}