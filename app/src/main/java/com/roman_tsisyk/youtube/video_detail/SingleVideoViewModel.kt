package com.roman_tsisyk.youtube.video_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SingleVideoViewModel : ViewModel() {

    private val _videoId = MutableLiveData<String>()
    val videoId: LiveData<String> = _videoId

    fun processSharedText(sharedText: String) {
        if (isYouTubeLink(sharedText)) {
            extractVideoId(sharedText)?.let { id ->
                _videoId.value = id
            }
        }
    }

    private fun isYouTubeLink(url: String): Boolean {
        return url.contains(YOUTUBE_URL_PREFIX) || url.contains(YOUTUBE_SHORT_URL_PREFIX)
    }

    private fun extractVideoId(url: String): String? {
        return when {
            url.contains(YOUTUBE_URL_PREFIX) -> url.substringAfter(YOUTUBE_URL_PREFIX).take(VIDEO_ID_LENGTH)
            url.contains(YOUTUBE_SHORT_URL_PREFIX) -> url.substringAfter(YOUTUBE_SHORT_URL_PREFIX).take(VIDEO_ID_LENGTH)
            else -> null
        }
    }

    companion object {
        private const val YOUTUBE_URL_PREFIX = "youtube.com/watch?v="
        private const val YOUTUBE_SHORT_URL_PREFIX = "youtu.be/"
        private const val VIDEO_ID_LENGTH = 11
    }
}
