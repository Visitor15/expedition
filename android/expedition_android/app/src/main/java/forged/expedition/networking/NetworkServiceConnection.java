package forged.expedition.networking;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import forged.expedition.services.BasicService;
import forged.expedition.services.NetworkService;

/**
 * Created by nchampagne on 9/23/14.
 */
public class NetworkServiceConnection implements ServiceConnection {

    private BasicService.ServiceBinder mServiceBinder;

    private NetworkService mService;

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        mServiceBinder = (BasicService.ServiceBinder) iBinder;
        mService = (NetworkService) mServiceBinder.getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
}
