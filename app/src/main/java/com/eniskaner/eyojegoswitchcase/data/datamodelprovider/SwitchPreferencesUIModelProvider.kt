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
            switchType = SwitchType.LOTR
        ),  SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "HarryPotter",
            switchType = SwitchType.HARRY_POTTER
        ), SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "DeadPool",
            switchType = SwitchType.DEADPOOL
        ), SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "WolfStreet",
            switchType = SwitchType.WOLF_STREET
        ), SwitchPreferencesUIModel(
            isEnabled = false,
            isChecked = false,
            switchName = "YesMan",
            switchType = SwitchType.YES_MAN
        ), SwitchPreferencesUIModel(
            isEnabled = true,
            isChecked = true,
            switchName = "Ego",
            switchType = SwitchType.EGO
        ))
        return  switchList
    }
}