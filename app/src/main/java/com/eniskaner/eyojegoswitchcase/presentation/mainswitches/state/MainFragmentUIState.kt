package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.state

import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel

data class MainFragmentUIState(
    val switchListModel: List<SwitchPreferencesUIModel> = emptyList(),
    val bottomNavItems: List<String> = emptyList()
)
