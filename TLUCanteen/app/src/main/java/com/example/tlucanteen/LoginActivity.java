package com.example.tlucanteen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;

    private FirebaseDatabase database;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo Firebase Realtime Database
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");

        // Ánh xạ các EditText và Button
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        // Sự kiện khi nhấn nút Đăng nhập
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                } else {
                    checkLogin(email, password);
                }
            }
        });
    }

    private void checkLogin(String email, String password) {
        // Kiểm tra dữ liệu người dùng trong Firebase
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isValidUser = false;

                // Lặp qua tất cả người dùng trong Realtime Database
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String dbEmail = userSnapshot.child("email").getValue(String.class);
                    String dbPassword = userSnapshot.child("password").getValue(String.class);

                    // So sánh email và password
                    if (email.equals(dbEmail) && password.equals(dbPassword)) {
                        isValidUser = true;
                        break;
                    }
                }

                // Kiểm tra xem có người dùng hợp lệ không
                if (isValidUser) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    // Chuyển đến màn hình chính (MainActivity)
                    // startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    // finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu không đúng.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "Lỗi khi kết nối đến Firebase.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
