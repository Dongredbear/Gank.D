package net.dong.gankd.model;

import android.app.Application;

/**
 * Created by dong on 16/4/19.
 */
public class MApplication extends Application {
    private static MApplication ourInstance;

    public static MApplication getMApplication() {
        synchronized (MApplication.class){
            if (ourInstance == null){
                ourInstance = new MApplication();
            }
        }
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }

}
