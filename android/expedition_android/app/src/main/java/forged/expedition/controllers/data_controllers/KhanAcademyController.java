package forged.expedition.controllers.data_controllers;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import forged.expedition.KhanAcademy;
import forged.expedition.controllers.BaseController;
import forged.expedition.networking.NetworkServiceConnection;
import forged.expedition.services.NetworkService;
import forged.expedition.topics.MathTopic;
import forged.expedition.topics.Topic;

/**
 * Created by visitor15 on 9/30/14.
 */
public class KhanAcademyController extends BaseController {

    private final NetworkServiceConnection networkServiceConnection;

    private boolean isBound;

    public KhanAcademyController() {
        networkServiceConnection = new NetworkServiceConnection(this);
        initializeService(NetworkService.class, networkServiceConnection);
    }

//    @Override
//    public void onHandleAsyncCallback(int arg1, int arg2, Message msg) {
//        switch(msg.what) {
//            case NetworkServiceConnection.SERVICE_CONNECTED: {
//                System.out.println("SERVICE_CONNECTED");
//                isBound = true;
//                getAllTopics();
//                break;
//            }
//            case NetworkServiceConnection.SERVICE_DISCONNECTED: {
//                System.out.println("SERVICE_DISCONNECTED");
//                isBound = false;
//                break;
//            }
//            case NetworkService.REQUEST_FINISHED: {
//
//                Bundle b = msg.getData();
//                break;
//            }
//            default: {
//                return;
//            }
//        }
//    }



    public List<Topic> getAllTopics() {
        List<Topic> topicList = new ArrayList<Topic>();

        networkServiceConnection.sendRequest(KhanAcademy.TOPIC_TREE_URL);

        return topicList;
    }

    public List<MathTopic> getAllMathTopics() {
        List<MathTopic> topicList = new ArrayList<MathTopic>();

        networkServiceConnection.sendRequest(KhanAcademy.getTopicUrl(new MathTopic()));


        return topicList;
    }

    @Override
    public void onHandleGenericCallback(Bundle b) {
        System.out.println("Got bundle: " + b);
        if(b.containsKey("callback_data")) {
            isBound = true;
            List<MathTopic> mathTopics = getAllMathTopics();
        }

        if(b.containsKey(NetworkService.REQUEST_DATA)) {
            String response = b.getString(NetworkService.REQUEST_DATA);
            System.out.println("GOT RESPONSE: " + response);
        }
    }
}
