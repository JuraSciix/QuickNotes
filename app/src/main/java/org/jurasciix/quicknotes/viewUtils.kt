package org.jurasciix.quicknotes

import android.view.View
import android.view.animation.Animation

fun View.animateClick(onClickListener: (View) -> Unit) {
    // Заметка: отключаем кликабельность на время анимации нажатия,
    // чтобы избежать двойных нажатий.
    val animListener = object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {
            isClickable = false
            onClickListener(this@animateClick)
        }

        override fun onAnimationEnd(animation: Animation) {
            isClickable = true
        }

        override fun onAnimationRepeat(animation: Animation) {
            // nope
        }
    }
    setOnClickListener { view ->
        val anim = Animations.beautifulSquash()
        anim.setAnimationListener(animListener)
        view.clearAnimation()
        view.startAnimation(anim)
    }
}