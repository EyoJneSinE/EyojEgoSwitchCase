package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.eniskaner.eyojegoswitchcase.data.datamodelprovider.SwitchPreferencesUIModelProvider
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchType
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.state.MainFragmentUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val switchPreferencesUIModelProvider: SwitchPreferencesUIModelProvider
) : ViewModel() {

    private val _switchListUiState = MutableStateFlow(
        MainFragmentUIState(
            bottomNavItems = savedStateHandle.get<List<String>>("bottomNavItems") ?: emptyList(),
            switchListModel = savedStateHandle.get<List<SwitchPreferencesUIModel>>("switchListModel")
                ?: emptyList()
        )
    )

    val switchListUiState = _switchListUiState.asStateFlow()


    init {
        getSwitchList()
        val savedBottomNavItems: List<String>? = savedStateHandle["bottomNavItems"]
        val savedSwitchListModel: List<SwitchPreferencesUIModel>? =
            savedStateHandle["switchListModel"]

        if (savedSwitchListModel != null && savedBottomNavItems != null) {
            updateSwitchListState(
                MainFragmentUIState(
                    switchListModel = savedSwitchListModel,
                    bottomNavItems = savedBottomNavItems
                )
            )
        }
    }

    fun getState(): StateFlow<MainFragmentUIState> = switchListUiState

    fun updateSwitchListState(newState: MainFragmentUIState) {

        _switchListUiState.value = newState

        savedStateHandle["bottomNavItems"] = newState.bottomNavItems
        savedStateHandle["switchListModel"] = newState.switchListModel
    }

    fun updateList(item: SwitchPreferencesUIModel, isChecked: Boolean, index: Int) {
        when (item.switchType) {
            SwitchType.EGO -> {
                setEgoSwitchChecked(item, isChecked)
                if (isChecked) {
                    updateSwitchListState(_switchListUiState.value.copy(bottomNavItems = emptyList()))
                } else {
                    updateSwitchListState(
                        _switchListUiState.value.copy(
                            bottomNavItems = generateBottomNavItems(
                                item
                            )
                        )
                    )
                }
            }

            else -> {
                setOtherSwitchCheck(item, isChecked, index)
                if (isChecked)
                    addSwitchToBottomNav(item)
                else removeSwitchFromBottomNav(item)

            }
        }
    }

    private fun setOtherSwitchCheck(
        item: SwitchPreferencesUIModel,
        isChecked: Boolean,
        index: Int
    ) {
        updateSwitchListState(_switchListUiState.value.copy(switchListModel = _switchListUiState.value.switchListModel.mapIndexed { itemIndex, switchModel ->
            switchModel.copy(
                isChecked = if (index == itemIndex) {
                    isChecked
                } else {
                    switchModel.isChecked
                }
            )
        }))
    }

    private fun addSwitchToBottomNav(item: SwitchPreferencesUIModel) {
        val currentItems = _switchListUiState.value.bottomNavItems
        val updatedList =
            _switchListUiState.value.copy(bottomNavItems = currentItems + item.switchName)
        updateSwitchListState(_switchListUiState.value.copy(bottomNavItems = updatedList.bottomNavItems))
    }

    private fun removeSwitchFromBottomNav(item: SwitchPreferencesUIModel) {
        val currentItems = _switchListUiState.value.bottomNavItems
        val updatedList =
            _switchListUiState.value.copy(bottomNavItems = currentItems - item.switchName)
        updateSwitchListState(_switchListUiState.value.copy(bottomNavItems = updatedList.bottomNavItems))
    }


    private fun generateBottomNavItems(item: SwitchPreferencesUIModel): List<String> {
        return _switchListUiState.value.switchListModel
            .filter { it.isChecked && it.switchType != SwitchType.EGO }
            .map { it.switchName }
            .ifEmpty { listOf("main") }
    }

    private fun setEgoSwitchChecked(item: SwitchPreferencesUIModel, isChecked: Boolean) {
        updateSwitchListState(_switchListUiState.value.copy(switchListModel = _switchListUiState.value.switchListModel.map { switchModel ->
            switchModel.copy(
                isChecked = if (switchModel.switchType == SwitchType.EGO) {
                    isChecked
                } else {
                    if (isChecked) false else switchModel.isChecked
                },
                isEnabled = if (switchModel.switchType == SwitchType.EGO) {
                    switchModel.isEnabled
                } else {
                    !isChecked
                },
            )
        }))
    }

    private fun getSwitchList() {
        updateSwitchListState(
            _switchListUiState.value.copy(
                switchListModel = switchPreferencesUIModelProvider.addSwitches()
            )
        )
    }
}
