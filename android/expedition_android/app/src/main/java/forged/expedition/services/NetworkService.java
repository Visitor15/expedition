package forged.expedition.services;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import forged.expedition.data.MetaChildData;
import forged.expedition.networking.NetworkServiceConnection;
import forged.expedition.networking.http.HttpConnector;
import forged.expedition.topics.GenericTopic;
import forged.expedition.topics.Topic;
import forged.expedition.topics.VideoTopic;

/**
 * Created by visitor15 on 9/22/14.
 */
public class NetworkService extends BasicService {

    public static enum RequestCommand {
        ACCEPT_RETURN_JSON_RESPONSE,
        ACCEPT_PARSE_JSON_RESPONSE,
    }

    public static enum RequestResponse {
        REQUEST_ACK,
        REQUEST_PROCESSING,
        REQUEST_UPDATE,
        REQUEST_FINISHED,
        REQUEST_ERROR,
        REQUEST_UNKNOWN
    }

    public static final int REQUEST_SUCCESS = 200;

    public static final int REQUEST_FAILURE = 403;

    public static final int REQUEST_ERROR = 404;

    public static final int SEND_REQUEST = 0;

    public static final int SEND_COMMAND_REQUEST = 1;

    public static final int REQUEST_FINISHED = 100;

    public static final String REQUEST_DATA = "request_data";

    public static final String REQUEST_TYPE = "request_type";

    public static final String REQUEST_URL = "request_url";

    public static final String REQUEST_ID = "request_id";

    public static final String RESPONSE_DATA = "response_data";

    public static final String COMMAND_DATA = "command_data";

    private HttpConnector httpConnector;

    public NetworkService() {
        super();
        httpConnector = new HttpConnector();
    }

    @Override
    protected void onHandleServiceMessage(Message msg) {
        switch (msg.what) {
            case SEND_REQUEST: {
                handleSendRequestAsync(Message.obtain(msg));
                break;
            }
            case SEND_COMMAND_REQUEST: {
                handleRequestCommand(Message.obtain(msg));
            }
            default: {
                return;
            }
        }
    }

    private void returnFailedRequest(Message msg) {
        Message message = Message.obtain(null, NetworkService.REQUEST_FAILURE);
        msg.getTarget().sendMessage(message);
    }

    @Override
    protected void onHandleWorkerMessage(Message msg) {


        Bundle msgData = msg.getData();
        RequestCommand requestCommand = RequestCommand.valueOf(msgData.getString(COMMAND_DATA));

        switch(requestCommand) {

            case ACCEPT_RETURN_JSON_RESPONSE: {
                acceptAndReturnJsonResponse();
                break;
            }
            case ACCEPT_PARSE_JSON_RESPONSE: {
                acceptParseAndReturnResponse(Message.obtain(msg));
                break;
            }
            default: {
                returnFailedRequest(msg);
            }
        }

//        switch (msg.what) {
//            case SEND_REQUEST: {
//                msg.getData().putString(NetworkService.RESPONSE_DATA, sendRequestForStringResponse(msg.getData()));
//                break;
//            }
//            case REQUEST_FINISHED: {
//                System.out.println("REQUEST FINISHED");
//                Message message = Message.obtain(null, NetworkService.REQUEST_FINISHED);
//                msg.getTarget().sendMessage(message);
//                break;
//            }
//            default: {
//                return;
//            }
//        }



        if(Thread.currentThread() instanceof HandlerThread) {
            ((HandlerThread) Thread.currentThread()).quit();
        }
    }

    private void sendFailedRequest(Message msg, String response) {
        msg.what = NetworkService.REQUEST_FAILURE;
        try {
            msg.replyTo.send(Message.obtain(msg));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        killCurrentWorkerThread();
    }



    private void acceptAndReturnJsonResponse() {

    }

    private void acceptParseAndReturnResponse(Message msg) {
        List<Topic> topicList = new ArrayList<Topic>();
        Gson gson = new GsonBuilder().create();
        try {
            Bundle b = msg.getData();

            String requestUrl = b.getString(NetworkService.REQUEST_URL);
    //        Class requestTypeClass = getClassLoader().loadClass(b.getString(NetworkService.REQUEST_TYPE));



            String errorMsg = "Request failed.";

            if(b == null) {
                sendFailedRequest(Message.obtain(msg), "No bundle found in message.");
            }

            String response = httpConnector.postForResponse(requestUrl);


            JSONObject obj = null;


                obj = new JSONObject(response);


            JSONArray metaDataArray = obj.getJSONArray("child_data");
            JSONArray testArray = obj.getJSONArray("children");

            response = "";

            for(int i = 0; i < metaDataArray.length(); i++) {
                List<MetaChildData> metaChildData = gson.fromJson(metaDataArray.toString(), new TypeToken<List<MetaChildData>>() {
                }.getType());

                if(metaChildData.size() == 1) {
                    if (metaChildData.get(0).getKind().equalsIgnoreCase("video")) {
                        topicList.add((Topic) gson.fromJson(testArray.toString(), new TypeToken<VideoTopic>() {
                        }.getType()));
                    } else {
                        topicList.add((Topic) gson.fromJson(testArray.toString(), new TypeToken<GenericTopic>() {
                        }.getType()));
                    }
                }
                else if (metaChildData.size() > 1) {
                    if (metaChildData.get(0).getKind().equalsIgnoreCase("video")) {
                        topicList.addAll((List<Topic>) gson.fromJson(testArray.toString(), new TypeToken<List<VideoTopic>>() {
                        }.getType()));
                    } else {
                        topicList.addAll((List<Topic>) gson.fromJson(testArray.toString(), new TypeToken<List<GenericTopic>>() {
                        }.getType()));
                    }
                }
            }

            msg.what = NetworkServiceConnection.REQUEST_SUCCESS;
            msg.replyTo.send(Message.obtain(msg));

        } catch (JSONException e) {
            e.printStackTrace();

            sendFailedRequest(Message.obtain(msg), e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            msg.what = NetworkServiceConnection.REQUEST_SUCCESS;
            msg.getData().putString(NetworkService.RESPONSE_DATA, gson.toJson(topicList, new TypeToken<List<GenericTopic>>() {
            }.getType()));

            try {
                msg.replyTo.send(Message.obtain(msg));
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

    public List<Topic> convertJsonToTopic(String jsonStr, Type type) {
        Gson gson = new GsonBuilder().create();
        JSONObject obj = null;
        try {
            obj = new JSONObject(jsonStr);



            JSONArray metaDataArray = obj.getJSONArray("child_data");
            JSONArray testArray = obj.getJSONArray("children");
            List<MetaChildData> metaChildData = gson.fromJson(metaDataArray.toString(), new TypeToken<List<MetaChildData>>() {
            }.getType());

            if(metaChildData.size() > 0) {
                if(metaChildData.get(0).getKind().equalsIgnoreCase("video")) {
                    return gson.fromJson(testArray.toString(), new TypeToken<List<VideoTopic>>() {
                    }.getType());
                }
                else {
                    return gson.fromJson(testArray.toString(), type);
                }
            }


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
        b.putString(NetworkService.RESPONSE_DATA, response);
        return b;
    }

    private String sendRequestForStringResponse(Bundle b) {
        return httpConnector.postForResponse(b.getString(REQUEST_DATA));
    }

    private void handleSendRequest(final Bundle bundle) {
        System.out.println("GOT HANDLE REQUEST COMMAND");
    }

    private void handleSendRequestAsync(final Message msg) {
        Handler mHandler = spawnWorkerThread();
        mHandler.sendMessage(msg);
    }

    private void handleRequestCommand(final Message msg) {
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
