package com.example.twjoin.data.database

import android.content.Context
import com.example.twjoin.data.entities.ListEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class ListRepository(private val context: Context) {
    fun getMockListData(): List<ListEntity> {
        val jsonString: String
        try {
            val inputStream = context.assets.open("list_mock_data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return emptyList()
        }

        val listEntityType = object : TypeToken<List<ListEntity>>() {}.type
        return Gson().fromJson(jsonString, listEntityType)
    }
}