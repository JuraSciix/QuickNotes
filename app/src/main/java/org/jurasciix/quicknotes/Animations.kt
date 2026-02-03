package org.jurasciix.quicknotes

import android.view.animation.Animation
import android.view.animation.ScaleAnimation

object Animations {

    fun beautifulSquash(duration: Long = 100L): Animation {
        val anim = ScaleAnimation(
            1.0f, 0.95f,
            1.0f, 0.95f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )

        anim.duration = duration

        // Хитрость, чтобы избежать дублирования анимации
        anim.repeatCount = 1
        anim.repeatMode = Animation.REVERSE

        return anim
    }
}