package edu.slef.bidchat.webservice;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.slef.bidchat.Utils.StreamUtils;
import edu.slef.bidchat.model.ChatMessage;
import edu.slef.bidchat.model.JsonChatMessage;
import edu.slef.bidchat.model.JsonOffer;
import edu.slef.bidchat.model.Offer;

import static android.content.ContentValues.TAG;

public class RestfulChatWebService implements ChatWebService {

    private static final String TAG = RestfulChatWebService.class.getCanonicalName();

    private static final String ENDPOINT = "https://s3.amazonaws.com/carousell/static/android/chat";
    private static final String CHAT_MESSAGES_KEY = "chats";
    public static final String OFFER_KEY = "offer";

    private List<ChatMessage> mChatMessages;
    private Offer mOffer;

    public RestfulChatWebService() {
        mChatMessages = new ArrayList<>();
    }

    @Override
    public List<ChatMessage> fetchChatMessages() {
        try {
            JSONObject jsonObject = fetchChatJson();
            JSONArray resultArray = jsonObject.getJSONArray(CHAT_MESSAGES_KEY);
            Gson gson = new Gson();
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject messageObject = resultArray.getJSONObject(i);
                ChatMessage chatMessage = gson.fromJson(messageObject.toString(), JsonChatMessage.class);
                mChatMessages.add(chatMessage);
            }
        } catch (JSONException e) {
            Log.e(TAG, "fail to parse json string");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mChatMessages;
    }

    @Override
    public Offer fetchOffer() {
        try {
            JSONObject jsonObject = fetchChatJson();
            JSONObject jsonOffer = jsonObject.getJSONObject(OFFER_KEY);
            Gson gson = new Gson();
            mOffer = gson.fromJson(jsonOffer.toString(), JsonOffer.class);
        } catch (JSONException e) {
            Log.e(TAG, "fail to parse json string");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mOffer;
    }

    @NonNull
    private JSONObject fetchChatJson() throws IOException, JSONException {
        URL url = new URL(ENDPOINT);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String readStream = StreamUtils.readStream(con.getInputStream());

        return new JSONObject(readStream);
    }

    @Override
    public void sendMessage(ChatMessage message) {
        // do something.
    }
}
