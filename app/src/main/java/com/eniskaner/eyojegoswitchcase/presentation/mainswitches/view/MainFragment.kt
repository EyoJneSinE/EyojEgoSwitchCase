package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.enableSavedStateHandles
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.eyojegoswitchcase.R
import com.eniskaner.eyojegoswitchcase.databinding.FragmentMainBinding
import com.eniskaner.eyojegoswitchcase.presentation.MainActivity
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.adapter.MainUiAdapter
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.adapter.SwitchClickListener
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.model.SwitchPreferencesUIModel
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.util.launchAndRepeatWithViewLifecycle
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.viewmodel.MainFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(), SwitchClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding
    private val mainFragmentViewModel: MainFragmentViewModel by viewModels()
    private val mainUiAdapter: MainUiAdapter by lazy { MainUiAdapter(this@MainFragment) }
    private val navController: NavController by lazy { findNavController() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMainUiRecyclerViewAdapter()
        getData()
    }

    private fun setMainUiRecyclerViewAdapter() {
        binding?.recyclerViewSwitchList?.run {
            hasFixedSize()
            adapter = mainUiAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding?.recyclerViewSwitchList?.layoutManager = LinearLayoutManager(requireContext())
        val bottomNavigationView = (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        observeBottomNavItems(bottomNavigationView)
        return binding?.root
    }

    private fun getData() {
        launchAndRepeatWithViewLifecycle {
            launch {
                mainFragmentViewModel.switchListUiState.collect {mainFragmentUiState ->
                    mainFragmentUiState?.let {
                        mainFragmentViewModel.updateSwitchListState(it)
                        mainFragmentViewModel.getState()
                        mainUiAdapter.submitList(mainFragmentUiState.switchListModel)
                    }
                }
            }
        }
    }

    private fun observeBottomNavItems(bottomNavigationView: BottomNavigationView?) {
        launchAndRepeatWithViewLifecycle {
            launch {
                mainFragmentViewModel.switchListUiState.collect {mainFragmentUiState ->
                    updateBottomNavigationView(mainFragmentUiState.bottomNavItems, bottomNavigationView)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView = (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        getData()
        observeBottomNavItems(bottomNavigationView)
    }

    private fun updateBottomNavigationView(items: List<String>, bottomNavigationView: BottomNavigationView?) {
        bottomNavigationView?.menu?.clear()
        items.forEachIndexed { index, item ->
            val itemId = when (item) {
                "main" -> R.id.mainFragment
                "LOTR" -> R.id.switch1Fragment
                "HarryPotter" -> R.id.switch2Fragment
                "DeadPool" -> R.id.switch3Fragment
                "WolfStreet" -> R.id.switch4Fragment
                "YesMan" -> R.id.switch5Fragment
                else -> View.generateViewId()
            }
            bottomNavigationView?.menu?.add(Menu.NONE, itemId, index, item)?.setIcon(R.drawable.ic_sentiment_satisfied_36dp)
        }
        bottomNavigationView?.visibility = if (items.isEmpty()) View.INVISIBLE else View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun switchClickListener(item: SwitchPreferencesUIModel) {
        mainFragmentViewModel.updateList(item)
    }
}