package com.roman_tsisyk.youtube.ui.video_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roman_tsisyk.youtube.R
import com.roman_tsisyk.youtube.ui.video_list.adapter.RecyclerViewAdapter


class VideoListActivity : AppCompatActivity() {

    private lateinit var viewModel: VideoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_example)

        viewModel = ViewModelProvider(this)[VideoListViewModel::class.java]

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@VideoListActivity)
        }

        viewModel.videoIds.observe(this) { ids ->
            recyclerView.adapter = RecyclerViewAdapter(ids)
        }

        viewModel.fetchVideoIds()
    }
}
