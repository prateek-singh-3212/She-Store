package com.example.shestore.Utility

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Interpolator
import android.widget.ImageView
import com.example.shestore.R

class NavigationIconClickListener @JvmOverloads internal constructor(
    private val context: Context, private val sheet: View, private val interpolator: Interpolator? = null,
    private val openIcon: Drawable? = null, private val closeIcon: Drawable? = null) : View.OnClickListener {

    private val animatorSet = AnimatorSet()
    private val height: Int
    private var backdropShown = false

    init {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        height = displayMetrics.heightPixels
    }

    fun getHeight() : Int = (context as Activity).findViewById<View>(R.id.nav_backdrop_scrollview).measuredHeight - (context as Activity).findViewById<View>(R.id.action_bar).measuredHeight

    override fun onClick(view: View) {
        backdropShown = !backdropShown

        // Cancel the existing animations
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()


        /**
         * Added Custom Adaptable size of nav drawer
         */

        val translateY =
            (context as Activity).findViewById<View>(R.id.nav_backdrop).measuredHeight- (context as Activity).findViewById<View>(R.id.action_bar).measuredHeight

        val animator = ObjectAnimator.ofFloat(
            sheet,
            "translationY",
            (if (backdropShown) translateY else 0).toFloat()
        )

        /*
         * Changes the recyclerview opactiy when open because we don't want user to focus on that.
         */
        if (backdropShown) sheet.alpha = 0.5f else sheet.alpha = 1f


        animator.duration = 500
        if (interpolator != null) {
            animator.interpolator = interpolator
        }
        animatorSet.play(animator)
        animator.start()
    }

    private fun updateIcon(view: View) {
        if (openIcon != null && closeIcon != null) {
            if (view !is ImageView) {
                throw IllegalArgumentException("updateIcon() must be called on an ImageView")
            }
            if (backdropShown) {
                view.setImageDrawable(closeIcon)
            } else {
                view.setImageDrawable(openIcon)
            }
        }
    }
}

/**
 * This is the extention function used to convert the pxiels into Dp.
 */
fun Int.pxToDp(context: Context) : Float = this / (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toFloat()