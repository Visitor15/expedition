package forged.expedition.util;

import android.os.Bundle;
import android.os.Message;

/**
 * Created by visitor15 on 9/30/14.
 */
public interface GenericCallback {
    public void onRequestSuccess(Bundle b);

    public void onRequestFailure(Bundle b);
}
