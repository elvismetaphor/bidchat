package edu.slef.bidchat.model;

import edu.slef.bidchat.model.ChatMessage;


public class ChatMessgeImpl implements ChatMessage {

    private String message;
    private String timestamp;
    private String type;

    public ChatMessgeImpl(String message, String timestamp, String type) {
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String getType() {
        return type;
    }
}
