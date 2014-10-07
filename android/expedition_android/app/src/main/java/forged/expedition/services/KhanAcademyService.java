package forged.expedition.services;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import forged.expedition.Expedition;
import forged.expedition.networking.NetworkServiceConnection;
import forged.expedition.topics.MathTopic;
import forged.expedition.util.GenericAsyncCallback;

/**
 * Created by visitor15 on 10/6/14.
 */
public class KhanAcademyService extends GenericAsyncCallback {

    private NetworkServiceConnection networkServiceConnection;

    public KhanAcademyService() {}

    private void init() {
        networkServiceConnection = new NetworkServiceConnection();
    }

    public void bindToService() {
        Expedition.getReference().bindService(new Intent(Expedition.getReference(),
                NetworkService.class),
                networkServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    public List<MathTopic> getAllMathTopics() {
        List<MathTopic> mathTopics = new ArrayList<MathTopic>();

        return mathTopics;
    }

    @Override
    public void onHandleAsyncCallback() {

    }
}
