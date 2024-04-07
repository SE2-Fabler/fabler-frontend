package com.se2.fabler.api

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.se2.fabler.models.BookData
import com.se2.fabler.models.UserData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.io.IOException

// FIXME: Change this, this is just for testing!
private const val PAGE_SIZE_BOOK = 3
private const val PAGE_SIZE_USER = 3

class FablerRepository(private val service: IFablerService) {
    fun getBookSearchResultStream(query: String): Flow<PagingData<BookData>> {
        Log.d("FablerRepository", "New book query: $query")
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE_BOOK,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { BookSearchPagingSource(service, query) }
        ).flow
    }

    fun getUserSearchResultStream(query: String): Flow<PagingData<UserData>> {
        Log.d("FablerRepository", "New user query: $query")
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE_USER,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserSearchPagingSource(service, query) }
        ).flow
    }

    fun getFollowersResultStream(userId: Int, followers: Boolean): Flow<PagingData<UserData>> {
        Log.d("FablerRepository", "New followers list query")
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE_USER,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FollowersPagingSource(service, userId, followers) }
        ).flow
    }
}

// https://medium.com/androiddevelopers/introduction-to-paging-3-0-in-the-mad-skills-series-648f77231121
class BookSearchPagingSource(private val service: IFablerService, private val query: String) :
    PagingSource<Int, BookData>() {
    override fun getRefreshKey(state: PagingState<Int, BookData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookData> {
        val position = params.key ?: 0
        return try {
            Log.d("dbg", "reached")
            val response = withContext(IO) { service.searchBooks(query, position, params.loadSize) }
            Log.d("dbg", response.toString())
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + params.loadSize / PAGE_SIZE_BOOK
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == 0) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

class UserSearchPagingSource(private val service: IFablerService, private val query: String) :
    PagingSource<Int, UserData>() {
    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        val position = params.key ?: 0
        return try {
            val response = withContext(IO) { service.searchUsers(query, position, params.loadSize) }
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + params.loadSize / PAGE_SIZE_USER
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == 0) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

// This class provides pagination  services for both the followers and following lists
class FollowersPagingSource(private val service: IFablerService, private val userId: Int, private val queryFollowing: Boolean = false) :
    PagingSource<Int, UserData>() {
    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        val position = params.key ?: 0
        return try {
            val response = if(queryFollowing) {
                service.getFollowing(userId, position, params.loadSize)
            } else {
                service.getFollowers(userId, position, params.loadSize)
            }
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + params.loadSize / PAGE_SIZE_USER
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == 0) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
