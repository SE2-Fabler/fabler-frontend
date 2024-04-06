package com.se2.fabler.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.se2.fabler.R
import com.se2.fabler.TestDataSource
import com.se2.fabler.models.BookData
import com.se2.fabler.models.CredentialsData
import com.se2.fabler.models.UserData
import com.se2.fabler.models.UserDataAll
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class FablerService {
    val serverurl = "http://10.0.0.178:5000/";
    var client = OkHttpClient();
    private val dataSource: TestDataSource = TestDataSource()

    fun searchBooks(query: String, page: Int, itemsPerPage: Int): List<BookData> {
        // Delay for 1 second to simulate network request
        println("reached")
        println(query)
        println(serverurl+"story")
        val request = Request.Builder().url(serverurl+"story").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: java.io.IOException) {
                // Handle failure
                println("FAILURE")
                println(e)
                throw e
            }

            override fun onResponse(call: Call, response: Response) {
                // Handle success
                val js = response.body?.string() ?: ""
                // Process the response data
                println(js)
                val typeToken = object : TypeToken<List<List<String>>>() {}.type
                var result = Gson().fromJson<List<List<String>>>(js, typeToken)
                var books: List<BookData> = listOf()
                for (s in result) {
                    println(s)
                    var bm = 0
                    if (s[7] != null){
                        bm = s[7].toInt()
                    }
                    books = books + BookData(s[0].toInt(), s[1], R.drawable.bg1, s[2], s[3].toInt(), s[5], s[6], false, bm, false)
                }
                println(books)
            }
        })
        return if (page < 10) {
            List(itemsPerPage) { dataSource.books[0] }
        } else {
            listOf()
        }
    }

    fun searchUsers(query: String, page: Int, itemsPerPage: Int): List<UserData> {
        // Delay for 2 second to simulate network request
        Thread.sleep(1000)
        return if (page < 10) {
            return List(itemsPerPage){dataSource.otheruser}
        } else {
            listOf()
        }
    }

    fun getUserDataAll(userId: Int): UserDataAll {
        // Delay for 1 second to simulate network request
        Thread.sleep(1000)
        Log.d("FablerService", "getUserDataAll: $userId")
        return if(userId == 1)
            UserDataAll(dataSource.userdata, dataSource.books, dataSource.books)
        else
            UserDataAll(dataSource.otheruser, dataSource.books, dataSource.books)
    }

    fun authUser(credential: CredentialsData): UserData? {
        // Delay for 1 second to simulate network request
        Thread.sleep(1000)
        return if(dataSource.userdata.username == credential.username)
            dataSource.userdata
        else
            null
    }
}