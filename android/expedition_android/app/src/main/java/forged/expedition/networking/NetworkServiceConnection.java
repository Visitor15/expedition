package forged.expedition.networking;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import forged.expedition.controllers.ControllerCallback;
import forged.expedition.services.NetworkService;
import forged.expedition.util.GenericAsyncCallback;
import forged.expedition.util.GenericCallback;

/**
 * Created by nchampagne on 9/23/14.
 */
public class NetworkServiceConnection extends GenericAsyncCallback implements ServiceConnection {

    public static final int SERVICE_CONNECTED = 1;

    public static final int SERVICE_DISCONNECTED = 2;

    public static final int REQUEST_SUCCESS = 100;

    public static final int REQUEST_FAILURE = 200;

    public static final int REQUEST_ERROR = 300;

    public static final String RESPONSE_DATA = "response_data";

    private NetworkService mService;

    private boolean isBound;

    private Messenger mMessenger;

    private Messenger mCallbackMessenger = new Messenger(new ConnectionServiceHandler());

    private GenericCallback mCallback;

    public NetworkServiceConnection() {}

    public NetworkServiceConnection(GenericCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//        mServiceBinder = (BasicService.ServiceBinder) iBinder;
        mMessenger = new Messenger(iBinder);
        isBound = true;

//        callback(Message.obtain(null, SERVICE_CONNECTED));
//        Bundle b = new Bundle();
//        b.putInt(SERVICE_RESPONSE, SERVICE_CONNECTED);
//        doCallback(b);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
        mService = null;
    }

//    private void callback(Message msg) {
//        mCallback.onHandleGenericCallback(msg);
//    }

    private void doCallback(Bundle b) {
        mCallback.onRequestSuccess(b);
    }

    private void doErrorCallback(Bundle b) {
        mCallback.onRequestFailure(b);
    }

    public void sendRequestForResponse(String url, ControllerCallback callback) {
        try {
            Bundle b = new Bundle();
            b.putSerializable("callback", callback);
            b.putString(NetworkService.REQUEST_DATA, url);
            Message msg = obtainNewRequestMessage();
            msg.what = NetworkService.SEND_REQUEST;
            msg.setData(b);
            mMessenger.send(msg);
        } catch (RemoteException e) {
            throw new RuntimeException("Error creating request.", e);
        }
    }

    public void sendRequestCommand(String url, Class type, NetworkService.RequestCommand command, ControllerCallback callback) {
        Bundle b = new Bundle();
        b.putSerializable("callback", callback);
        b.putString(NetworkService.REQUEST_TYPE, type.toString());
        b.putString(NetworkService.COMMAND_DATA, command.name());
        b.putString(NetworkService.REQUEST_URL, url);

        Message msg = obtainNewRequestMessage();
        msg.what = NetworkService.SEND_COMMAND_REQUEST;
        msg.setData(b);

        try {
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private Message obtainNewRequestMessage() {
        Message msg = new Message();
        msg.replyTo = mCallbackMessenger;
        return msg;
    }

    private void parseData(Bundle b) {
        String s = b.getString(NetworkService.REQUEST_DATA);
    }

    public boolean isBound() {
        return isBound;
    }

    private void onHandleMessage(Message msg) {
        switch(msg.what) {
            case NetworkService.REQUEST_SUCCESS: {
                doCallback(msg.getData());
                break;
            }
            case NetworkService.REQUEST_FAILURE: {
                doErrorCallback(msg.getData());
                break;
            }
            case NetworkService.REQUEST_ERROR: {
                break;
            }
            default: {
                return;
            }
        }
    }

    @Override
    public void onHandleAsyncCallback(Bundle b) {
        doCallback(b);
    }

    /*
     * CLASS
     */
    protected class ConnectionServiceHandler extends Handler {

        protected ConnectionServiceHandler() {}

        @Override
        public void handleMessage(Message msg) {
            synchronized (this) {
                onHandleMessage(msg);
            }
        }
    }
}
