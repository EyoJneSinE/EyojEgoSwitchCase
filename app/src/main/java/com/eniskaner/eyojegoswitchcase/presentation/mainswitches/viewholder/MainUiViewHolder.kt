package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.eyojegoswitchcase.databinding.ItemSwitchBinding
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.adapter.SwitchClickListener
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel

class MainUiViewHolder(
    private val binding: ItemSwitchBinding,
    private val switchClickListener: SwitchClickListener
): RecyclerView.ViewHolder(binding.root) {

    fun bindSwitchListToRecyclerView(item: SwitchPreferencesUIModel) {
        bindSwitchButton(item)
        setListener(item)
    }

    private fun bindSwitchButton(item: SwitchPreferencesUIModel) = with(binding.switchButton) {
        isEnabled = item.isEnabled
        isChecked = item.isChecked
        text = item.switchName
    }

    private fun setListener(item: SwitchPreferencesUIModel) = with(binding.switchButton) {
        setOnClickListener {
            switchClickListener.switchClickListener(item, !item.isChecked, adapterPosition)
        }
        /*setOnCheckedChangeListener { _, isChecked ->
            switchClickListener.switchClickListener(item, isChecked, adapterPosition)
        }*/
    }
}
