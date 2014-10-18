package forged.expedition.controllers.data_controllers;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import forged.expedition.KhanAcademy;
import forged.expedition.controllers.BaseController;
import forged.expedition.controllers.ControllerCallback;
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
        networkServiceConnection = new NetworkServiceConnection(KhanAcademyController.this);
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
//        onReceiveResults();
    }

    public List<Topic> getAllTopics() {
        List<Topic> topicList = new ArrayList<Topic>();

        networkServiceConnection.sendRequest(KhanAcademy.TOPIC_TREE_URL);

        return topicList;
    }

    public List<MathTopic> getAllMathTopics() {
        List<MathTopic> topicList = new ArrayList<MathTopic>();

//        networkServiceConnection.sendRequestForResponse(KhanAcademy.getTopicUrl(MathTopic.MATH_ID), KhanAcademy.TopicIdentifier.MATH);

//        long id = networkServiceConnection.sendRequestForResponse(KhanAcademy.getTopicUrl(MathTopic.MATH_ID));

        return topicList;
    }

    public void getAllMathTopics(ControllerCallback callback) {
        networkServiceConnection.sendRequestForResponse(KhanAcademy.getTopicUrl(MathTopic.MATH_ID), callback);
    }

    @Override
    public void onHandleGenericCallback(Bundle b) {


        ((ControllerCallback) b.getSerializable("callback")).handleCallback(b);

//        System.out.println("Got bundle: " + b);
//        if(b.containsKey("callback_data")) {
//            isBound = true;
//            List<MathTopic> mathTopics = getAllMathTopics();
//        }
//
//        if(b.containsKey(NetworkService.REQUEST_DATA)) {
//            String response = b.getString(NetworkService.REQUEST_DATA);
//            System.out.println("GOT RESPONSE: " + response);
//        }
    }
}
