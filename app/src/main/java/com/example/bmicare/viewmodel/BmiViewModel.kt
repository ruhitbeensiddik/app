package com.example.bmicare.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bmicare.model.BmiCategory
import kotlin.math.round

// Gender enum
enum class Gender { MALE, FEMALE }

class BmiViewModel : ViewModel() {

    private val _selectedGender = mutableStateOf<Gender?>(null)
    val selectedGender: State<Gender?> = _selectedGender

    private val _weight = mutableStateOf("")
    val weight: State<String> = _weight

    private val _heightFeet = mutableStateOf("")
    val heightFeet: State<String> = _heightFeet

    private val _heightInches = mutableStateOf("")
    val heightInches: State<String> = _heightInches

    private val _bmiResult = mutableStateOf(0f)
    val bmiResult: State<Float> = _bmiResult

    private val _bmiCategory = mutableStateOf(BmiCategory.NORMAL)
    val bmiCategory: State<BmiCategory> = _bmiCategory

    fun setGender(gender: Gender) {
        _selectedGender.value = gender
    }

    fun setWeight(w: String) {
        if (w.isEmpty() || w.matches(Regex("^\\d*\\.?\\d*\$"))) {
            _weight.value = w
        }
    }

    fun setHeightFeet(ft: String) {
        if (ft.isEmpty() || ft.matches(Regex("^\\d*\$"))) {
            _heightFeet.value = ft
        }
    }

    fun setHeightInches(inc: String) {
        if (inc.isEmpty() || inc.matches(Regex("^\\d*\$"))) {
            _heightInches.value = inc
        }
    }

    fun calculateBmi(): Boolean {
        val w = _weight.value.toFloatOrNull()
        val ft = _heightFeet.value.toIntOrNull()
        val inc = _heightInches.value.toIntOrNull()

        if (w == null || w <= 0) return false
        if (ft == null || ft < 0) return false
        if (inc == null || inc < 0 || inc > 11) return false
        
        val totalInches = (ft * 12) + inc
        if (totalInches <= 0) return false

        // Convert inches to meters
        val heightMeters = totalInches * 0.0254f
        
        val bmi = w / (heightMeters * heightMeters)
        _bmiResult.value = round(bmi * 100) / 100
        _bmiCategory.value = BmiCategory.fromBmi(_bmiResult.value)
        return true
    }

    fun reset() {
        _weight.value = ""
        _heightFeet.value = ""
        _heightInches.value = ""
        _selectedGender.value = null
        _bmiResult.value = 0f
    }
}
