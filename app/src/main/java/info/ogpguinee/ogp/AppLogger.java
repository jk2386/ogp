package info.ogpguinee.ogp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class AppLogger {
    public static final boolean DEBUG = true; //// TODO: 16-11-2017 Make it false for product.
    private static Context sContext = MyApp.getInstance().getApplicationContext();

    // Logger
    public static class Logger {
        //Verbose
        public static void verbose(String tag, String message) {
            if (DEBUG)
                Log.v(tag, message);
        }

        public static void verbose(String tag, String message, Throwable tr) {
            if (DEBUG)
                Log.v(tag, message, tr);
        }

        // Debug
        public static void debug(String tag, String message) {
            if (DEBUG)
                Log.d(tag, message);
        }

        public static void debug(String tag, String message, Throwable throwable) {
            if (DEBUG)
                Log.d(tag, message, throwable);
        }

        // Information
        public static void info(String tag, String message) {
            if (DEBUG)
                Log.i(tag, message);
        }

        public static void info(String tag, String message, Throwable throwable) {
            if (DEBUG)
                Log.i(tag, message, throwable);
        }

        //Warning
        public static void warn(String tag, String message) {
            if (DEBUG)
                Log.w(tag, message, new Exception(message));
        }

        public static void warn(String tag, String message, Throwable throwable) {
            if (DEBUG)
                Log.w(tag, message, throwable);
        }

        public static void warn(String tag, Throwable throwable) {
            Log.w(tag, throwable);
        }

        //Error
        public static void error(String tag, String message) {
            if (DEBUG)
                Log.e(tag, message);
        }

        public static void error(String tag, String message, Throwable throwable) {
            if (DEBUG)
                Log.e(tag, message, throwable);
        }
    }


    public static void showToast(String TAG, String message) {
        if (DEBUG)
            try {
                Toast.makeText(sContext, message, Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Logger.error(TAG, "showToast: " + ex.getMessage(), ex);
            }
    }


    public static void showToastForDebug(String TAG, String message) {
        if (DEBUG)
            try {
                Toast.makeText(sContext, message, Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Logger.error(TAG, "showToast: " + ex.getMessage(), ex);
            }
    }


}