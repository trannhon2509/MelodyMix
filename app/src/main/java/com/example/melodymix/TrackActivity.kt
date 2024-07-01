package com.example.melodymix

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TrackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.track)

        val textShare: TextView = findViewById(R.id.textShare)
        textShare.setOnClickListener {
            val intent = Intent(this@TrackActivity, SongShareActivity::class.java)
            startActivity(intent)
        }
    }
}
