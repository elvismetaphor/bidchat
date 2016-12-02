package edu.slef.bidchat.model;

import com.google.gson.annotations.SerializedName;

public class JsonChatMessage implements ChatMessage {

    @SerializedName("message")
    private String message;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("type")
    private String type;

    public JsonChatMessage() {}

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }
}
