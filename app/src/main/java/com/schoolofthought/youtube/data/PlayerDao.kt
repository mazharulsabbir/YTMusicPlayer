package com.schoolofthought.youtube.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.schoolofthought.youtube.data.model.VideoLinks

@Dao
interface PlayerDao {
    @Query("SELECT * FROM videolinks")
    fun getVideoLinks(): List<VideoLinks>

    @Insert
    fun insertVideoLink(vararg videoLinks: VideoLinks)

    @Delete
    fun deleteVideoLink(videoLinks: VideoLinks)
}