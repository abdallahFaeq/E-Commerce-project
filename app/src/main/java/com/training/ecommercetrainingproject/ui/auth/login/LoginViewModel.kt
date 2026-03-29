package com.training.ecommercetrainingproject.ui.auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private var _name = MutableStateFlow("Abdullah")
    val name = _name.asStateFlow()
}