package com.ffme.fitforme.data;


public class CardsFollowDataProvider {

    public String card_title, card_subtitle;
    public String avatar_image_name_1, avatar_image_name_2, avatar_image_name_3, avatar_image_name_4;
    public String avatar_image_name_1_url, avatar_image_name_2_url, avatar_image_name_3_url, avatar_image_name_4_url;
    private Integer picture1, picture2, picture3, picture4;

    public CardsFollowDataProvider() {
    }

    public CardsFollowDataProvider(String card_title, String card_subtitle, String avatar_image_name_1, String avatar_image_name_2, String avatar_image_name_3, String avatar_image_name_4,
                                   String avatar_image_name_1_url, String avatar_image_name_2_url, String avatar_image_name_3_url, String avatar_image_name_4_url) {
        this.card_title = card_title;
        this.card_subtitle = card_subtitle;
        this.avatar_image_name_1 = avatar_image_name_1;
        this.avatar_image_name_2 = avatar_image_name_2;
        this.avatar_image_name_3 = avatar_image_name_3;
        this.avatar_image_name_4 = avatar_image_name_4;
        this.avatar_image_name_1_url = avatar_image_name_1_url;
        this.avatar_image_name_2_url = avatar_image_name_2_url;
        this.avatar_image_name_3_url = avatar_image_name_3_url;
        this.avatar_image_name_4_url = avatar_image_name_4_url;
    }


    public CardsFollowDataProvider(String name, String about, int picture1, int picture2, int picture3, int picture4) {
        this.card_title = name;
        this.card_subtitle = about;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
        this.picture4 = picture4;
    }

    public String getCard_title() {
        return card_title;
    }

    public void setCard_title(String card_title) {
        this.card_title = card_title;
    }

    public String getCard_subtitle() {
        return card_subtitle;
    }

    public void setCard_subtitle(String card_subtitle) {
        this.card_subtitle = card_subtitle;
    }

    public String getAvatar_image_name_1() {
        return avatar_image_name_1;
    }

    public void setAvatar_image_name_1(String avatar_image_name_1) {
        this.avatar_image_name_1 = avatar_image_name_1;
    }

    public String getAvatar_image_name_2() {
        return avatar_image_name_2;
    }

    public void setAvatar_image_name_2(String avatar_image_name_2) {
        this.avatar_image_name_2 = avatar_image_name_2;
    }

    public String getAvatar_image_name_3() {
        return avatar_image_name_3;
    }

    public void setAvatar_image_name_3(String avatar_image_name_3) {
        this.avatar_image_name_3 = avatar_image_name_3;
    }

    public String getAvatar_image_name_4() {
        return avatar_image_name_4;
    }

    public void setAvatar_image_name_4(String avatar_image_name_4) {
        this.avatar_image_name_4 = avatar_image_name_4;
    }

    public String getAvatar_image_name_1_url() {
        return avatar_image_name_1_url;
    }

    public void setAvatar_image_name_1_url(String avatar_image_name_1_url) {
        this.avatar_image_name_1_url = avatar_image_name_1_url;
    }

    public String getAvatar_image_name_2_url() {
        return avatar_image_name_2_url;
    }

    public void setAvatar_image_name_2_url(String avatar_image_name_2_url) {
        this.avatar_image_name_2_url = avatar_image_name_2_url;
    }

    public String getAvatar_image_name_3_url() {
        return avatar_image_name_3_url;
    }

    public void setAvatar_image_name_3_url(String avatar_image_name_3_url) {
        this.avatar_image_name_3_url = avatar_image_name_3_url;
    }

    public String getAvatar_image_name_4_url() {
        return avatar_image_name_4_url;
    }

    public void setAvatar_image_name_4_url(String avatar_image_name_4_url) {
        this.avatar_image_name_4_url = avatar_image_name_4_url;
    }

    public Integer getPicture1() {
        return picture1;
    }

    public void setPicture1(Integer picture1) {
        this.picture1 = picture1;
    }

    public Integer getPicture2() {
        return picture2;
    }

    public void setPicture2(Integer picture2) {
        this.picture2 = picture2;
    }

    public Integer getPicture3() {
        return picture3;
    }

    public void setPicture3(Integer picture3) {
        this.picture3 = picture3;
    }

    public Integer getPicture4() {
        return picture4;
    }

    public void setPicture4(Integer picture4) {
        this.picture4 = picture4;
    }
}
