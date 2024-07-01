package com.example.melodymix;

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

public class ProfileSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_setting);
        // Xử lý khi nhấn nút Back
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đoạn mã để trở về activity_main
                Intent intent = new Intent(ProfileSetting.this, MainActivity.class);
                startActivity(intent);
                finish(); // Kết thúc activity hiện tại để ngăn người dùng quay lại bằng nút back
            }
        });

        // Xử lý khi nhấn nút View Profile
        Button viewProfileButton = findViewById(R.id.viewProfileButton);
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đoạn mã để trở về activity_main
                Intent intent = new Intent(ProfileSetting.this, MainActivity.class);
                startActivity(intent);
                finish(); // Kết thúc activity hiện tại để ngăn người dùng quay lại bằng nút back
            }
        });
    }
}