package com.example.studooov2.UserSignUpLogin.InternetOperations.Data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.ApiRequest
import com.example.studooov2.UserSignUpLogin.Models.regularUser
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class UserPagingSource(
        private val api : ApiRequest,
        private val letter: String = "l"
        )
    : PagingSource<Int, regularUser>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, regularUser> {
        val currentPage = params.key ?: 0
        val response = api.apiSettings().getRegularUsersByFilter(currentPage, params.loadSize, letter)
        var userList : MutableList<regularUser> = emptyList<regularUser>() as MutableList<regularUser>
        response.subscribe(object : SingleObserver<List<regularUser>>{
            override fun onSubscribe(d: Disposable) {
                TODO("Not yet implemented")
            }

            override fun onSuccess(t: List<regularUser>) {
                userList = t.toMutableList()
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return try {
            LoadResult.Page(
                    data = userList,
                    prevKey = if (currentPage == 0) null else -1,
                    nextKey = currentPage.plus(1)
            )
        }
        catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, regularUser>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}