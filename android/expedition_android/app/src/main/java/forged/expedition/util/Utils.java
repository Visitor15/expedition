package forged.expedition.util;

import android.content.Context;

/**
 * Created by visitor15 on 10/27/14.
 */
public class Utils {

    public Utils() {}

    public static int dipsToPixels(Context c, int dips) {
        final float scale = c.getResources().getDisplayMetrics().density;
        return (int) (dips * scale + 0.5f);
    }
}
