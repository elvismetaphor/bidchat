package edu.slef.bidchat.recycleview;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.slef.bidchat.R;
import edu.slef.bidchat.model.ChatMessage;
import edu.slef.bidchat.model.Offer;

public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public class SellerViewHolder extends RecyclerView.ViewHolder {
        public ImageView portrait;
        public TextView name;
        public TextView message;
        public TextView timestamp;

        public SellerViewHolder(View view) {
            super(view);

            portrait = (ImageView) view.findViewById(R.id.seller_portrait);
            name = (TextView) view.findViewById(R.id.seller_name);
            message = (TextView) view.findViewById(R.id.seller_message);
            timestamp = (TextView) view.findViewById(R.id.seller_timestamp);
        }
    }

    public class BuyerViewHolder extends RecyclerView.ViewHolder {
        public ImageView portrait;
        public TextView name;
        public TextView message;
        public TextView timestamp;

        public BuyerViewHolder(View view) {
            super(view);

            portrait = (ImageView) view.findViewById(R.id.buyer_portrait);
            name = (TextView) view.findViewById(R.id.buyer_name);
            message = (TextView) view.findViewById(R.id.buyer_message);
            timestamp = (TextView) view.findViewById(R.id.buyer_timestamp);
        }
    }

    private static final int SELLER_TYPE = 0;
    private static final int BUYER_TYPE = 1;
    private static final String SELLER_TYPE_STRING = "s";

    private Context mContext;
    private List<ChatMessage> mChatMessages;
    private Offer mOffer;


    public ChatMessageAdapter(Context context, List<ChatMessage> chatMessages, Offer offer) {
        mContext = context;
        mChatMessages = chatMessages;
        mOffer = offer;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case BUYER_TYPE:
                View buyerView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.buyer_message, parent, false);
                viewHolder = new BuyerViewHolder(buyerView);
                break;
            case SELLER_TYPE:
                View sellerView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.seller_message, parent, false);
                viewHolder = new SellerViewHolder(sellerView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BUYER_TYPE:
                updateBuyerViewHolder(holder, position);
                break;
            case SELLER_TYPE:
                updateSellerViewHolder(holder, position);
                break;
        }
    }

    private void updateBuyerViewHolder(RecyclerView.ViewHolder holder, int position) {
        BuyerViewHolder buyer = (BuyerViewHolder) holder;
        Picasso.with(mContext).load(mOffer.getBuyerImageUrl()).into(buyer.portrait);
        buyer.name.setText(mOffer.getBuyerName());
        buyer.message.setText(mChatMessages.get(position).getMessage());
        buyer.timestamp.setText(mChatMessages.get(position).getTimestamp());
    }

    private void updateSellerViewHolder(RecyclerView.ViewHolder holder, int position) {
        SellerViewHolder seller = (SellerViewHolder) holder;
        Picasso.with(mContext).load(mOffer.getSellerImageUrl()).into(seller.portrait);
        seller.name.setText(mOffer.getSellerName());
        seller.message.setText(mChatMessages.get(position).getMessage());
        seller.timestamp.setText(mChatMessages.get(position).getTimestamp());
    }

    @Override
    public int getItemCount() {
        return mChatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mChatMessages.get(position).
                getType().equals(SELLER_TYPE_STRING) ? SELLER_TYPE : BUYER_TYPE;
    }
}
