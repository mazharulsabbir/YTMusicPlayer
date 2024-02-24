package com.schoolofthought.youtube.video_detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.schoolofthought.youtube.R

class SingleVideoActivity : AppCompatActivity() {

    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var viewModel: SingleVideoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_video)

        viewModel = ViewModelProvider(this)[SingleVideoViewModel::class.java]

        youTubePlayerView = findViewById(R.id.single_youtube_player_view)
        youTubePlayerView.enableBackgroundPlayback(true)

        // Observe the ViewModel's LiveData
        viewModel.videoId.observe(this) { videoId ->
            initializePlayer(videoId)
        }

        // Process the intent
        intent?.takeIf { it.action == Intent.ACTION_SEND && it.type == "text/plain" }
            ?.getStringExtra(Intent.EXTRA_TEXT)?.let { sharedText ->
                viewModel.processSharedText(sharedText)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        youTubePlayerView.release()
    }


    private fun initializePlayer(videoId: String) {
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }
}