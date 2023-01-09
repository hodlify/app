package ru.nikitazhelonkin.coinbalance.ui.widget

import androidx.annotation.StringRes
import android.widget.Toast
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import ru.nikitazhelonkin.coinbalance.R
import android.widget.TextView
import android.view.Gravity
import androidx.core.content.res.ResourcesCompat

object AppToast {

    @JvmStatic
    fun make(context: Context, @StringRes stringResId: Int, duration: Int): Toast {
        return make(context, context.getString(stringResId), duration)
    }

    @JvmStatic
    fun make(context: Context, text: CharSequence, duration: Int): Toast {
        @SuppressLint("ShowToast") val toast = Toast.makeText(context, text, duration)
        toast.view?.let { snackBarView ->
            snackBarView.setBackgroundResource(R.drawable.bg_toast)
            val textView = snackBarView.findViewById<TextView>(android.R.id.message)
            textView.gravity = Gravity.CENTER
            textView.setShadowLayer(0f, 0f, 0f, 0)
            textView.setTextColor(Color.WHITE)
            textView.typeface = ResourcesCompat.getFont(context, R.font.roboto_condensed_regular)
            val offset = context.resources.displayMetrics.density.toInt()
            toast.setGravity(Gravity.BOTTOM or Gravity.FILL_HORIZONTAL, 0, offset)
        }
        return toast
    }
}