package com.rivent_chris.proguardtest.Utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by riven_chris on 2017/8/23.
 */

public class DateUtil {
    private static String Y_M_D = "yyyy-MM-dd HH:mm:ss";
    private static String TAG = "DateUtil";

    public static String getDate(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat format = new SimpleDateFormat(Y_M_D);
        String dateString = format.format(date);
        Log.d(TAG, TAG + ": " + dateString);
        return dateString;
    }
}
