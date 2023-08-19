package com.roman_tsisyk.youtube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_example)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        fetchVideoIds { ids ->
            recyclerView.adapter = RecyclerViewAdapter(ids)
        }
    }

    private fun fetchVideoIds(callback: (List<String>) -> Unit) {
        val request = Request.Builder()
            .url("$BASE_URL$PATH_TO_FILE")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // TODO Handle call error
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                handleResponse(response, callback)
            }
        })
    }

    private fun handleResponse(response: Response, callback: (List<String>) -> Unit) {
        val responseBody = response.body?.string()

        if (response.isSuccessful && !responseBody.isNullOrEmpty()) {
            try {
                val videoResponse = gson.fromJson(responseBody, GithubVideoResponse::class.java)
                runOnUiThread {
                    callback(videoResponse.videoIds)
                }
            } catch (e: Exception) {
                // TODO Handle JSON parsing error
                e.printStackTrace()
            }
        } else {
            // TODO Handle server error
        }
    }

    companion object {
        private const val YOUR_GITHUB_USERNAME = "RomanTsisyk"
        private const val REPO_NAME = "jsons"
        private const val BRANCH_NAME = "master"
        private const val PATH_TO_FILE = "/test_youtube.json"
        private const val BASE_URL =
            "https://raw.githubusercontent.com/$YOUR_GITHUB_USERNAME/$REPO_NAME/$BRANCH_NAME"
    }
}
