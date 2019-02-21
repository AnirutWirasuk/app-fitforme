package com.ffme.fitforme.data;


public class CardsRateDataProvider {

    public String card_title, card_subtitle, rate_image, avatar_image_name, avatar_image_url, rate_image_url;
    public String rate;
    public Integer picture;


    public CardsRateDataProvider() {
    }

    public CardsRateDataProvider(String card_title, String card_body_text, String rate, final String avatar_image_name, String rate_image, String avatar_image_url, String rate_image_url) {

        this.card_title = card_title;
        this.card_subtitle = card_body_text;
        this.rate = rate;
        this.rate_image = rate_image;
        this.avatar_image_name = avatar_image_name;
        this.avatar_image_url = avatar_image_url;
        this.rate_image_url = rate_image_url;


    }

    public CardsRateDataProvider(String card_title, String card_body_text, String rate, int avatar_image_resource) {
        this.card_title = card_title;
        this.card_subtitle = card_body_text;
        this.rate = rate;

        this.picture = avatar_image_resource;


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

    public void setCard_subtitle(String card_body_text) {
        this.card_subtitle = card_body_text;
    }

    public String getRate_image() {
        return rate_image;
    }

    public void setRate_image(String rate_image) {
        this.rate_image = rate_image;
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

    public String getRate_image_url() {
        return rate_image_url;
    }

    public void setRate_image_url(String rate_image_url) {
        this.rate_image_url = rate_image_url;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getPicture() {
        return picture;
    }

    public void setPicture(Integer picture) {
        this.picture = picture;
    }
}
