package edu.slef.bidchat.manager;


import java.util.ArrayList;
import java.util.List;

import edu.slef.bidchat.model.ChatMessage;
import edu.slef.bidchat.model.Offer;
import edu.slef.bidchat.webservice.ChatWebService;

public class ChatMessageManager {

    private ChatWebService mWebService;
    private List<ChatMessage> mChatMessages;
    private Offer mOffer;

    public ChatMessageManager(ChatWebService webService) {
        mWebService = webService;
        mChatMessages = new ArrayList<>();
    }

    public List<ChatMessage> getChatMessageList() {
        return mChatMessages;
    }

    public Offer getOffer() {
        return mOffer;
    }

    public void fetchChatMessages() {
        mChatMessages.addAll(mWebService.fetchChatMessages());
    }

    public void fetchOffer() {
        mOffer = mWebService.fetchOffer();
    }

    public void sendMessage(ChatMessage message) {
        mChatMessages.add(message);
        mWebService.sendMessage(message);
    }

}
