package com.fake.lukee.test.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profile")
public class Profile extends Persistent {

    private String gamerTag;

    private String nickname;

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Profile(String gamerTag, String nickname) {
        this.gamerTag = gamerTag;
        this.nickname = nickname;
    }
}