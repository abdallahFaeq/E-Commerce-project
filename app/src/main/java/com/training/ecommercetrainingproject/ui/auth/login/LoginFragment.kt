package com.training.ecommercetrainingproject.ui.auth.login

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.training.ecommercetrainingproject.R
import com.training.ecommercetrainingproject.data.models.Resource
import com.training.ecommercetrainingproject.data.repos.auth.FirebaseAuthRepoImpl
import com.training.ecommercetrainingproject.data.repos.user.UserPreferencesRepositoryImpl
import com.training.ecommercetrainingproject.data.sources.datastore.AppPreferencesDataSource
import com.training.ecommercetrainingproject.databinding.FragmentLoginBinding
import com.training.ecommercetrainingproject.ui.common.UserViewModel
import com.training.ecommercetrainingproject.ui.common.views.ProgressDialogManager
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val progressDialog: ProgressDialogManager by lazy {
        ProgressDialogManager(requireContext())
    }


    val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            FirebaseAuthRepoImpl(),
            UserPreferencesRepositoryImpl(AppPreferencesDataSource(requireContext()))
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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = loginViewModel

        initListeners()
        initViewModels()

    }

    private fun initListeners() {
        binding.registerLabel.setOnClickListener {
            navigateToRegisterFragment()
        }
    }

    private fun initViewModels() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginState.collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            progressDialog?.let { dialog ->
                                dialog.dismissDialog()
                            }
                            Log.e(TAG, "resource is success")
                            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT)
                                .show()
                        }

                        is Resource.Error -> {
                            progressDialog?.let { dialog ->
                                dialog.dismissDialog()
                            }
                            Log.e(TAG, "${resource.exception?.message}")
                            Toast.makeText(
                                requireContext(),
                                "${resource.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is Resource.Loading -> {
                            progressDialog?.let { dialog ->
                                dialog.showDialog()
                            }
                        }
                    }
                }
            }
        }
    }



    private fun navigateToRegisterFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}
