package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.adapter

import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchType

interface SwitchClickListener {
    fun switchClickListener(item: SwitchPreferencesUIModel, isChecked: Boolean, index: Int)

}
