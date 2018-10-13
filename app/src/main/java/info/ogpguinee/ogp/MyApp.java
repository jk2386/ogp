package info.ogpguinee.ogp;

import android.app.Application;

public class MyApp extends Application {

    public static MyApp sMyApp;

    public MyApp() {
        sMyApp = this;
    }

    public static MyApp getInstance() {
        if (sMyApp == null) {
            throw new IllegalStateException();
        }

        return sMyApp;
    }
}
