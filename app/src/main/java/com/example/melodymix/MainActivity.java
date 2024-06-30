package com.example.melodymix;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PreSearch.OnSearchButtonClickListener {

    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.view_pager);
        setUpViewPager();

        bottomNavigationView.setOnItemSelectedListener((item) -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                viewPager.setCurrentItem(0);
            } else if (itemId == R.id.pre_search) {
                viewPager.setCurrentItem(1);
            } else if (itemId == R.id.library) {
                viewPager.setCurrentItem(2);
            }
            return true;
        });
    }

    @Override
    public void onSearchButtonClicked() {
        adapter.setFragment(1, new Search());
        viewPager.setCurrentItem(1, true); // Chuyển đến trang Search
    }
    private void setUpViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);
    }
}
