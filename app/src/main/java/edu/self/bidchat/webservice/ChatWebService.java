package edu.self.bidchat.webservice;


import java.util.List;

import edu.self.bidchat.model.ChatMessage;
import edu.self.bidchat.model.Offer;

public interface ChatWebService {

    List<ChatMessage> fetchChatMessages();

    Offer fetchOffer();

    void sendMessage(ChatMessage message);
}
