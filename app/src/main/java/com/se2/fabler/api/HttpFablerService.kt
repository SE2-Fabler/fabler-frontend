package com.se2.fabler.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.se2.fabler.R
import com.se2.fabler.models.BookData
import com.se2.fabler.models.CredentialsData
import com.se2.fabler.models.UserData
import com.se2.fabler.models.UserDataAll
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.gildor.coroutines.okhttp.await

class HttpFablerService : IFablerService {
    private val serverurl = "http://10.0.0.178:5000/"
    private val client = OkHttpClient()

    override suspend fun searchBooks(query: String, page: Int, itemsPerPage: Int): List<BookData> {
        val endpoint = serverurl + "story"
        Log.d(
            "HttpFablerService",
            "serchbooks() API endpoint: $endpoint\n\tquery: $query, page: $page, itemsPerPage: $itemsPerPage"
        )
        val request = Request.Builder().url(endpoint).build()
        try {
            val response = client.newCall(request).await()
            // Handle success
            val js = response.body?.string() ?: ""
            // Process the response data
            val typeToken = object : TypeToken<List<List<String>>>() {}.type
            val result = Gson().fromJson<List<List<String>>>(js, typeToken)
            val books: MutableList<BookData> = mutableListOf()
            for (s in result) {
                books.add(BookData(
                    s[0].toInt(),
                    s[1],
                    R.drawable.bg1,
                    s[2],
                    s[3].toInt(),
                    s[5],
                    s[6],
                    false,
                    s[7].toInt(),
                    false
                ))
            }
            return books
        } catch (e: Exception) {
            Log.d("HttpFablerService", "searchBooks() API failure: $e")
            throw e
        }
    }

    override fun searchUsers(query: String, page: Int, itemsPerPage: Int): List<UserData> {
        TODO("Not yet implemented")
    }

    override fun getUserDataAll(userId: Int): UserDataAll {
        TODO("Not yet implemented")
    }

    override fun authUser(credential: CredentialsData): UserData? {
        TODO("Not yet implemented")
    }

    override fun getFollowers(userId: Int, page: Int, itemsPerPage: Int): List<UserData> {
        TODO("Not yet implemented")
    }

    override fun getFollowing(userId: Int, page: Int, itemsPerPage: Int): List<UserData> {
        TODO("Not yet implemented")
    }
}