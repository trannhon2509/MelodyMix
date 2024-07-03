package com.example.melodymix;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {
    private ArrayList<Song> mSongs;
    private RecyclerView mRecylerSong;
    private SongAdapter mSongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_view);
        mRecylerSong = findViewById(R.id.recylerSong);
        mSongs = new ArrayList<>();
        createSongList();
        mSongAdapter = new SongAdapter(mSongs);
        mRecylerSong.setAdapter(mSongAdapter);
        mRecylerSong.setLayoutManager(new LinearLayoutManager(this));
        Log.d("SongActivity", "Activity created and RecyclerView set");
    }

    private void createSongList() {
        mSongs.add(new Song("Phai Chi Tay Thoi", "Unknown Artist"));
        Log.d("SongActivity", "Song list created with " + mSongs.size() + " songs");
    }
    public void onImageButtonClick(View view) {
        // Intent to navigate to another activity
        Intent intent = new Intent(SongActivity.this, AnotherActivity.class);
        startActivity(intent);
    }
    public void onPauseButtonClick(View view) {
        Toast.makeText(this, "Pause Button Clicked", Toast.LENGTH_SHORT).show();
    }

    public void onHomeButtonClick(View view) {
        Toast.makeText(this, "Home Button Clicked", Toast.LENGTH_SHORT).show();
    }

    public void onSearchButtonClick(View view) {
        Toast.makeText(this, "Search Button Clicked", Toast.LENGTH_SHORT).show();
    }

    public void onLibraryButtonClick(View view) {
        Toast.makeText(this, "Library Button Clicked", Toast.LENGTH_SHORT).show();
    }

    public void onButtonClick(View view) {
        Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
    }



    public void onBluetoothButtonClick(View view) {
        Toast.makeText(this, "Bluetooth Button Clicked", Toast.LENGTH_SHORT).show();
    }

    public void onPauseMediaButtonClick(View view) {
        Toast.makeText(this, "Pause Media Button Clicked", Toast.LENGTH_SHORT).show();
    }
}
