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
import com.se2.fabler.TestDataSource
private val dataSource: TestDataSource = TestDataSource()

class HttpFablerService : IFablerService {
    private val serverurl = "http://192.168.2.25:5000/"
    private val client = OkHttpClient()

    override suspend fun searchBooks(query: String, page: Int, itemsPerPage: Int): List<BookData> {
        var endpoint = serverurl + "story"
        if (!query.isNullOrBlank()){
            endpoint = endpoint + "?title=" + query
        }
        Log.d("dbg", "reached2")
        Log.d(
            "HttpFablerService",
            "serchbooks() API endpoint: $endpoint\n\tquery: $query, page: $page, itemsPerPage: $itemsPerPage"
        )
        val request = Request.Builder().url(endpoint).build()
        try {
            val response = client.newCall(request).await()
            // Handle success
            val js = response.body?.string() ?: ""
            Log.d("response",js)
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

    override suspend fun searchUsers(query: String, page: Int, itemsPerPage: Int): List<UserData> {
        var endpoint = serverurl + "user"
        if (!query.isNullOrBlank()){
            endpoint = endpoint + "?query=" + query
        }
        Log.d(
            "HttpFablerService",
            "serchusers() API endpoint: $endpoint\n\tquery: $query, page: $page, itemsPerPage: $itemsPerPage"
        )
        val request = Request.Builder().url(endpoint).build()
        try {
            val response = client.newCall(request).await()
            // Handle success
            val js = response.body?.string() ?: ""
            Log.d("response",js)
            // Process the response data
            val typeToken = object : TypeToken<List<List<String>>>() {}.type
            val result = Gson().fromJson<List<List<String>>>(js, typeToken)
            val users: MutableList<UserData> = mutableListOf()
            for (s in result) {
                users.add(UserData(
                    s[0].toInt(),
                    s[1],
                    s[2],
                    s[3],
                    R.drawable.sample_pfp,
                    listOf(),
                    listOf(),
                    true,
                    s[4],
                    s[5],
                    "2021-10-10",
                    true,
                    true,
                    listOf(),
                    listOf()
                ))
            }
            return users
        } catch (e: Exception) {
            Log.d("HttpFablerService", "searchUsers() API failure: $e")
            throw e
        }
    }

    override fun getUserDataAll(userId: Int): UserDataAll {
        //TODO
        Thread.sleep(1000)
        Log.d("HttpFablerService", "getUserDataAll: $userId")
        return if(userId == 1)
            UserDataAll(dataSource.userdata, dataSource.books, dataSource.books)
        else
            UserDataAll(dataSource.otheruser, dataSource.books, dataSource.books)
    }

    override fun authUser(credential: CredentialsData): UserData? {
        //TODO
        Thread.sleep(1000)
        return if(dataSource.userdata.username == credential.username)
            dataSource.userdata
        else
            null
    }

    override fun getFollowers(userId: Int, page: Int, itemsPerPage: Int): List<UserData> {
        TODO("Not yet implemented")
    }

    override fun getFollowing(userId: Int, page: Int, itemsPerPage: Int): List<UserData> {
        TODO("Not yet implemented")
    }
}