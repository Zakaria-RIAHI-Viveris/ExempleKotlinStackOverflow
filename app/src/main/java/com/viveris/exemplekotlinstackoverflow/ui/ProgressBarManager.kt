package com.viveris.exemplekotlinstackoverflow.ui

import android.view.animation.AlphaAnimation
import android.widget.FrameLayout
import com.viveris.exemplekotlinstackoverflow.extention.changeVisibility

class ProgressBarManager(private val progressBarHolder: FrameLayout?) {

    fun onProgressBarStateChange(isLoading: Boolean) {
        progressBarHolder?.apply {
            val animation: AlphaAnimation = when(isLoading) {
                true -> AlphaAnimation(0f, 1f)
                else -> AlphaAnimation(1f, 0f)
            }
            animation.duration = 200
            this.animation = animation
            changeVisibility(isLoading)
        }
    }
}