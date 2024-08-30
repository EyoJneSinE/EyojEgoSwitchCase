package com.eniskaner.eyojegoswitchcase.presentation.switch3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eniskaner.eyojegoswitchcase.R
import com.eniskaner.eyojegoswitchcase.databinding.FragmentSwitch3Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Switch3Fragment : Fragment() {

    private var _binding: FragmentSwitch3Binding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSwitch3Binding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}