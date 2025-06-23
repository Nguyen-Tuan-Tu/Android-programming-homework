package com.example.tlucanteenconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PasswordChangedSuccess_Activity extends AppCompatActivity {

    private Button btnXacNhanSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_changed_success);

        btnXacNhanSuccess = findViewById(R.id.btn_xac_nhan_success);

        btnXacNhanSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại màn hình đăng nhập chính
                Intent intent = new Intent(PasswordChangedSuccess_Activity.this, Login_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Xóa các activity trên stack
                startActivity(intent);
                finish(); // Kết thúc activity hiện tại
            }
        });
    }
}