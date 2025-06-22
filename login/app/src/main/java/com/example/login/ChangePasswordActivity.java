package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText newPasswordEditText, confirmPasswordEditText;
    private Button confirmButton;
    private String email;  // Email được truyền từ màn hình OTP

    private FirebaseDatabase database;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPasswordEditText = findViewById(R.id.et_new_password);
        confirmPasswordEditText = findViewById(R.id.et_confirm_password);
        confirmButton = findViewById(R.id.btn_confirm);

        // Lấy email từ Intent
        email = getIntent().getStringExtra("email");

        // Khởi tạo Firebase Realtime Database
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");

        confirmButton.setOnClickListener(v -> {
            String newPassword = newPasswordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (newPassword.equals(confirmPassword)) {
                // Cập nhật mật khẩu trong Firebase
                updatePasswordInFirebase(email, newPassword);

                // Chuyển sang màn hình thông báo thành công
                Intent intent = new Intent(ChangePasswordActivity.this, PasswordChangedSuccessActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(ChangePasswordActivity.this, "Mật khẩu không khớp, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Phương thức cập nhật mật khẩu vào Firebase
    private void updatePasswordInFirebase(String email, String newPassword) {
        usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Cập nhật mật khẩu
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        userSnapshot.getRef().child("password").setValue(newPassword);
                    }
                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu đã được thay đổi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Không tìm thấy người dùng với email này", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ChangePasswordActivity.this, "Lỗi khi cập nhật mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
