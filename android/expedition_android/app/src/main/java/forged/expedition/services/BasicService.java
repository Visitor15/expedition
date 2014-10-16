package forged.expedition.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by nchampagne on 9/23/14.
 */
public abstract class BasicService extends Service {

    public static final int MSG_REGISTER_CLIENT = 100;

    public static final int MSG_UNREGISTER_CLIENT = 200;

    public static final int MSG_REGISTRATION_SUCCESSFUL = 1;

    public static final String MESSENGER_ID = "messenger_id";

    public static final int SERVICE_CONNECTED = 1;

    public static final int SERVICE_DISCONNECTED = 2;

    public static final int SERVICE_REQUEST_ERROR = 3;

    Looper mServiceLooper;

    Messenger mMessenger;

    ServiceHandler mServiceHandler;

    ServiceWorkerHandler mServiceWorkerHandler;

    List<Messenger> mClients = new ArrayList<Messenger>();

    List<HandlerThread> mThreads = new ArrayList<HandlerThread>();

    Map<Long, Messenger> mClientHandlers = new HashMap<Long, Messenger>();

    final IBinder mBinder = new ServiceBinder();

    public BasicService() {}

    protected abstract void onHandleMessage(final Message msg);

    protected abstract void onHandleWorkerMessage(final Message msg);

    @Override
    public abstract void onDestroy();

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceThread", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
        mMessenger = new Messenger(mServiceHandler);
    }

    @Override
    public abstract int onStartCommand(Intent intent, int flags, int startId);

    @Override
    public abstract boolean onUnbind(Intent intent);

    @Override
    public abstract void onRebind(Intent intent);

    private void registerClient(Message msg) {

        mClients.add(msg.replyTo);

        Bundle b = new Bundle();
        b.putLong("test", 1);

        mClientHandlers.put(new Long(1), msg.replyTo);

        try {
            msg.replyTo.send(Message.obtain(null, MSG_REGISTRATION_SUCCESSFUL));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void doCallback(Message msg) {
        try {
            msg.replyTo.send(Message.obtain(msg));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected Long generateId() {
        return new Random().nextLong();
    }

    private void unregisterClient(Message msg) {
        mClients.remove(msg.replyTo);
    }

    /*
     * CLASS
     */
    public class ServiceBinder extends Binder {
        /*
         * Returns an instance of the current service.
         */
        public BasicService getService() {
            return BasicService.this;
        }
    }

    /*
     * CLASS
     */
    protected class ServiceHandler extends Handler {

        protected ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            synchronized (this) {
                switch(msg.what) {
                    case MSG_REGISTER_CLIENT: {
                        registerClient(msg);
                    }
                    case MSG_UNREGISTER_CLIENT: {
                        unregisterClient(msg);
                    }
                }
                onHandleMessage(msg);
            }
        }
    }

    /*
     * CLASS
     */
    protected class ServiceWorkerHandler extends Handler {

        protected ServiceWorkerHandler(Looper looper) { super(looper); }

        @Override
        public void handleMessage(Message msg) {
            synchronized (this) {
                onHandleWorkerMessage(msg);
            }
        }
    }
}
