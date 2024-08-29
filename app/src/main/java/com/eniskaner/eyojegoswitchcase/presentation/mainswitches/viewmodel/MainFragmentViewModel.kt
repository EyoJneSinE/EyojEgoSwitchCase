package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.viewmodel

import androidx.lifecycle.ViewModel
import com.eniskaner.eyojegoswitchcase.data.datamodelprovider.SwitchPreferencesUIModelProvider
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchType
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.state.MainFragmentUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val switchPreferencesUIModelProvider: SwitchPreferencesUIModelProvider
): ViewModel() {

    private val _switchListUiState = MutableStateFlow(MainFragmentUIState())
    val switchListUiState = _switchListUiState.asStateFlow()



    init {
        getSwitchList()
    }

    fun updateList(item: SwitchPreferencesUIModel) {
        when(item.switchType) {
            SwitchType.EGO -> {
                if (item.isChecked) {
                    enableEgoSwitch(item)
                    disableOtherSwitch(item)
                    _switchListUiState.update {
                        it.copy(bottomNavItems = emptyList())
                    }
                } else {
                    enableOtherSwitches()
                    updateBottomNavItems()
                }
            }
            SwitchType.OTHERS -> {
                if (item.isChecked) {
                    addSwitchToBottomNav(item)
                } else {
                    removeSwitchFromBottomNav(item)
                }
            }
        }
    }

    private fun enableEgoSwitch(item: SwitchPreferencesUIModel) {
        val updatedList = _switchListUiState.value.switchListModel.map {
            it.copy(
                isChecked = it.switchType == SwitchType.EGO,
                isEnabled = it.switchType == SwitchType.EGO
            )
        }

        _switchListUiState.update {
            it.copy(switchListModel = updatedList)
        }
    }

    private fun disableOtherSwitch(item: SwitchPreferencesUIModel) {
        val updateList = _switchListUiState.value.switchListModel.map {
            if (it.switchType == SwitchType.OTHERS) {
                it.copy(isEnabled = item.isEnabled)
            } else it
            it.copy(isEnabled = it.switchType == SwitchType.EGO)
        }
        _switchListUiState.update {
            it.copy(switchListModel = updateList)
        }
    }

    private fun enableOtherSwitches() {
        val updatedList = _switchListUiState.value.switchListModel.map {
            if (it.switchType == SwitchType.OTHERS) {
                it.copy(isEnabled = true)
            } else it
        }
        _switchListUiState.update {
            it.copy(switchListModel = updatedList)
        }
    }

    private fun updateBottomNavItems() {
        val currentItems = _switchListUiState.value.bottomNavItems
        val updatedList = _switchListUiState.value.copy(bottomNavItems = listOf("main") + currentItems)
        _switchListUiState.update {
            it.copy(bottomNavItems = updatedList.bottomNavItems)
        }
    }

    private fun addSwitchToBottomNav(item: SwitchPreferencesUIModel) {
        val currentItems = _switchListUiState.value.bottomNavItems
        if (currentItems.size < 5) {
            val updatedList = _switchListUiState.value.copy(bottomNavItems = currentItems + item.switchName)
            _switchListUiState.update {
                it.copy(bottomNavItems = updatedList.bottomNavItems)
            }
        } else {
            _switchListUiState.update {
                it.copy(bottomNavItems = currentItems)
            }
        }
    }

    private fun removeSwitchFromBottomNav(item: SwitchPreferencesUIModel) {
        val currentItems = _switchListUiState.value.bottomNavItems
        val updatedList = _switchListUiState.value.copy(bottomNavItems = currentItems - item.switchName)
        _switchListUiState.update {
            it.copy(bottomNavItems = updatedList.bottomNavItems)
        }
    }

    private fun getSwitchList() {
        val switchList = switchPreferencesUIModelProvider.addSwitches()
        _switchListUiState.update {
            it.copy(switchListModel = switchList)
        }
    }
}