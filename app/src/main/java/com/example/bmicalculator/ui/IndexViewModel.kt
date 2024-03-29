package com.example.bmicalculator.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bmicalculator.data.IndexUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class IndexViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(IndexUiState())
    val uiState = _uiState.asStateFlow()
    var age by mutableStateOf("")
    var isMale by mutableStateOf(true)
    var height by mutableStateOf("")
    var weight by mutableStateOf("")
    fun updateAge(input: String) {
        if (input.length <= 3) {
            if (!input.contains(".") && !input.contains(",")) {
                age = input
            }
        }

    }
    fun updateHeight(input: String) {
        if (input.length <= 6) {
            if (!input.contains(".") && !input.contains(",")) {
                height = input
            }
        }

    }
    fun updateWeight(input: String) {
        if (input.length <= 6) {
            if (!input.contains(",")) {
                if (input.count { it == '.' } <= 1) {
                    weight = input
                }
            }
        }

    }
    fun updateGender(isMale: Boolean) {
        this.isMale = isMale
    }
    fun saveStates(callback: () -> Unit) {
        if(age != "" && height != "" && weight != "") {
            _uiState.update { current -> current
                .copy(age = age.toInt(),
                    isMale = isMale,
                    height = height.toInt() / 100.0,
                    weight = weight.toDouble()) }
            callback()
        }
    }
}