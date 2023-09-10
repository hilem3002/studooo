package com.example.studooov2.UserSignUpLogin.InternetOperations.Data.paging

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.ApiRequest
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val api : ApiRequest,
        private val letter : String = "l") : ViewModel() {

    fun getQuotes() = Pager(
            config = PagingConfig(pageSize = 8, maxSize = 20),
            pagingSourceFactory = {UserPagingSource(api, letter)}

    ).liveData

}