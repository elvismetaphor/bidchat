package edu.slef.bidchat.webservice;


import java.util.ArrayList;
import java.util.List;

import edu.slef.bidchat.model.ChatMessage;

public class RestfulChatWebService implements ChatWebService {

    public List<ChatMessage> fetchChatMessages() {
        return new ArrayList<ChatMessage>();
    }

    public void sendMessage(ChatMessage message) {
        // do something.
    }
}
