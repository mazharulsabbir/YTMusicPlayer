package com.roman_tsisyk.youtube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roman_tsisyk.youtube.R

/** Videos to play inside the list */
private val videoIds = listOf(
    "6JYIGclVQdw",
    "LvetJ9U_tVY",
    "6JYIGclVQdw",
    "LvetJ9U_tVY",
    "6JYIGclVQdw",
    "LvetJ9U_tVY",
    "6JYIGclVQdw",
    "LvetJ9U_tVY",
    "6JYIGclVQdw",
    "LvetJ9U_tVY",
    "6JYIGclVQdw",
    "LvetJ9U_tVY"
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_example)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        recyclerView.adapter = RecyclerViewAdapter(videoIds)
    }
}