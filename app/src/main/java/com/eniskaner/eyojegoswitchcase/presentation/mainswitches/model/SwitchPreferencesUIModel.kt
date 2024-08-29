package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model

data class SwitchPreferencesUIModel(
    val isChecked : Boolean,
    val isEnabled : Boolean,
    val switchName: String,
    val switchType: SwitchType
)

enum class SwitchType {
    EGO,
    OTHERS
}