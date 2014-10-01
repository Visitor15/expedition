package forged.expedition.controllers;

import android.os.Message;

import forged.expedition.util.GenericCallback;

/**
 * Created by visitor15 on 9/30/14.
 */
public abstract class BaseController implements GenericCallback {

    @Override
    public abstract void onHandleGenericCallback(int arg1, int arg2, Message msg);
}
