package com.example.melodymix;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class MyProfile extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlaylistsAdapter adapter;
    private List<Playlist> playlists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        // Initialize RecyclerView and playlists data
        recyclerView = findViewById(R.id.playlistsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        playlists = new ArrayList<>();
        playlists.add(new Playlist("Study", 7));
        playlists.add(new Playlist("EDM", 10));
        playlists.add(new Playlist("Rock", 11));

        adapter = new PlaylistsAdapter(this, playlists);
        recyclerView.setAdapter(adapter);
    }
}