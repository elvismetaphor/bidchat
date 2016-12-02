package edu.slef.bidchat.webservice;


import java.util.List;

import edu.slef.bidchat.model.ChatMessage;
import edu.slef.bidchat.model.Offer;

public interface ChatWebService {

    List<ChatMessage> fetchChatMessages();

    Offer fetchOffer();

    void sendMessage(ChatMessage message);
}
