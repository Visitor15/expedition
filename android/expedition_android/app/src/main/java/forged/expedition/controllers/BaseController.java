package forged.expedition.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Message;

import forged.expedition.Expedition;
import forged.expedition.services.BasicService;
import forged.expedition.util.GenericCallback;

/**
 * Created by visitor15 on 9/30/14.
 */
public abstract class BaseController implements GenericCallback {

    private boolean isBound = false;

    protected boolean isServiceConnected = false;

    public BaseController() {

    }

    protected void initializeService(Class c, ServiceConnection serviceConnection) {
        isBound = false;
        Expedition.getReference().bindService(new Intent(Expedition.getReference(),
                        c),
                serviceConnection,
                Context.BIND_AUTO_CREATE);
    }

//    @Override
//    public void onHandleAsyncCallback(int arg1, int arg2, Message msg) {
//
//    }
    @Override
    public void onHandleGenericCallback(Message msg) {


        switch(msg.what) {
            case BasicService.SERVICE_CONNECTED: {
                onServiceConnected();
                break;
            }
            case BasicService.SERVICE_DISCONNECTED: {
                onServiceDisconnected();
                break;
            }
            case BasicService.SERVICE_REQUEST_ERROR: {
                onServiceRequestError();
                break;
            }
            case BasicService.DELIVER_RESULTS: {
                onDeliverResults();
            }
        }


    }

    public abstract void onServiceConnected();

    public abstract void onServiceDisconnected();

    public abstract void onServiceRequestError();

    public abstract void onDeliverResults();
}
