package edu.self.bidchat.manager;

import edu.self.bidchat.webservice.ChatWebService;
import edu.self.bidchat.webservice.RestfulChatWebService;

public class ModelManager {

    private static ModelManager instance= null;
    private static Object mutex= new Object();

    private ChatMessageManager mChatMessageManager;
    private ChatWebService mChatWebService;

    private ModelManager() {
        mChatWebService = new RestfulChatWebService();
        mChatMessageManager = new ChatMessageManager(mChatWebService);
    }

    public static ModelManager getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if(instance == null) {
                    instance = new ModelManager();
                }
            }
        }

        return instance;
    }

    public ChatMessageManager getChatMessageManager() {
        return mChatMessageManager;
    }

    public ChatWebService getChatWebService() {
        return mChatWebService;
    }

}
