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
import okhttp3.FormBody

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
                    0,
                    0,
                    true,
                    s[4],
                    s[5],
                    "2021-10-10",
                    true,
                    true
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

    override suspend fun authUser(credential: CredentialsData): UserData? {
        //TODO
        var endpoint = serverurl + "auth/login"
        var formBody = FormBody.Builder()
            .add("username",credential.username)
            .add("password",credential.password)
            .build();
        Log.d(
            "HttpFablerService",
            "authUser() API endpoint: $endpoint\n\t"
        )
        val request = Request.Builder().url(endpoint).post(formBody).build()
        try {
            val response = client.newCall(request).await()
            // Handle success
            val js = response.body?.string() ?: ""
            Log.d("response", js)// Process the response data
            if (js == "Incorrect password." || js == "Incorrect username."){
                //HANDLE ERROR
            } else {
                val typeToken = object : TypeToken<List<String>>() {}.type
                val result = Gson().fromJson<List<String>>(js, typeToken)
                val user = UserData(
                    result[0].toInt(),
                    result[1],
                    result[2],
                    result[3],
                    R.drawable.sample_pfp,
                    0,
                    0,
                    true,
                    result[4],
                    result[5],
                    "2021-10-10",
                    true,
                    true
                )
                Log.d("HttpFablerService", user.toString())
            }
        } catch (e: Exception) {
            Log.d("HttpFablerService", "authUser() API failure: $e")
            throw e
        }
        return if(dataSource.userdata.username == credential.username)
            dataSource.userdata
        else
            null
    }
    override suspend fun registerUser(credential: CredentialsData, email: String) {
        var endpoint = serverurl + "auth/register"
        var formBody = FormBody.Builder()
            .add("username",credential.username)
            .add("email", email)
            .add("password",credential.password)
            .build();
        Log.d(
            "HttpFablerService",
            "registerUser() API endpoint: $endpoint\n\t"
        )
        val request = Request.Builder().url(endpoint).post(formBody).build()
        try {
            val response = client.newCall(request).await()
            // Handle success
            val js = response.body?.string() ?: ""
            Log.d("response", js)// Process the response data
        } catch (e: Exception) {
            Log.d("HttpFablerService", "authUser() API failure: $e")
            throw e
        }
    }

    override suspend fun getFollowers(userId: Int, page: Int, itemsPerPage: Int): List<UserData> {
        val endpoint = serverurl + "user/followers?id=" + userId
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
                    0,
                    0,
                    true,
                    s[4],
                    s[5],
                    "2021-10-10",
                    true,
                    true
                ))
            }
            return users
        } catch (e: Exception) {
            Log.d("HttpFablerService", "searchUsers() API failure: $e")
            throw e
        }
    }

    override suspend fun getFollowing(userId: Int, page: Int, itemsPerPage: Int): List<UserData> {
        val endpoint = serverurl + "user/following?id=" + userId
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
                    0,
                    0,
                    true,
                    s[4],
                    s[5],
                    "2021-10-10",
                    true,
                    true
                ))
            }
            return users
        } catch (e: Exception) {
            Log.d("HttpFablerService", "searchUsers() API failure: $e")
            throw e
        }
    }
    override suspend fun getBookmarked(userId: Int, page: Int, itemsPerPage: Int): List<BookData> {
        var endpoint = serverurl + "story/bookmark?id=" + userId
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
    override suspend fun setFollow(userId: Int, followerID: Int, set: Boolean) {
        var endpoint = serverurl + "user/following"
        if (set) {
            val formBody = FormBody.Builder()
                .add("uid", userId.toString())
                .add("fid", followerID.toString())
                .build();
            val request = Request.Builder().url(endpoint).post(formBody).build()
            try {
                val response = client.newCall(request).await()
                // Handle success
                val js = response.body?.string() ?: ""
                Log.d("response", js)// Process the response data
            } catch (e: Exception) {
                Log.d("HttpFablerService", "authUser() API failure: $e")
                throw e
            }
        } else {
            endpoint += "?=" + followerID
            val request = Request.Builder().url(endpoint).delete().build()
            try {
                val response = client.newCall(request).await()
                // Handle success
                val js = response.body?.string() ?: ""
                Log.d("response", js)// Process the response data
            } catch (e: Exception) {
                Log.d("HttpFablerService", "authUser() API failure: $e")
                throw e
            }
        }
    }
    override suspend fun setBookmark(userId: Int, bookID: Int, set: Boolean) {
        var endpoint = serverurl + "user/bookmark"
        if (set) {
            val formBody = FormBody.Builder()
                .add("uid", userId.toString())
                .add("bid", bookID.toString())
                .build();
            val request = Request.Builder().url(endpoint).post(formBody).build()
            try {
                val response = client.newCall(request).await()
                // Handle success
                val js = response.body?.string() ?: ""
                Log.d("response", js)// Process the response data
            } catch (e: Exception) {
                Log.d("HttpFablerService", "authUser() API failure: $e")
                throw e
            }
        } else {
            endpoint += "?=" + bookID
            val request = Request.Builder().url(endpoint).delete().build()
            try {
                val response = client.newCall(request).await()
                // Handle success
                val js = response.body?.string() ?: ""
                Log.d("response", js)// Process the response data
            } catch (e: Exception) {
                Log.d("HttpFablerService", "authUser() API failure: $e")
                throw e
            }
        }
    }
    override suspend fun deleteBook(bookID: Int) {
        val endpoint = serverurl + "user/story?=" + bookID
        val request = Request.Builder().url(endpoint).delete().build()
        try {
            val response = client.newCall(request).await()
            // Handle success
            val js = response.body?.string() ?: ""
            Log.d("response", js)// Process the response data
        } catch (e: Exception) {
            Log.d("HttpFablerService", "authUser() API failure: $e")
            throw e
        }
    }
}