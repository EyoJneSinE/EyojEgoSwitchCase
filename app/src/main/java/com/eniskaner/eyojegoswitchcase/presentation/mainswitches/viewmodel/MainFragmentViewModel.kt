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
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _switchListUiState = MutableStateFlow(
        MainFragmentUIState(
            bottomNavItems = savedStateHandle.get<List<String>>("bottomNavItems") ?: emptyList(),
            switchListModel = listOf(SwitchPreferencesUIModel(
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
        )
    )
    val switchListUiState =  _switchListUiState.asStateFlow()



    init {
        val savedBottomNavItems: List<String>? = savedStateHandle["bottomNavItems"]
        val savedSwitchListModel: List<SwitchPreferencesUIModel>? = savedStateHandle["switchListModel"]

        if (savedSwitchListModel != null && savedBottomNavItems != null) {
            updateSwitchListState(
                MainFragmentUIState(
                    switchListModel = savedSwitchListModel,
                    bottomNavItems = savedBottomNavItems
                )
            )
        }
    }

    fun getState():StateFlow<MainFragmentUIState> = switchListUiState

    fun updateSwitchListState(newState: MainFragmentUIState) {
        _switchListUiState.value = newState
        Log.d("ViewModel", "Updating state: $newState")

        // State'i SavedStateHandle ile kaydet
        savedStateHandle["bottomNavItems"] = newState.bottomNavItems
        savedStateHandle["switchListModel"] = newState.switchListModel
    }

    fun updateList(item: SwitchPreferencesUIModel) {
        when(item.switchType) {
            SwitchType.EGO -> {
                handleEgoSwitch(item)
            }
            else -> {
                handleOtherSwitches(item)
            }
        }

    }

    private fun handleOtherSwitches(item: SwitchPreferencesUIModel) {
        val updatedList = _switchListUiState.value.switchListModel.map {
            if (it.switchType == item.switchType && it.isChecked == item.isChecked) {
                it.copy(isChecked = item.isChecked)
            } else it
        }


        updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))


        if (item.isChecked) {
            addSwitchToBottomNav(item)
        } else {
            removeSwitchFromBottomNav(item)
        }
    }

    private fun handleEgoSwitch(item: SwitchPreferencesUIModel) {
        if (item.isChecked) {
            enableEgoSwitch(item)
            _switchListUiState.update {
                it.copy(bottomNavItems = emptyList())
            }
        } else {
            disableEgoSwitch(item)
            _switchListUiState.update {
                it.copy(bottomNavItems = generateBottomNavItems(item))
            }
        }
    }

    private fun generateBottomNavItems(item: SwitchPreferencesUIModel): List<String> {
        return _switchListUiState.value.switchListModel
            .filter { it.isChecked && it.switchType != SwitchType.EGO }
            .map { it.switchName }
            .ifEmpty { listOf("main") }
    }

    private fun addSwitchToBottomNav(item: SwitchPreferencesUIModel) {
        val currentItems = _switchListUiState.value.bottomNavItems
        if (currentItems.size < 5) {
            val updatedList = _switchListUiState.value.copy(bottomNavItems = currentItems + item.switchName)
            updateSwitchListState(_switchListUiState.value.copy(bottomNavItems = updatedList.bottomNavItems))
        }
    }

    private fun removeSwitchFromBottomNav(item: SwitchPreferencesUIModel) {
        val currentItems = _switchListUiState.value.bottomNavItems
        val updatedList = _switchListUiState.value.copy(bottomNavItems = currentItems - item.switchName)
        updateSwitchListState(_switchListUiState.value.copy(bottomNavItems = updatedList.bottomNavItems))
    }



    private fun enableEgoSwitch(item: SwitchPreferencesUIModel) {
        val updatedList = _switchListUiState.value.switchListModel.map {
            it.copy(
                isChecked = it.switchType == SwitchType.EGO,
                isEnabled = it.switchType == SwitchType.EGO
            )
        }
        updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
    }

    private fun disableEgoSwitch(item: SwitchPreferencesUIModel) {
        val updatedList = _switchListUiState.value.switchListModel.map {
            if (it.switchType == SwitchType.EGO) {
                it.copy(isChecked = false, isEnabled = true)
            } else {
                it.copy(isEnabled = true)
            }
        }
        updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
    }
}

/*fun enableWhichSwitchIsChecked(item: SwitchPreferencesUIModel) {
        val updatedList = _switchListUiState.value.switchListModel.map {
            it.copy(isChecked = item.isChecked)
        }
        updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
    }*/

/*private fun disableOtherSwitch(item: SwitchPreferencesUIModel) {
        val updateList = _switchListUiState.value.switchListModel.map {
            it.copy(isEnabled = it.switchType == SwitchType.EGO, isChecked = it.switchType == SwitchType.EGO )
        }
        updateSwitchListState(_switchListUiState.value.copy(switchListModel = updateList))
    }

    private fun enableOtherSwitches() {
        val updatedList = _switchListUiState.value.switchListModel.map {
            if (it.switchType != SwitchType.EGO) {
                it.copy(isEnabled = true)
            } else it
        }
        updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
    }

    private fun updateBottomNavItems(item: SwitchPreferencesUIModel) {
        if (item.isChecked) {
            val currentItems = _switchListUiState.value.bottomNavItems
            val updatedList = _switchListUiState.value.copy(bottomNavItems = currentItems)
            updateSwitchListState(_switchListUiState.value.copy(bottomNavItems = updatedList.bottomNavItems))
        } else {
            updateSwitchListState(_switchListUiState.value.copy(bottomNavItems = listOf("main")))
        }
    }*/

/*private fun addSwitchToBottomNav(item: SwitchPreferencesUIModel) {
        val currentItems = _switchListUiState.value.bottomNavItems
        if (currentItems.size < 5) {
            val updatedList = _switchListUiState.value.copy(bottomNavItems = currentItems + item.switchName)
            updateSwitchListState(_switchListUiState.value.copy(bottomNavItems = updatedList.bottomNavItems))

        } else {
            updateSwitchListState(_switchListUiState.value.copy(bottomNavItems = currentItems))
        }
    }

    private fun removeSwitchFromBottomNav(item: SwitchPreferencesUIModel) {
        val currentItems = _switchListUiState.value.bottomNavItems
        val updatedList = _switchListUiState.value.copy(bottomNavItems = currentItems - item.switchName)
        updateSwitchListState(_switchListUiState.value.copy(bottomNavItems = updatedList.bottomNavItems))
    }*/

/*when(item.switchType) {
        SwitchType.EGO -> {
            if (item.isChecked) {
                enableEgoSwitch(item)
                //disableOtherSwitch(item)
                //updateSwitchListState(_switchListUiState.value.copy(bottomNavItems = emptyList()))
                _switchListUiState.update {
                    it.copy(bottomNavItems = emptyList())
                }
            } else {
                disableEgoSwitch(item)
                //enableWhichSwitchIsChecked(item)
                updateSwitchListState(_switchListUiState.value.copy(bottomNavItems = listOf("main")))
                //enableOtherSwitches()
                //updateBottomNavItems(item)
            }
        }
        *//*SwitchType.LOTR -> {
                if (item.isChecked) {
                    val updatedList = _switchListUiState.value.switchListModel.map {
                        it.copy(
                            isChecked = it.switchType == SwitchType.LOTR
                        )
                    }
                    updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
                    addSwitchToBottomNav(item)
                } else {
                    val updatedList = _switchListUiState.value.switchListModel.map {
                        it.copy(
                            isChecked = it.switchType == SwitchType.LOTR
                        )
                    }
                    updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
                    removeSwitchFromBottomNav(item)
                }
            }
            SwitchType.YES_MAN -> {
                if (item.isChecked) {
                    val updatedList = _switchListUiState.value.switchListModel.map {
                        it.copy(
                            isChecked = it.switchType == SwitchType.YES_MAN
                        )
                    }
                    updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
                    addSwitchToBottomNav(item)
                } else {
                    val updatedList = _switchListUiState.value.switchListModel.map {
                        it.copy(
                            isChecked = it.switchType == SwitchType.YES_MAN
                        )
                    }
                    updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
                    removeSwitchFromBottomNav(item)
                }
            }
            SwitchType.DEADPOOL -> {
                if (item.isChecked) {
                    val updatedList = _switchListUiState.value.switchListModel.map {
                        it.copy(
                            isChecked = it.switchType == SwitchType.DEADPOOL
                        )
                    }
                    updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
                    addSwitchToBottomNav(item)
                } else {
                    val updatedList = _switchListUiState.value.switchListModel.map {
                        it.copy(
                            isChecked = it.switchType == SwitchType.DEADPOOL
                        )
                    }
                    updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
                    removeSwitchFromBottomNav(item)
                }
            }
            SwitchType.WOLF_STREET -> {
                if (item.isChecked) {
                    val updatedList = _switchListUiState.value.switchListModel.map {
                        it.copy(
                            isChecked = it.switchType == SwitchType.WOLF_STREET
                        )
                    }
                    updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
                    addSwitchToBottomNav(item)
                } else {
                    val updatedList = _switchListUiState.value.switchListModel.map {
                        it.copy(
                            isChecked = it.switchType == SwitchType.WOLF_STREET
                        )
                    }
                    updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
                    removeSwitchFromBottomNav(item)
                }
            }
            SwitchType.HARRY_POTTER -> {
                if (item.isChecked) {
                    val updatedList = _switchListUiState.value.switchListModel.map {
                        it.copy(
                            isChecked = it.switchType == SwitchType.HARRY_POTTER
                        )
                    }
                    updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
                    addSwitchToBottomNav(item)
                } else {
                    val updatedList = _switchListUiState.value.switchListModel.map {
                        it.copy(
                            isChecked = it.switchType == SwitchType.HARRY_POTTER
                        )
                    }
                    updateSwitchListState(_switchListUiState.value.copy(switchListModel = updatedList))
                    removeSwitchFromBottomNav(item)
                }
            }*//*
            else -> {
                handleOtherSwitches(item)
                *//*if (_switchListUiState.value.switchListModel.any { it.switchType == SwitchType.EGO && it.isChecked }) {
                    return
                }
                if (item.isChecked) {

                    addSwitchToBottomNav(item)
                } else {
                    removeSwitchFromBottomNav(item)
                }*//*
            }
        }*/