package com.thomaskioko.rxretrofit.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Thomas Kioko
 */

public class DisplayUtils {

    /**
     * @param context     {@link Context} Context the method is called.
     * @param message     Message to be displayed.
     * @param longOrShort {@link Boolean} Determines the length of the toast.
     */
    public static void showToastMessage(Context context, String message, boolean longOrShort) {
        Toast.makeText(context, message, (longOrShort ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)).show();
    }
}
