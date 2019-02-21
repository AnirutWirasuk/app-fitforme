package com.ffme.fitforme.data;



public class CardsProfileDataProvider {

    public String card_title, card_subtitle;
    public String avatar_image_name, span_1, span_1_title, span_2, span_2_title, span_3, span_3_title;
    public String avatar_image_url;
    private Integer picture;
    private int photos, followers, following;

    public CardsProfileDataProvider() {
    }

    public CardsProfileDataProvider(String card_title, String card_subtitle, String avatar_image_name, String span_1, String span_1_title, String span_2, String span_2_title, String span_3, String span_3_title,
                                    String avatar_image_name_url) {
        this.card_title = card_title;
        this.card_subtitle = card_subtitle;
        this.avatar_image_name = avatar_image_name;
        this.span_1 = span_1;
        this.span_1_title = span_1_title;
        this.span_2 = span_2;
        this.span_2_title = span_2_title;
        this.span_3 = span_3;
        this.span_3_title = span_3_title;
        this.avatar_image_url = avatar_image_name_url;

    }

    public CardsProfileDataProvider(String name, String about, int photos, int followers, int following, int picture) {
        this.card_title = name;
        this.card_subtitle = about;
        this.photos = photos;
        this.followers = followers;
        this.following = following;
        this.picture = picture;
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

    public String getAvatar_image_name() {
        return avatar_image_name;
    }

    public void setAvatar_image_name(String avatar_image_name) {
        this.avatar_image_name = avatar_image_name;
    }

    public String getAvatar_image_url() {
        return avatar_image_url;
    }

    public void setAvatar_image_url(String avatar_image_url) {
        this.avatar_image_url = avatar_image_url;
    }

    public String getSpan_1() {
        return span_1;
    }

    public void setSpan_1(String span_1) {
        this.span_1 = span_1;
    }

    public String getSpan_1_title() {
        return span_1_title;
    }

    public void setSpan_1_title(String span_1_title) {
        this.span_1_title = span_1_title;
    }

    public String getSpan_2() {
        return span_2;
    }

    public void setSpan_2(String span_2) {
        this.span_2 = span_2;
    }

    public String getSpan_2_title() {
        return span_2_title;
    }

    public void setSpan_2_title(String span_2_title) {
        this.span_2_title = span_2_title;
    }

    public String getSpan_3() {
        return span_3;
    }

    public void setSpan_3(String span_3) {
        this.span_3 = span_3;
    }

    public String getSpan_3_title() {
        return span_3_title;
    }

    public void setSpan_3_title(String span_3_title) {
        this.span_3_title = span_3_title;
    }

    public int getPhotos() {
        return photos;
    }

    public void setPhotos(int photos) {
        this.photos = photos;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public Integer getPicture() {
        return picture;
    }

    public void setPicture(Integer picture) {
        this.picture = picture;
    }
}
