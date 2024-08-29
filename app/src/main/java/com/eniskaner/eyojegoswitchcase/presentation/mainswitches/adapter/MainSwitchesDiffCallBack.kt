package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel

class MainSwitchesDiffCallBack: DiffUtil.ItemCallback<SwitchPreferencesUIModel>() {
    override fun areItemsTheSame(
        oldItem: SwitchPreferencesUIModel,
        newItem: SwitchPreferencesUIModel
    ): Boolean = oldItem.isChecked == newItem.isChecked ||
            oldItem.switchType == newItem.switchType ||
            oldItem.isEnabled == newItem.isEnabled ||
            oldItem.switchName == newItem.switchName

    override fun areContentsTheSame(
        oldItem: SwitchPreferencesUIModel,
        newItem: SwitchPreferencesUIModel
    ): Boolean = oldItem == newItem
}