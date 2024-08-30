package com.eniskaner.eyojegoswitchcase.presentation.switch5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eniskaner.eyojegoswitchcase.databinding.FragmentSwitch5Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Switch5Fragment : Fragment() {

    private var _binding: FragmentSwitch5Binding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSwitch5Binding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}