package com.example.bmicalculator.data

import kotlin.math.roundToInt

data class IndexUiState(
    val age: Int,
    val isMale: Boolean,
    val height: Double,
    val weight: Double) {

    val bodyMassIndex: Double
        get() = (weight / (height * height) * 100).roundToInt() / 100.0

    val suggestedWeight: String
        get() = "${18.5 * (height * height)} ~ ${25 * (height * height)}"
}