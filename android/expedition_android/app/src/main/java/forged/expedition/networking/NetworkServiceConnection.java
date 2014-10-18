package forged.expedition.networking;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import java.util.Random;

import forged.expedition.controllers.ControllerCallback;
import forged.expedition.services.BasicService;
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

    private NetworkService mService;

    private boolean isBound;

    private Messenger mMessenger;

    private Messenger mCallbackMessenger = new Messenger(new ConnectionServiceHandler());

    private GenericCallback mCallback;

//    private GenericAsyncCallback asyncCallback;

    public NetworkServiceConnection() {}

    public NetworkServiceConnection(GenericCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//        mServiceBinder = (BasicService.ServiceBinder) iBinder;
        mMessenger = new Messenger(iBinder);
        isBound = true;

        callback(Message.obtain(null, SERVICE_CONNECTED));
//        Bundle b = new Bundle();
//        b.putInt(SERVICE_RESPONSE, SERVICE_CONNECTED);
//        doCallback(b);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
        mService = null;
    }

    private void callback(Message msg) {
        mCallback.onHandleGenericCallback(msg);
    }

    private void doCallback(Bundle b) {
        mCallback.onHandleGenericCallback(b);
    }

    public void sendRequest(String url) {
        try {
            Bundle b = new Bundle();
            b.putString(NetworkService.REQUEST_DATA, url);
            Message msg = obtainNewRequestMessage();
            msg.what = NetworkService.SEND_REQUEST;
//            Message msg = Message.obtain(new ConnectionServiceHandler(), NetworkService.SEND_REQUEST);
            msg.setData(b);
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendRequestForResponse(String url, int id) {
        try {
            Bundle b = new Bundle();
            b.putString(NetworkService.REQUEST_DATA, url);
            b.putInt(NetworkService.REQUEST_ID, id);
            Message msg = obtainNewRequestMessage();
            msg.what = NetworkService.SEND_REQUEST;
            msg.setData(b);
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public long sendRequestForResponse(String url) {
        try {
            long id = new Random().nextLong();
            Bundle b = new Bundle();
            b.putString(NetworkService.REQUEST_DATA, url);
            b.putLong(NetworkService.REQUEST_ID, id);
            Message msg = obtainNewRequestMessage();
            msg.what = NetworkService.SEND_REQUEST;
            msg.setData(b);
            mMessenger.send(msg);

            return id;
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Error creating request.");
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
            case REQUEST_SUCCESS: {
                doCallback(msg.getData());
//                callback(0, 0, Message.obtain(null, REQUEST_SUCCESS));
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
