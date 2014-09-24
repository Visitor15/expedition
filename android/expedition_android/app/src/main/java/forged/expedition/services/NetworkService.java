package forged.expedition.services;

import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;

/**
 * Created by visitor15 on 9/22/14.
 */
public class NetworkService extends BasicService {

    public static final int SEND_REQUEST = 0;


    public NetworkService() {
        super();
    }

    @Override
    protected void onHandleMessage(Message msg) {
        switch (msg.what) {
            case SEND_REQUEST: {
                handleSendRequest(msg.getData());
                break;
            }
            default: {
                return;
            }
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        HandlerThread thread = new HandlerThread("NetworkServiceWorkerThread", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        mServiceLooper = thread.getLooper();
        return START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    @Override
    public void onRebind(Intent intent) {

    }

    private void handleSendRequest(final Bundle bundle) {
        System.out.println("GOT HANDLE REQUEST COMMAND");
    }

    public void sendRequest() {

    }
}
