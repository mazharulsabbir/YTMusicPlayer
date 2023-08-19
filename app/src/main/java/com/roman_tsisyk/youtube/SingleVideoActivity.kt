package com.roman_tsisyk.youtube

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class SingleVideoActivity : AppCompatActivity() {

    private lateinit var youTubePlayerView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_video)

        youTubePlayerView = findViewById(R.id.single_youtube_player_view)
        youTubePlayerView.enableBackgroundPlayback(true)

        if (Intent.ACTION_SEND == intent.action && "text/plain" == intent.type) {
            val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
            if (sharedText != null && isYouTubeLink(sharedText)) {
                val videoId = extractVideoId(sharedText)
                initializePlayer(videoId)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Manually release the player when the activity is destroyed
        youTubePlayerView.release()
    }

    private fun isYouTubeLink(url: String): Boolean {
        return url.contains("youtube.com/watch?v=") || url.contains("youtu.be/")
    }

    private fun extractVideoId(url: String): String {
        if (url.contains("youtube.com/watch?v=")) {
            return url.split("v=")[1].substring(0, 11)
        } else if (url.contains("youtu.be/")) {
            return url.split("be/")[1].substring(0, 11)
        }
        return ""
    }

    private fun initializePlayer(videoId: String) {
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }
}