package ru.nikitazhelonkin.coinbalance.ui.widget;


import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;

public class TintHelper {

    public static Drawable applyTint(Drawable drawable, int tintColor) {
        if (drawable != null) {
            Drawable drawableWrapped = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawableWrapped, tintColor);
            return drawableWrapped;
        }
        return null;
    }
}
