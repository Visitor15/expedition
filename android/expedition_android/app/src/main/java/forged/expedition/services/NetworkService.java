package forged.expedition.services;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;

import forged.expedition.networking.http.HttpConnector;

/**
 * Created by visitor15 on 9/22/14.
 */
public class NetworkService extends BasicService {

    public static final int SEND_REQUEST = 0;

    public static final int REQUEST_FINISHED = 100;

    public static final String REQUEST_DATA = "request_data";

    private HttpConnector httpConnector;


    public NetworkService() {
        super();
        httpConnector = new HttpConnector();
    }

    @Override
    protected void onHandleMessage(Message msg) {
        switch (msg.what) {
            case SEND_REQUEST: {
                Bundle b = msg.getData();
                handleSendRequestAsync(b);
                break;
            }
            default: {
                return;
            }
        }
    }

    @Override
    protected void onHandleWorkerMessage(Message msg) {
        switch (msg.what) {
            case SEND_REQUEST: {
                makeHttpRequest(msg.getData());
            }
            case REQUEST_FINISHED: {
                System.out.println("REQUEST FINISHED");
                break;
            }
            default: {
                return;
            }
        }
    }

    @Override
    public void onDestroy() {
        System.out.println("GOT HANDLE REQUEST COMMAND");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    @Override
    public void onRebind(Intent intent) {

    }

    private void makeHttpRequest(Bundle b) {
        String response = httpConnector.postForReponse(b.getString(REQUEST_DATA));
        System.out.println(response);
    }

    private void handleSendRequest(final Bundle bundle) {
        System.out.println("GOT HANDLE REQUEST COMMAND");
    }

    private void handleSendRequestAsync(final Bundle bundle) {
        Handler mHandler = spawnWorkerThread();
        Message msg = Message.obtain(mHandler, SEND_REQUEST);
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }

    private Handler spawnWorkerThread() {
        HandlerThread thread = new HandlerThread("NetworkServiceWorkerThread", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        ServiceWorkerHandler mServiceWorkerHandler = new ServiceWorkerHandler(thread.getLooper());
//        mThreadsMap.put(thread, mServiceWorkerHandler);
        return mServiceWorkerHandler;
    }

    public void sendRequest() {

    }
}
