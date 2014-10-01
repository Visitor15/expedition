package forged.expedition;

import android.app.Application;

/**
 * Created by visitor15 on 9/30/14.
 */
public class Expedition extends Application {

    private static Expedition _singleton;

    @Override
    public void onCreate() {
        Expedition._singleton = this;
        super.onCreate();
    }

    public static Expedition getReference() {
        return Expedition._singleton;
    }
}
