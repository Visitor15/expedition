package forged.expedition.controllers.data_controllers;

import android.os.Bundle;

import forged.expedition.controllers.BaseController;
import forged.expedition.networking.NetworkServiceConnection;
import forged.expedition.services.NetworkService;

/**
 * Created by visitor15 on 9/30/14.
 */
public class RequestController extends BaseController {

    private NetworkServiceConnection networkServiceConnection;

    private boolean isBound;

    public RequestController() {
        networkServiceConnection = new NetworkServiceConnection(RequestController.this);
        initializeService(NetworkService.class, networkServiceConnection);
    }

    @Override
    public void onServiceConnected() {
        // Automatically called from BaseController.
    }

    @Override
    public void onServiceDisconnected() {
        // Automatically called from BaseController.
    }

    @Override
    public void onServiceRequestError() {
        // Automatically called from BaseController.
    }

    @Override
    public void onDeliverResults() {

    }

//    public List<Topic> getAllTopics() {
//        List<Topic> topicList = new ArrayList<Topic>();
//
//        networkServiceConnection.sendRequest(KhanAcademy.TOPIC_TREE_URL);
//
//        return topicList;
//    }
//
//    public List<MathTopic> getAllMathTopics() {
//        List<MathTopic> topicList = new ArrayList<MathTopic>();
//
//        networkServiceConnection.sendRequest(KhanAcademy.TOPIC_TREE_URL);
//
//
//        return topicList;
//    }
//
//    public List<ScienceTopic> getAllScienceTopics() {
//        List<ScienceTopic> topicList = new ArrayList<ScienceTopic>();
//
//        networkServiceConnection.sendRequest(KhanAcademy.getTopicUrl(new ScienceTopic()));
//
//
//        return topicList;
//    }

    @Override
    public void onHandleGenericCallback(Bundle b) {
        System.out.println("Got bundle: " + b);
        if(b.containsKey("callback_data")) {
            isBound = true;
//            List<ScienceTopic> mathTopics = getAllScienceTopics();
        }

        if(b.containsKey(NetworkService.REQUEST_DATA)) {
            String response = b.getString(NetworkService.REQUEST_DATA);
            System.out.println("GOT RESPONSE: " + response);
        }
    }
}
