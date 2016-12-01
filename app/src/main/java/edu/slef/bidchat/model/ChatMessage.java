package edu.slef.bidchat.model;


public interface ChatMessage {
    String getTimestamp();

    String getMessage();

    String getType();
}
