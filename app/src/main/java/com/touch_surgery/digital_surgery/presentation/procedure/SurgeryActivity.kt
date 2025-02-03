package com.touch_surgery.digital_surgery.presentation.procedure

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider
import com.touch_surgery.digital_surgery.presentation.theme.DigitalSurgeryTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class SurgeryActivity : ComponentActivity() {

    private val viewModel by viewModel<ProcedureViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition.value
            }
        }.setOnExitAnimationListener { splashScreenViewProvider ->
            animateLogo(splashScreenViewProvider)
        }
        setContent {
            DigitalSurgeryTheme {
                ProceduresScreen()
            }
        }
    }

    private fun animateLogo(screen: SplashScreenViewProvider) {
        val zoomX = ObjectAnimator.ofFloat(
            screen.iconView,
            View.SCALE_X,
            0.5f, 0.0f
        )
        zoomX.apply {
            interpolator = OvershootInterpolator()
            duration = 500L
            doOnEnd { screen.remove() }
        }

        val zoomY = ObjectAnimator.ofFloat(
            screen.iconView,
            View.SCALE_Y,
            0.5f, 0.0f
        )
        zoomY.apply {
            interpolator = OvershootInterpolator()
            duration = 500L
            doOnEnd { screen.remove() }
        }
        zoomX.start()
        zoomY.start()
    }
}
