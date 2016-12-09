package edu.self.bidchat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import edu.self.bidchat.utils.DataUtils;
import edu.self.bidchat.manager.ChatMessageManager;
import edu.self.bidchat.manager.ModelManager;
import edu.self.bidchat.model.ChatMessage;
import edu.self.bidchat.model.ChatMessgeImpl;
import edu.self.bidchat.recycleview.ChatMessageAdapter;

public class ChatActivity extends AppCompatActivity {

    public static final String BUYER_TYPE_STRING = "b";

    private ChatMessageManager mChatMessageManager;
    private RecyclerView mRecycleView;
    private ChatMessageAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mChatMessageManager = ModelManager.getInstance().getChatMessageManager();

        mEditText = (EditText) findViewById(R.id.input_message);
        mRecycleView = (RecyclerView) findViewById(R.id.message_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatMessage chatMessage = new ChatMessgeImpl(
                        mEditText.getText().toString(), DataUtils.convertNowToTimestamp(), BUYER_TYPE_STRING);
                mEditText.getText().clear();
                new SendMessageAsyncTask().execute(chatMessage);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mAdapter == null) {
            new FetchChatMessageAndOfferAsyncTask().execute();
        }
    }

    private class FetchChatMessageAndOfferAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            mChatMessageManager.fetchChatMessages();
            mChatMessageManager.fetchOffer();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mAdapter = new ChatMessageAdapter(ChatActivity.this, mChatMessageManager.getChatMessageList(),
                                mChatMessageManager.getOffer());
            mRecycleView.setAdapter(mAdapter);
        }
    }

    private class SendMessageAsyncTask extends AsyncTask<ChatMessage, Void, Void> {

        @Override
        protected Void doInBackground(ChatMessage... chatMessages) {
            for (ChatMessage message : chatMessages) {
                mChatMessageManager.sendMessage(message);
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mAdapter.notifyItemInserted(mAdapter.getItemCount());
            mRecycleView.smoothScrollToPosition(mAdapter.getItemCount());
        }
    }
}
