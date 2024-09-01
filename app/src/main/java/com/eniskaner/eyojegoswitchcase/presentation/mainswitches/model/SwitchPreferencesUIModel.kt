package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SwitchPreferencesUIModel(
    val isChecked : Boolean,
    val isEnabled : Boolean,
    val switchName: String,
    val switchType: SwitchType
): Parcelable

enum class SwitchType {
    EGO,
    LOTR,
    HARRY_POTTER,
    DEADPOOL,
    WOLF_STREET,
    YES_MAN
}