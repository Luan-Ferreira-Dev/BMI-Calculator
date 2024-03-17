package com.example.bmicalculator.data

import kotlin.math.roundToInt

data class IndexUiState(
    val age: Int = 0,
    val isMale: Boolean = true,
    val height: Double = 0.0,
    val weight: Double = 0.0) {

    val bodyMassIndex: Double
        get() = (weight / (height * height) * 100).roundToInt() / 100.0

    val suggestedWeight: String
        get() = "${18.5 * (height * height)} ~ ${25 * (height * height)}"
}