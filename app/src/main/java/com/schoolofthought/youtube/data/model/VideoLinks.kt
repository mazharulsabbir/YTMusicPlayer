package com.schoolofthought.youtube.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoLinks(
    @PrimaryKey val id: String,
    val fullUrl: String,
)
