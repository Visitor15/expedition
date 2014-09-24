package forged.expedition.services;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.os.Process;

/**
 * Created by nchampagne on 9/23/14.
 */
public abstract class BasicService extends Service {

    Looper mServiceLooper;

    Messenger mMessenger;

    ServiceHandler mServiceHandler;

    final IBinder mBinder = new ServiceBinder();

    public BasicService() {}

    protected abstract void onHandleMessage(final Message msg);

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
                onHandleMessage(msg);
            }
        }
    }
}
