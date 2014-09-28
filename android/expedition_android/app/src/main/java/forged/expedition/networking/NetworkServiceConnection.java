package forged.expedition.networking;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
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

    public static final int REQUEST_SUCCESS = 100;

    public static final int REQUEST_FAILURE = 200;

    public static final int REQUEST_ERROR = 300;

    private NetworkService mService;

    private boolean isBound;

    private Messenger mMessenger;

    private Messenger mCallbackMessenger = new Messenger(new ServiceHandler());

    private MainActivity tempCallback;

    public NetworkServiceConnection(MainActivity cb) {
        this.tempCallback = cb;
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//        mServiceBinder = (BasicService.ServiceBinder) iBinder;
        mMessenger = new Messenger(iBinder);
        isBound = true;

        registerConnection();

        tempCallback.serviceConnected();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
        mService = null;
    }

    private void registerConnection() {
        try {
            Message msg = Message.obtain(null, BasicService.MSG_REGISTER_CLIENT);
            msg.replyTo = mCallbackMessenger;
            mMessenger.send(msg);
        } catch (RemoteException e) {
            // In this case the service has crashed before we could even do anything with it
        }
    }

    public void sendRequest() {
        try {
            Bundle b = new Bundle();
            b.putString(NetworkService.REQUEST_DATA, "http://www.khanacademy.org/api/v1/playlists");
            Message msg = Message.obtain(null, NetworkService.SEND_REQUEST);
            msg.setData(b);
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void exitService() {
        try {
            mMessenger.send(Message.obtain(null, BasicService.MSG_UNREGISTER_CLIENT));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void parseData(Bundle b) {
        String s = b.getString(NetworkService.REQUEST_DATA);
    }

    public boolean isBound() {
        return isBound;
    }

    private void onHandleMessage(Message msg) {
        switch(msg.what) {
            case REQUEST_SUCCESS: {
                parseData(msg.getData());
                break;
            }
            case REQUEST_FAILURE: {
                break;
            }
            case REQUEST_ERROR: {
                break;
            }
            case BasicService.MSG_REGISTRATION_SUCCESSFUL: {
                break;
            }
            default: {
                return;
            }
        }
    }

    /*
     * CLASS
     */
    protected class ServiceHandler extends Handler {

        protected ServiceHandler() {

        }

        @Override
        public void handleMessage(Message msg) {
            synchronized (this) {
                onHandleMessage(msg);
            }
        }
    }
}
