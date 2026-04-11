package com.training.ecommercetrainingproject.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.training.ecommercetrainingproject.R
import com.training.ecommercetrainingproject.data.repos.user.UserPreferencesRepositoryImpl
import com.training.ecommercetrainingproject.data.sources.datastore.AppPreferencesDataSource
import com.training.ecommercetrainingproject.databinding.FragmentLoginBinding
import com.training.ecommercetrainingproject.ui.common.UserViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding ?= null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by lazy {
        UserViewModel(
            userPreferencesRepository = UserPreferencesRepositoryImpl(
                AppPreferencesDataSource(requireContext())
            )
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.saveLoginState(true)

        binding.registerLabel.setOnClickListener {
            navigateToRegisterFragment()
        }
    }

    private fun navigateToRegisterFragment(){
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}