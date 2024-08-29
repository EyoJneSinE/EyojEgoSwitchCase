package com.eniskaner.eyojegoswitchcase.presentation.mainswitches.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.eyojegoswitchcase.databinding.FragmentMainBinding
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.adapter.MainUiAdapter
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.util.launchAndRepeatWithViewLifecycle
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding
    private val mainFragmentViewModel: MainFragmentViewModel by viewModels()
    private val mainUiAdapter: MainUiAdapter by lazy { MainUiAdapter() }

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
        return binding?.root
    }

    private fun getData() {
        launchAndRepeatWithViewLifecycle {
            launch {
                mainFragmentViewModel.switchListUiState.collect {mainFragmentUiState ->
                    mainFragmentUiState?.let {
                        mainUiAdapter.submitList(mainFragmentUiState.switchListModel)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}