package com.eniskaner.eyojegoswitchcase.data.datamodelprovider

import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchType
import javax.inject.Inject

class SwitchPreferencesUIModelProvider @Inject constructor() {

    fun addSwitches(): List<SwitchPreferencesUIModel> {
        val switchList = listOf(SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "LOTR",
            switchType = SwitchType.OTHERS
        ),  SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "HarryPotter",
            switchType = SwitchType.OTHERS
        ), SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "DeadPool",
            switchType = SwitchType.OTHERS
        ), SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "WolfStreet",
            switchType = SwitchType.OTHERS
        ), SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "YesMan",
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