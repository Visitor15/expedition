package forged.expedition.controllers.data_controllers;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import forged.expedition.KhanAcademy;
import forged.expedition.controllers.BaseController;
import forged.expedition.controllers.ControllerCallback;
import forged.expedition.controllers.DataCallback;
import forged.expedition.networking.NetworkServiceConnection;
import forged.expedition.services.NetworkService;
import forged.expedition.topics.MathTopic;
import forged.expedition.topics.ScienceTopic;
import forged.expedition.topics.Topic;

/**
 * Created by visitor15 on 9/30/14.
 */
public class KhanAcademyController extends BaseController {

    private final NetworkServiceConnection networkServiceConnection;

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
        // Automatically called from BaseController.
    }

    public void getAllMathTopics(ControllerCallback callback) {
        networkServiceConnection.sendRequestForResponse(KhanAcademy.getTopicUrl(MathTopic.MATH_ID), callback);
    }

    public void getAllMathTopics(final DataCallback callback) {
        networkServiceConnection.sendRequestForResponse(KhanAcademy.getTopicUrl(MathTopic.MATH_ID), new ControllerCallback() {
            @Override
            public void handleCallback(Bundle b) {
                callback.receiveResults(convertJsonToTopic(b.getString(NetworkService.RESPONSE_DATA), MathTopic.class));
            }
        });
    }

    public void getPlaylists(final DataCallback callback) {
        networkServiceConnection.sendRequestForResponse(KhanAcademy.getTopicUrl(MathTopic.MATH_ID), new ControllerCallback() {
            @Override
            public void handleCallback(Bundle b) {
                callback.receiveResults(convertJsonToTopic(b.getString(NetworkService.RESPONSE_DATA), MathTopic.class));
            }
        });
    }

    public void getAllScienceTopics(ControllerCallback callback) {
        networkServiceConnection.sendRequestForResponse(KhanAcademy.getTopicUrl(ScienceTopic.SCIENCE_ID), callback);
    }

    public Topic convertJsonToTopic(String jsonStr, Class clazz) {
        Gson gson = new GsonBuilder().create();

        try {
            JSONObject obj = new JSONObject(jsonStr);


            JSONArray testArray = obj.getJSONArray("children");

            List<MathTopic> topicList = gson.fromJson(testArray.toString(), new TypeToken<List<MathTopic>>() {
            }.getType());

            System.out.println("TOPIC SIZE: " + topicList.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        JsonParser parser = new JsonParser();
//        JsonArray jArray = parser.parse(jsonStr).getAsJsonArray();
//
//        List<MathTopic> mathTopics = new ArrayList<MathTopic>();
//
//        for(JsonElement obj : jArray )
//        {
//            mathTopics.add(gson.fromJson(obj, MathTopic.class));
//        }

//        return (Topic) gson.fromJson(jsonStr, new TypeToken<List<MathTopic>>(){}.getType());
        return null;
    }

    @Override
    public void onHandleGenericCallback(Bundle b) {
        ((ControllerCallback) b.getSerializable("callback")).handleCallback(b);
    }
}
