package com.davidsmt.vehicleapp.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.davidsmt.vehicleapp.R
import kotlinx.android.synthetic.main.layout_loading_error_view.view.*

/**
 * Created by David SMT on 20/01/2019.
 */
class LoadingErrorView : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_loading_error_view, this, true)
        setBackgroundColor(ResourcesCompat.getColor(resources, R.color.white, null))
        startLottieAnimation()
    }

    fun finishLoading(actionAfterLoadingEnds: () -> Unit) {
        stopLottieAnimation {
            visibility = View.GONE
            actionAfterLoadingEnds.invoke()
        }
    }

    fun showErrorMessage(errorMessage: String, retryButtonAction: () -> Unit) {
        error_label.text = errorMessage
        error_label.visibility = View.VISIBLE
        error_label.animate().alpha(1f)

        retry_button.visibility = View.VISIBLE
        retry_button.animate().alpha(1f)
        retry_button.setOnClickListener {
            error_label.text = ""
            error_label.visibility = View.GONE
            error_label.alpha = 0f
            retry_button.visibility = View.GONE
            retry_button.alpha = 0f
            startLottieAnimation()

            retryButtonAction.invoke()
        }

        stopLottieAnimation(null)
    }

    private fun startLottieAnimation() {
        loading_animation.playAnimation()
    }

    private fun stopLottieAnimation(actionAfterLoadingEnds: (() -> Unit)?) {
        // With this delay, you get a better effect, so loading is visible enough
        // for the user, animations ends and then content is presented
        postDelayed({
            loading_animation.repeatCount = 0
            actionAfterLoadingEnds?.invoke()
        }, LOTTIE_ANIMATION_STOP_DELAY)
    }

    companion object {
        private const val LOTTIE_ANIMATION_STOP_DELAY: Long = 2000
    }

}