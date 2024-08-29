package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.eyojegoswitchcase.databinding.ItemSwitchBinding
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.adapter.SwitchClickListener
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel

class MainUiViewHolder(
    private val itemSwitchBinding: ItemSwitchBinding,
    private val switchClickListener: SwitchClickListener
): RecyclerView.ViewHolder(itemSwitchBinding.root) {

    fun bindSwitchListToRecyclerView(item: SwitchPreferencesUIModel) {
        itemSwitchBinding.switchButton.apply {
            isEnabled = item.isEnabled
            isChecked = item.isChecked
            text = item.switchName
            id = View.generateViewId()
            setOnCheckedChangeListener { _, isChecked ->
                switchClickListener.switchClickListener(item.copy(isChecked = isChecked))
            }
        }
    }
}