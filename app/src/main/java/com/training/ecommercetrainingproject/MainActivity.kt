package com.training.ecommercetrainingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
            .apply {
                setKeepOnScreenCondition {
                    viewModel.isLoading.value
                }
                setOnExitAnimationListener{splashView->
                    splashView.view.animate()
                        .alpha(0f)
                        .scaleX(1.1f)
                        .scaleY(1.1f)
                        .rotation(360.0f)
                        .setDuration(400)
                        .withEndAction{
                            splashView.remove()
                        }
                        .start()
                }
            }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}