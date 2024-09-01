package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.state

import android.os.Parcelable
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainFragmentUIState(
    val switchListModel: List<SwitchPreferencesUIModel> = emptyList(),
    val bottomNavItems: List<String> = emptyList()
) :Parcelable
