package com.eniskaner.eyojegoswitchcase.data.datamodelprovider

import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchType
import javax.inject.Inject

class SwitchPreferencesUIModelProvider @Inject constructor() {

    fun addSwitches(): List<SwitchPreferencesUIModel> {
        val switchList = listOf(SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "a",
            switchType = SwitchType.OTHERS
        ),  SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "b",
            switchType = SwitchType.OTHERS
        ), SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "c",
            switchType = SwitchType.OTHERS
        ), SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "d",
            switchType = SwitchType.OTHERS
        ), SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "e",
            switchType = SwitchType.OTHERS
        ), SwitchPreferencesUIModel(
            isEnabled = true,
            isChecked = true,
            switchName = "Ego",
            switchType = SwitchType.EGO
        ))
        return  switchList
    }
}