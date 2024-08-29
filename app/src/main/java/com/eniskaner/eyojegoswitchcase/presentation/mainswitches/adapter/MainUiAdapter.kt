package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.viewholder.MainUiViewHolder

class MainUiAdapter(): ListAdapter<SwitchPreferencesUIModel, MainUiViewHolder>(MainSwitchesDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainUiViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MainUiViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}