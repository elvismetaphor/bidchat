package edu.self.bidchat.model;


import com.google.gson.annotations.SerializedName;

public class JsonOffer implements Offer {

    @SerializedName("buyer_name")
    private String buyerName;

    @SerializedName("buyer_image_url")
    private String buyerImageUrl;

    @SerializedName("seller_name")
    private String sellerName;

    @SerializedName("seller_image_url")
    private String sellerImageUrl;

    public JsonOffer() {}

    @Override
    public String getBuyerName() {
        return buyerName;
    }

    @Override
    public String getBuyerImageUrl() {
        return buyerImageUrl;
    }

    @Override
    public String getSellerName() {
        return sellerName;
    }

    @Override
    public String getSellerImageUrl() {
        return sellerImageUrl;
    }
}
