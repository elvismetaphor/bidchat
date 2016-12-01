package edu.slef.bidchat.webservice;


import java.util.List;

import edu.slef.bidchat.model.ChatMessage;

public interface ChatWebService {

    List<ChatMessage> fetchChatMessages();

    void sendMessage(ChatMessage message);
}
