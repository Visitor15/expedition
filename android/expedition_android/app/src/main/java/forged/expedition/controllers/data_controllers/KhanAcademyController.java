package forged.expedition.controllers.data_controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import forged.expedition.Expedition;
import forged.expedition.KhanAcademy;
import forged.expedition.controllers.BaseController;
import forged.expedition.networking.NetworkServiceConnection;
import forged.expedition.services.NetworkService;
import forged.expedition.topics.Topic;

/**
 * Created by visitor15 on 9/30/14.
 */
public class KhanAcademyController extends BaseController {

    private final NetworkServiceConnection networkServiceConnection;

    private boolean isBound;

    public KhanAcademyController() {
        networkServiceConnection = new NetworkServiceConnection(this);
        initializeService();
    }

    @Override
    public void onHandleGenericCallback(int arg1, int arg2, Message msg) {
        switch(msg.what) {
            case NetworkServiceConnection.SERVICE_CONNECTED: {
                System.out.println("SERVICE_CONNECTED");
                isBound = true;
                getAllTopics();
                break;
            }
            case NetworkServiceConnection.SERVICE_DISCONNECTED: {
                System.out.println("SERVICE_DISCONNECTED");
                isBound = false;
                break;
            }
            case NetworkService.REQUEST_FINISHED: {

                Bundle b = msg.getData();
                break;
            }
            default: {
                return;
            }
        }
    }

    private void initializeService() {
        isBound = false;
        Expedition.getReference().bindService(new Intent(Expedition.getReference(),
                        NetworkService.class),
                networkServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    public List<Topic> getAllTopics() {
        List<Topic> topicList = new ArrayList<Topic>();

        networkServiceConnection.sendRequest(KhanAcademy.TOPIC_TREE_URL);

        return topicList;
    }
}
