// MainViewModel.kt
package com.example.umraguru.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.umraguru.domain.model.DateConversion
import com.example.umraguru.domain.usecase.ConvertDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val convertDateUseCase: ConvertDateUseCase
) : ViewModel() {
    private val _dateConversion = MutableLiveData<DateConversion>()
    val dateConversion: LiveData<DateConversion> = _dateConversion

    fun convertDate(input: String) {
        val result = convertDateUseCase(input)
        _dateConversion.value = result
    }
}