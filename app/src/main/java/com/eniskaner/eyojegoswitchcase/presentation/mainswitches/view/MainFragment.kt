package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.view.removeItemAt
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.eyojegoswitchcase.R
import com.eniskaner.eyojegoswitchcase.databinding.FragmentMainBinding
import com.eniskaner.eyojegoswitchcase.presentation.MainActivity
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.adapter.MainUiAdapter
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.adapter.SwitchClickListener
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchType
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.util.launchAndRepeatWithViewLifecycle
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.viewmodel.MainFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(), SwitchClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding
    private val mainFragmentViewModel: MainFragmentViewModel by viewModels()
    private val mainUiAdapter: MainUiAdapter by lazy { MainUiAdapter(this@MainFragment) }
    private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding?.recyclerViewSwitchList?.layoutManager = LinearLayoutManager(requireContext())

        bottomNavigationView =
            (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        getData()
    }

    private fun setAdapter() {
        binding?.recyclerViewSwitchList?.adapter = mainUiAdapter
    }

    private fun getData() {
        launchAndRepeatWithViewLifecycle {
            launch {
                mainFragmentViewModel.switchListUiState.collect { mainFragmentUiState ->

                    mainFragmentViewModel.updateSwitchListState(mainFragmentUiState)
                    mainFragmentViewModel.getState()
                    mainUiAdapter.submitList(mainFragmentUiState.switchListModel)

                }
            }
        }
    }

    override fun switchClickListener(
        item: SwitchPreferencesUIModel,
        isChecked: Boolean,
        index: Int
    ) {
        mainFragmentViewModel.updateList(item, isChecked, index)
        updateBottomNavMenu(
            isChecked = isChecked,
            itemId = getItemID(item.switchName)
        )

        if (item.switchType == SwitchType.EGO && !isChecked) {
            bottomNavigationView?.isVisible = true
        } else if (item.switchType != SwitchType.EGO && isChecked) {
            bottomNavigationView?.isVisible = true
        } else if (item.switchType != SwitchType.EGO && !isChecked) {
            bottomNavigationView?.isVisible = true
        } else {
            bottomNavigationView?.isVisible = false
        }
    }

    private fun getItemID(item: String): Int {
        return when (item) {
            "LOTR" -> R.id.switch1Fragment
            "HarryPotter" -> R.id.switch2Fragment
            "DeadPool" -> R.id.switch3Fragment
            "WolfStreet" -> R.id.switch4Fragment
            "YesMan" -> R.id.switch5Fragment
            "main" -> R.id.mainFragment
            else -> View.generateViewId()
        }
    }

    private fun getCheckedBottomNavItems() {
        removeMenuItems()
        val checkedItems =
            mainFragmentViewModel.switchListUiState.value.switchListModel.filter { it.isChecked }
                .map { it.switchName }
        val menu = bottomNavigationView?.menu
        menu?.let {
            checkedItems.forEach { item ->
                val itemId = getItemID(item)
                menu.add(Menu.NONE, itemId, Menu.NONE, getMenuTitle(itemId))
                    ?.setIcon(getMenuIcon(itemId))
            }
        }
    }

    private fun updateBottomNavMenu(isChecked: Boolean, itemId: Int) {
        val menu = bottomNavigationView?.menu


        menu?.let {
            if (isChecked) {
                if (itemId == R.id.mainFragment) {
                    val menuSize = bottomNavigationView?.menu?.size()
                    if (menuSize != null) {
                        removeMenuItems()
                    }
                } else {
                    if (menu.findItem(itemId) == null && menu.size() <= 4) {
                        menu.add(Menu.NONE, itemId, Menu.NONE, getMenuTitle(itemId))
                            ?.setIcon(getMenuIcon(itemId))
                    }
                }
            } else {
                if (itemId != R.id.mainFragment)
                    menu.removeItem(itemId)
                getCheckedBottomNavItems()
            }

        }

    }

    private fun removeMenuItems() {
        val ids = listOf(
            R.id.switch1Fragment,
            R.id.switch2Fragment,
            R.id.switch3Fragment,
            R.id.switch4Fragment,
            R.id.switch5Fragment
        )
        for (id in ids) {
            removeMenuItem(id)
        }
    }

    private fun removeMenuItem(fragmentId: Int) {
        val menu = bottomNavigationView?.menu
        menu?.let {
            with(menu) {
                findItem(fragmentId)?.let {
                    removeItem(it.itemId)
                }
            }
        }
    }

    private fun getMenuTitle(itemId: Int): String {
        return when (itemId) {
            R.id.mainFragment -> "main"
            R.id.switch1Fragment -> "LOTR"
            R.id.switch2Fragment -> "HarryPotter"
            R.id.switch3Fragment -> "DeadPool"
            R.id.switch4Fragment -> "WolfStreet"
            R.id.switch5Fragment -> "YesMan"
            else -> ""
        }
    }

    private fun getMenuIcon(itemId: Int): Int {
        return when (itemId) {
            R.id.mainFragment -> R.drawable.ic_sentiment_satisfied_36dp
            R.id.switch1Fragment -> R.drawable.ic_optimism
            R.id.switch2Fragment -> R.drawable.ic_happiness
            R.id.switch3Fragment -> R.drawable.ic_kindness
            R.id.switch4Fragment -> R.drawable.ic_respect
            R.id.switch5Fragment -> R.drawable.ic_giving
            else -> 0
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
