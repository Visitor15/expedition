package forged.expedition.services;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;

import forged.expedition.networking.NetworkServiceConnection;
import forged.expedition.networking.http.HttpConnector;

/**
 * Created by visitor15 on 9/22/14.
 */
public class NetworkService extends BasicService {

    public static final int SEND_REQUEST = 0;

    public static final int REQUEST_FINISHED = 100;

    public static final String REQUEST_DATA = "request_data";

    public static final String REQUEST_ID = "request_id";

    private HttpConnector httpConnector;


    public NetworkService() {
        super();
        httpConnector = new HttpConnector();
    }

    @Override
    protected void onHandleMessage(Message msg) {
        switch (msg.what) {
            case SEND_REQUEST: {
                handleSendRequestAsync(Message.obtain(msg));
                break;
            }
            default: {
                return;
            }
        }
    }

    @Override
    protected void onHandleWorkerMessage(Message msg) {
        Bundle b = null;
        switch (msg.what) {
            case SEND_REQUEST: {
                b = makeHttpRequestForBundle(msg.getData());

                break;
            }
            case REQUEST_FINISHED: {
                System.out.println("REQUEST FINISHED");
                Message message = Message.obtain(null, NetworkService.REQUEST_FINISHED);
                msg.getTarget().sendMessage(message);
                break;
            }
            default: {
                return;
            }
        }

        if(b == null) {
            b = new Bundle();
        }

        msg.setData(b);
        msg.what = NetworkServiceConnection.REQUEST_SUCCESS;
        try {
            msg.replyTo.send(Message.obtain(msg));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(Thread.currentThread() instanceof HandlerThread) {
            ((HandlerThread) Thread.currentThread()).quit();
        }

//        msg.getTarget().sendMessage(Message.obtain(msg));
//        doCallback(Message.obtain(msg));
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

//    private void makeHttpRequest(Bundle b) {
//        String response = httpConnector.postForResponse(b.getString(REQUEST_DATA));
//        System.out.println(response);
//    }

    private Bundle makeHttpRequestForBundle(Bundle b) {
        String response = httpConnector.postForResponse(b.getString(REQUEST_DATA));
        System.out.println(response);
        b.putString(NetworkService.REQUEST_DATA, response);
        return b;
    }

    private void handleSendRequest(final Bundle bundle) {
        System.out.println("GOT HANDLE REQUEST COMMAND");
    }

    private void handleSendRequestAsync(final Message msg) {
        Handler mHandler = spawnWorkerThread();
        mHandler.sendMessage(msg);
    }

    private Handler spawnWorkerThread() {
        HandlerThread thread = new HandlerThread("NetworkServiceWorkerThread", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        return new ServiceWorkerHandler(thread.getLooper());
    }

    public void sendRequest() {

    }
}
