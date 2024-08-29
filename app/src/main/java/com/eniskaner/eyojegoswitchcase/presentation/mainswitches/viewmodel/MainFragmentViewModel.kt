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

    private fun getSwitchList() {
        val switchList = switchPreferencesUIModelProvider.addSwitches()
        _switchListUiState.update {
            it.copy(switchListModel = switchList)
        }
    }
}