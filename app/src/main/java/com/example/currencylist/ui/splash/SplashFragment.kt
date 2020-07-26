package com.example.currencylist.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.currencylist.R
import com.example.currencylist.common.lazyFast

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val navigation by lazyFast { findNavController() }
    private val delayedAction = Runnable { navigation.navigate(R.id.action_splashFragment_to_listFragment) }
    private val handler by lazyFast { Handler() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler.postDelayed(delayedAction, 3000)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(delayedAction)
    }
}