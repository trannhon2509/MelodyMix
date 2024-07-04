package com.example.melodymix;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton buttonPlay, buttonPrevious, buttonNext, buttonRepeat, buttonShuffle;
    ImageView imageView;
    TextView textTitle, textCurrentTime, textTotalTime;
    SeekBar playerSeeBarTime;
    boolean isRepeatAll = true;
    boolean isRepeatOne = false;
    boolean isShuffle = false; // Added shuffle boolean

    static MediaPlayer mMediaPlay;
    private Runnable runnable;
    private AudioManager mAudioManager;
    int currentIndex = 0;

    // Make songs an instance variable
    private ArrayList<Integer> songs;
    private ArrayList<Integer> originalSongList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ImageView imageTrack = findViewById(R.id.imageTrack);
        imageTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TrackActivity.class);
                startActivity(intent);
            }
        });

        // Initializing views
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonNext = findViewById(R.id.buttonNext);
        textTitle = findViewById(R.id.textTitle);
        textCurrentTime = findViewById(R.id.textCurrentTime);
        textTotalTime = findViewById(R.id.textTotalTime);
        imageView = findViewById(R.id.imageView);
        playerSeeBarTime = findViewById(R.id.playerSeeBarTime);
        buttonRepeat = findViewById(R.id.buttonRepeat);
        buttonShuffle = findViewById(R.id.buttonShuffle); // Initialize shuffle button

        // Initializing the songs ArrayList
        songs = new ArrayList<>();
        songs.add(0, R.raw.dltted);
        songs.add(1, R.raw.hdlde);
        songs.add(2, R.raw.ndtnct);
        songs.add(3, R.raw.tkett);

        // Save the original song list
        originalSongList = new ArrayList<>(songs);

        mMediaPlay = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerSeeBarTime.setMax(mMediaPlay.getDuration());
                if (mMediaPlay != null && mMediaPlay.isPlaying()) {
                    mMediaPlay.pause();
                    buttonPlay.setImageResource(R.drawable.baseline_play);
                } else {
                    mMediaPlay.start();
                    buttonPlay.setImageResource(R.drawable.baseline_pause);
                }
                SongNames();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNextSong();
            }
        });

        buttonRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRepeatAll) {
                    isRepeatAll = false;
                    isRepeatOne = true;
                    buttonRepeat.setImageResource(R.drawable.baseline_repeatone);
                } else if (isRepeatOne) {
                    isRepeatAll = true;
                    isRepeatOne = false;
                    buttonRepeat.setImageResource(R.drawable.baseline_repeat);
                }
                setMediaPlayerCompletionListener();
            }
        });

        buttonShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShuffle) {
                    isShuffle = false;
                    buttonShuffle.setImageResource(R.drawable.baseline_shuffleoff);
                    // Restore the original order
                    songs = new ArrayList<>(originalSongList);
                } else {
                    isShuffle = true;
                    buttonShuffle.setImageResource(R.drawable.baseline_shuffle);
                    // Shuffle the songs list
                    Collections.shuffle(songs);
                }
            }
        });

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlay != null) {
                    buttonPlay.setImageResource(R.drawable.baseline_pause);
                }
                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    currentIndex = songs.size() - 1;
                }
                if (mMediaPlay.isPlaying()) {
                    mMediaPlay.stop();
                }
                mMediaPlay = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
                mMediaPlay.start();
                SongNames();
                setMediaPlayerCompletionListener();
            }
        });

        // Handler to update UI
        handler.postDelayed(updateUI, 1000);

        setMediaPlayerCompletionListener();
    }

    private void playNextSong() {
        if (mMediaPlay != null) {
            buttonPlay.setImageResource(R.drawable.baseline_pause);
        }
        if (isShuffle) {
            // Shuffle mode: pick a random song
            Random random = new Random();
            currentIndex = random.nextInt(songs.size());
        } else {
            // Regular mode: play the next song in the list
            if (currentIndex < songs.size() - 1) {
                currentIndex++;
            } else {
                currentIndex = 0;
            }
        }
        if (mMediaPlay.isPlaying()) {
            mMediaPlay.stop();
        }
        mMediaPlay = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
        mMediaPlay.start();
        SongNames();
        setMediaPlayerCompletionListener();
    }

    private void SongNames() {
        if (currentIndex == 0) {
            textTitle.setText("DLTTED");
            imageView.setImageResource(R.drawable.music1);
        }
        if (currentIndex == 1) {
            textTitle.setText("HDLDE");
            imageView.setImageResource(R.drawable.music2);
        }
        if (currentIndex == 2) {
            textTitle.setText("NDTNCT");
            imageView.setImageResource(R.drawable.music3);
        }
        if (currentIndex == 3) {
            textTitle.setText("TKETT");
            imageView.setImageResource(R.drawable.music4);
        }

        // SeekBar duration and total time
        mMediaPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                playerSeeBarTime.setMax(mMediaPlay.getDuration());
                textTotalTime.setText(formatTime(mMediaPlay.getDuration()));
                mMediaPlay.start();
            }
        });

        playerSeeBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mMediaPlay.seekTo(progress);
                    playerSeeBarTime.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setMediaPlayerCompletionListener() {
        mMediaPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (isRepeatOne) {
                    mMediaPlay.start();
                } else {
                    playNextSong();
                }
            }
        });
    }

    private String formatTime(int millis) {
        int minutes = (millis / 1000) / 60;
        int seconds = (millis / 1000) % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    private final Handler handler = new Handler();
    private final Runnable updateUI = new Runnable() {
        @Override
        public void run() {
            if (mMediaPlay != null && mMediaPlay.isPlaying()) {
                int currentPosition = mMediaPlay.getCurrentPosition();
                playerSeeBarTime.setProgress(currentPosition);
                textCurrentTime.setText(formatTime(currentPosition));
            }
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlay != null) {
            handler.removeCallbacks(updateUI);
            mMediaPlay.release();
            mMediaPlay = null;
        }
    }
}
