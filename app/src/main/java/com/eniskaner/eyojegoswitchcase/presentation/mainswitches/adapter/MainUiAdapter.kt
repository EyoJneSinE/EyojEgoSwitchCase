package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.eyojegoswitchcase.databinding.ItemSwitchBinding
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.viewholder.MainUiViewHolder

class MainUiAdapter(
    private val switchClickListener: SwitchClickListener
): ListAdapter<SwitchPreferencesUIModel, MainUiViewHolder>(MainSwitchesDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainUiViewHolder {
        val binding = ItemSwitchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  MainUiViewHolder(binding, switchClickListener)
    }

    override fun onBindViewHolder(holder: MainUiViewHolder, position: Int) {
        holder.bindSwitchListToRecyclerView(getItem(position))
    }
}