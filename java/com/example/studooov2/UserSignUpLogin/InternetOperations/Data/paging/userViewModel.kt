package com.example.studooov2.UserSignUpLogin.InternetOperations.Data.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class userViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    val list = userRepository.getQuotes().cachedIn(viewModelScope)

}