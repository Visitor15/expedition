package forged.expedition.controllers.data_controllers;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
                callback.receiveResults(convertJsonToTopic(b.getString(NetworkService.RESPONSE_DATA), new TypeToken<List<MathTopic>>() {
                }.getType()));
            }
        });
    }

    public void getPlaylists(final DataCallback callback) {
        networkServiceConnection.sendRequestForResponse(KhanAcademy.getTopicUrl(MathTopic.MATH_ID), new ControllerCallback() {
            @Override
            public void handleCallback(Bundle b) {
                callback.receiveResults(convertJsonToTopic(b.getString(NetworkService.RESPONSE_DATA), new TypeToken<List<MathTopic>>() {
                }.getType()));
            }
        });
    }

    public void getAllScienceTopics(ControllerCallback callback) {
        networkServiceConnection.sendRequestForResponse(KhanAcademy.getTopicUrl(ScienceTopic.SCIENCE_ID), callback);
    }

    public List<Topic> convertJsonToTopic(String jsonStr, Type type) {

        try {
            Gson gson = new GsonBuilder().create();
            JSONObject obj = new JSONObject(jsonStr);

            JSONArray testArray = obj.getJSONArray("children");

            return gson.fromJson(testArray.toString(), type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<Topic>();
    }

    @Override
    public void onHandleGenericCallback(Bundle b) {
        ((ControllerCallback) b.getSerializable("callback")).handleCallback(b);
    }
}
