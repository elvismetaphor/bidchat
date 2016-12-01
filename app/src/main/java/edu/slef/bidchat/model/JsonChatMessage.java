package edu.slef.bidchat.model;

import com.google.gson.annotations.SerializedName;

public class JsonChatMessage implements ChatMessage {

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("message")
    private String message;

    @SerializedName("type")
    private String type;

    public JsonChatMessage() {}

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
