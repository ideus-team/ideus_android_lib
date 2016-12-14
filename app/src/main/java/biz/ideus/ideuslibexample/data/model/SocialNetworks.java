package biz.ideus.ideuslibexample.data.model;

/**
 * Created by blackmamba on 14.12.16.
 */

public enum SocialNetworks {
    FACEBOOK_NET("facebook"), TWITTER_NET("twitter"), GOOGLE_PLUS_NET("google_plus");

    public String networkName;

    SocialNetworks(String networkName){
        this.networkName = networkName;
    }
}
