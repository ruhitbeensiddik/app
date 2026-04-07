package com.example.bmicare.model

import androidx.compose.ui.graphics.Color
import com.example.bmicare.ui.theme.NormalColor
import com.example.bmicare.ui.theme.ObeseColor
import com.example.bmicare.ui.theme.OverweightColor
import com.example.bmicare.ui.theme.UnderweightColor

enum class BmiCategory(
    val title: String,
    val color: Color,
    val recommendation: String,
    val range: String
) {
    UNDERWEIGHT(
        "Underweight",
        UnderweightColor,
        "Suggest nutritious balanced diet and medical consultation if needed.",
        "BMI < 18.5"
    ),
    NORMAL(
        "Normal",
        NormalColor,
        "Maintain healthy lifestyle, balanced diet, regular exercise.",
        "18.5 - 24.9"
    ),
    OVERWEIGHT(
        "Overweight",
        OverweightColor,
        "Recommend regular exercise, calorie control, better sleep.",
        "25.0 - 29.9"
    ),
    OBESE(
        "Obese",
        ObeseColor,
        "Recommend medical advice, weight-loss planning, exercise, healthy diet.",
        "BMI 30+"
    );

    companion object {
        fun fromBmi(bmi: Float): BmiCategory {
            return when {
                bmi < 18.5f -> UNDERWEIGHT
                bmi in 18.5f..24.99f -> NORMAL
                bmi in 25.0f..29.99f -> OVERWEIGHT
                else -> OBESE
            }
        }
    }
}
