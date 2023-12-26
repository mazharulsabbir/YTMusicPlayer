package com.roman_tsisyk.youtube.ui.video_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.roman_tsisyk.youtube.data.model.GithubVideoResponse
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class VideoListViewModel : ViewModel() {
    private val client = OkHttpClient()
    private val gson = Gson()
    private val _videoIds = MutableLiveData<List<String>>()
    val videoIds: LiveData<List<String>> = _videoIds

    fun fetchVideoIds() {
        val request = Request.Builder()
            .url("$BASE_URL$PATH_TO_FILE")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                // Optionally post a failure state or empty list to _videoIds
            }

            override fun onResponse(call: Call, response: Response) {
                handleResponse(response)
            }
        })
    }

    private fun handleResponse(response: Response) {
        val responseBody = response.body?.string()

        if (response.isSuccessful && !responseBody.isNullOrEmpty()) {
            try {
                val videoResponse = gson.fromJson(responseBody, GithubVideoResponse::class.java)
                _videoIds.postValue(videoResponse.videoIds)
            } catch (e: Exception) {
                e.printStackTrace()
                // Optionally post a failure state or empty list to _videoIds
            }
        }
        // Optionally handle server error case
    }


    companion object {
        private const val YOUR_GITHUB_USERNAME = "RomanTsisyk"
        private const val REPO_NAME = "YTubeBackplay"
        private const val BRANCH_NAME = "master"
        private const val PATH_TO_FILE = "/test_youtube.json"
        private const val BASE_URL =
            "https://raw.githubusercontent.com/$YOUR_GITHUB_USERNAME/$REPO_NAME/$BRANCH_NAME"
    }

}