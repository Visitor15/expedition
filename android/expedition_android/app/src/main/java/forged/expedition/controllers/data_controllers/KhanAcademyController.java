package forged.expedition.controllers.data_controllers;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import forged.expedition.Expedition;
import forged.expedition.KhanAcademy;
import forged.expedition.controllers.BaseController;
import forged.expedition.controllers.ControllerCallback;
import forged.expedition.controllers.DataCallback;
import forged.expedition.data.MetaChildData;
import forged.expedition.networking.NetworkServiceConnection;
import forged.expedition.services.NetworkService;
import forged.expedition.topics.ArtsAndHumanitiesTopic;
import forged.expedition.topics.ComputingTopic;
import forged.expedition.topics.EconomicsAndFinanceTopic;
import forged.expedition.topics.GenericTopic;
import forged.expedition.topics.MathTopic;
import forged.expedition.topics.PartnerContentTopic;
import forged.expedition.topics.ScienceTopic;
import forged.expedition.topics.TalksAndInterviewsTopic;
import forged.expedition.topics.TestPrepTopic;
import forged.expedition.topics.Topic;
import forged.expedition.topics.VideoTopic;

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

    private void sendRequestForAsyncResponse(String id, final Type type, final DataCallback callback) {
        networkServiceConnection.sendRequestForResponse(KhanAcademy.getTopicUrl(id), new ControllerCallback() {
            @Override
            public void handleCallback(Bundle b) {
                callback.receiveResults(convertJsonToTopic(b.getString(NetworkService.RESPONSE_DATA), type));
            }
        });
    }

    private void createRequestWithUrlForAsyncResponse(String url, final Type type, final DataCallback callback) {

        networkServiceConnection.sendRequestCommand(url, type.getClass(), NetworkService.RequestCommand.ACCEPT_PARSE_JSON_RESPONSE, new ControllerCallback() {
            @Override
            public void handleCallback(Bundle b) {
                callback.receiveResults(convertJsonToTopic(b.getString(NetworkService.RESPONSE_DATA), type));
            }
        });

//        networkServiceConnection.sendRequestForResponse(url, new ControllerCallback() {
//            @Override
//            public void handleCallback(Bundle b) {
//                callback.receiveResults(convertJsonToTopic(b.getString(NetworkService.RESPONSE_DATA), type));
//            }
//        });
    }

    private void createTopicTreeUrlRequestAsync(final DataCallback callback) {
//        networkServiceConnection.sendRequestForResponse(KhanAcademy.TOPIC_TREE_URL, new ControllerCallback() {
//            @Override
//            public void handleCallback(Bundle b) {
//                callback.receiveResults(parseTopicTree(b.getString(NetworkService.RESPONSE_DATA)));
//            }
//        });

        networkServiceConnection.sendRequestCommand(KhanAcademy.TOPIC_TREE_URL, GenericTopic.class, NetworkService.RequestCommand.ACCEPT_PARSE_JSON_RESPONSE, new ControllerCallback() {
            @Override
            public void handleCallback(Bundle b) {
                callback.receiveResults(parseTopicTree(b.getString(NetworkService.RESPONSE_DATA)));
            }
        });
    }

    public void getAllMathTopics(final DataCallback callback) {
        sendRequestForAsyncResponse(MathTopic.MATH_ID, new TypeToken<List<MathTopic>>() {
        }.getType(), callback);
    }

    public void getPlaylists(final DataCallback callback) {

        sendRequestForAsyncResponse(MathTopic.MATH_ID, new TypeToken<List<MathTopic>>() {
        }.getType(), callback);

        networkServiceConnection.sendRequestForResponse(KhanAcademy.getTopicUrl(MathTopic.MATH_ID), new ControllerCallback() {
            @Override
            public void handleCallback(Bundle b) {
                callback.receiveResults(convertJsonToTopic(b.getString(NetworkService.RESPONSE_DATA), new TypeToken<List<MathTopic>>() {
                }.getType()));
            }
        });
    }

    public void getNewAndNotewory(final DataCallback callback) {
        sendRequestForAsyncResponse(ScienceTopic.SCIENCE_ID, new TypeToken<List<ScienceTopic>>() {
        }.getType(), callback);
    }

    public void getAllScienceTopics(final DataCallback callback) {
        sendRequestForAsyncResponse(ScienceTopic.SCIENCE_ID, new TypeToken<List<ScienceTopic>>() {
        }.getType(), callback);
    }

    public void getAllEconomicsAndFinanceTopics(final DataCallback callback) {
        sendRequestForAsyncResponse(EconomicsAndFinanceTopic.ECONOMICS_AND_FINANCE_ID, new TypeToken<List<EconomicsAndFinanceTopic>>() {
        }.getType(), callback);
    }

    public void getAllArtsAndHumanitiesTopics(final DataCallback callback) {
        sendRequestForAsyncResponse(ArtsAndHumanitiesTopic.ARTS_AND_HUMANITIES_ID, new TypeToken<List<ArtsAndHumanitiesTopic>>() {
        }.getType(), callback);
    }

    public void getAllComputingTopics(final DataCallback callback) {
        sendRequestForAsyncResponse(ComputingTopic.COMPUTING_TOPIC_ID, new TypeToken<List<ComputingTopic>>() {
        }.getType(), callback);
    }

    public void getAllTestPrepTopics(final DataCallback callback) {
        sendRequestForAsyncResponse(TestPrepTopic.TEST_PREP_TOPIC_ID, new TypeToken<List<TestPrepTopic>>() {
        }.getType(), callback);
    }

    public void getAllPartnerContentTopics(final DataCallback callback) {
        sendRequestForAsyncResponse(PartnerContentTopic.PARTNER_CONTENT_ID, new TypeToken<List<PartnerContentTopic>>() {
        }.getType(), callback);
    }

    public void getAllTalksAndInterviews(final DataCallback callback) {
        sendRequestForAsyncResponse(TalksAndInterviewsTopic.TALKS_AND_INTERVIEWS_TOPIC_ID, new TypeToken<List<TalksAndInterviewsTopic>>() {
        }.getType(), callback);
    }

    public void getAll(final DataCallback callback) {

        createTopicTreeUrlRequestAsync(callback);

//        createRequestWithUrlForAsyncResponse(KhanAcademy.TOPIC_TREE_URL, new TypeToken<List<Topic>>() {
//        }.getType(), callback);
    }

    public void getTopicsByTopicName(String topicName, final DataCallback callback) {
        createRequestWithUrlForAsyncResponse(KhanAcademy.getTopicUrl(convertToFriendlyString(topicName)), new TypeToken<List<GenericTopic>>() {
        }.getType(), callback);
    }

    public void getVideoTopicsForTopic(String topicName, final DataCallback<VideoTopic> callback) {
        createRequestWithUrlForAsyncResponse(KhanAcademy.getVideoTopicUrl(convertToFriendlyString(topicName)), new TypeToken<VideoTopic>() {
        }.getType(), callback);
    }

    public List<Topic> parseTopicTree(String jsonTopicTree) {

        Gson gson = new GsonBuilder().create();
        JSONObject obj = null;

        try {
            obj = new JSONObject(jsonTopicTree);


            return gson.fromJson(jsonTopicTree, new TypeToken<List<GenericTopic>>() {
            }.getType());

//            JSONArray metaData = obj.getJSONArray("child_data");

//            List<MetaChildData> metaChildData = gson.fromJson(metaData.toString(), new TypeToken<List<MetaChildData>>() {
//            }.getType());
//
//            if(metaChildData.size() > 0) {
//
//                for(int i = 0; i < metaChildData.size())
//
//                if(metaChildData.get(0).getKind().equalsIgnoreCase("video")) {
//                    return gson.fromJson(testArray.toString(), new TypeToken<List<VideoTopic>>() {
//                    }.getType());
//                }
//                else if(metaChildData.get(0).getKind().equalsIgnoreCase("topic")) {
//                    return gson.fromJson(testArray.toString(), type);
//                }
//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList<Topic>();
    }

    public List<Topic> convertJsonToTopic(String jsonStr, Type type) {
        Gson gson = new GsonBuilder().create();
        JSONObject obj = null;
        try {
            obj = new JSONObject(jsonStr);

            JSONArray childMetaData = obj.getJSONArray("child_data");
            JSONArray testArray = obj.getJSONArray("children");


            if(childMetaData.length() > 0) {

                List<MetaChildData> metaChildData = gson.fromJson(childMetaData.toString(), new TypeToken<List<MetaChildData>>() {
                }.getType());

                if(metaChildData.get(0).getKind().equalsIgnoreCase("video")) {
                    return gson.fromJson(testArray.toString(), new TypeToken<List<VideoTopic>>() {
                    }.getType());
                }
                else {
                    return gson.fromJson(testArray.toString(), type);
                }
            }


            return gson.fromJson(testArray.toString(), type);

//            JSONArray metaDataArray = obj.getJSONArray("child_data");
//            JSONArray testArray = jsonResults.getJSONArray(jsonResults.);
//            List<MetaChildData> metaChildData = gson.fromJson(metaDataArray.toString(), new TypeToken<List<MetaChildData>>() {
//            }.getType());
//
//            if(metaChildData.size() > 0) {
//                if(metaChildData.get(0).getKind().equalsIgnoreCase("video")) {
//                    return gson.fromJson(testArray.toString(), new TypeToken<List<VideoTopic>>() {
//                    }.getType());
//                }
//                else {
//                    return gson.fromJson(testArray.toString(), type);
//                }
//            }


//            if(((JSONObject) testArray.get(0)).getString("kind").equalsIgnoreCase("Video") &&
//                    ((JSONObject) testArray.get(0)).has("translated_youtube_id")) {
//
//                try {
//
//                    Type t = new TypeToken<List<VideoTopic<GenericTopic>>>() {
//                    }.getType();
//
//                    return gson.fromJson(testArray.toString(), t);
//                } catch(JsonSyntaxException e) {
//                    e.printStackTrace();
//                }
//            }
//            else {
//                System.out.println("GOT ARRAY SIZE: " + testArray.length());
//                Toast.makeText(Expedition.getReference(), "GOT ARRAY SIZE: " + testArray.length(), Toast.LENGTH_LONG).show();
//
//                return gson.fromJson(testArray.toString(), type);
//            }
        } catch (Exception e) {
            e.printStackTrace();

            // No child data present

            try {

                Object resultType = gson.fromJson(obj.toString(), type);
                List resultList = new ArrayList<Object>();
                resultList.add(resultType);
                return resultList;
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
        return new ArrayList<Topic>();
    }

    @Override
    public void onRequestSuccess(Bundle b) {

        Gson gson = new GsonBuilder().create();




        ((ControllerCallback) b.getSerializable("callback")).handleCallback(b);
    }

    @Override
    public void onRequestFailure(Bundle b) {
        Toast.makeText(Expedition.getReference(), "Error requesting service: " + b.getString(NetworkServiceConnection.RESPONSE_DATA, ""), Toast.LENGTH_LONG).show();
    }

    private String convertToFriendlyString(String str) {
        return str.replace(" ", "_").toLowerCase(Locale.getDefault());
    }
}
