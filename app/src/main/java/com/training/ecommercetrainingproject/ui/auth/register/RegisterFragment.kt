package com.training.ecommercetrainingproject.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.training.ecommercetrainingproject.R
import com.training.ecommercetrainingproject.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private var _binding : FragmentRegisterBinding?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInLabel.setOnClickListener {
            navigateToLoginFragment()
        }
    }

    private fun navigateToLoginFragment(){
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    }