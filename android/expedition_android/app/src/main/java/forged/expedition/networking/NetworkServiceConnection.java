package forged.expedition.networking;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import forged.expedition.MainActivity;
import forged.expedition.services.BasicService;
import forged.expedition.services.NetworkService;

/**
 * Created by nchampagne on 9/23/14.
 */
public class NetworkServiceConnection implements ServiceConnection {

    private BasicService.ServiceBinder mServiceBinder;

    private NetworkService mService;

    private boolean isBound;

    private Messenger mMessenger;

    private MainActivity tempCallback;

    public NetworkServiceConnection(MainActivity cb) {
        this.tempCallback = cb;
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//        mServiceBinder = (BasicService.ServiceBinder) iBinder;
        mMessenger = new Messenger(iBinder);
        isBound = true;

        tempCallback.serviceConnected();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
        mServiceBinder = null;
        mService = null;
    }

    public void sendRequest() {
        try {
            mMessenger.send(Message.obtain(null, NetworkService.SEND_REQUEST, "test_url"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isBound() {
        return isBound;
    }
}
