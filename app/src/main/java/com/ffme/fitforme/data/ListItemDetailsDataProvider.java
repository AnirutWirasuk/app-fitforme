package com.ffme.fitforme.data;



public class ListItemDetailsDataProvider {

    private String item_title, item_subtitle;
    private String span_1, span_1_title, span_2, span_2_title, span_3, span_3_title;
    private String avatar_image_name, avatar_image_url;
    private Integer avatar_image;

    public ListItemDetailsDataProvider() {
    }

    public ListItemDetailsDataProvider(String item_title, String item_subtitle, Integer avatar_image, String span_1, String span_1_title, String span_2, String span_2_title, String span_3, String span_3_title) {
        this.item_title = item_title;
        this.item_subtitle = item_subtitle;
        this.avatar_image = avatar_image;
        this.span_1 = span_1;
        this.span_1_title = span_1_title;
        this.span_2 = span_2;
        this.span_2_title = span_2_title;
        this.span_3 = span_3;
        this.span_3_title = span_3_title;
    }

    public ListItemDetailsDataProvider(String item_title, String item_subtitle, String avatar_image_name, String avatar_image_url, String span_1, String span_1_title, String span_2, String span_2_title, String span_3, String span_3_title) {
        this.item_title = item_title;
        this.item_subtitle = item_subtitle;
        this.avatar_image_name = avatar_image_name;
        this.span_1 = span_1;
        this.span_1_title = span_1_title;
        this.span_2 = span_2;
        this.span_2_title = span_2_title;
        this.span_3 = span_3;
        this.span_3_title = span_3_title;
        this.avatar_image_url = avatar_image_url;
    }

    public String getItem_subtitle() {
        return item_subtitle;
    }

    public String getAvatar_image_url() {
        return avatar_image_url;
    }

    public void setAvatar_image_url(String avatar_image_url) {
        this.avatar_image_url = avatar_image_url;
    }

    public void setItem_subtitle(String item_subtitle) {
        this.item_subtitle = item_subtitle;
    }

    public Integer getAvatar_image() {
        return avatar_image;
    }

    public void setAvatar_image(Integer avatar_image) {
        this.avatar_image = avatar_image;
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

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String name) {
        this.item_title = name;
    }


    public String getAvatar_image_name() {
        return avatar_image_name;
    }

    public void setAvatar_image_name(String avatar_image_name) {
        this.avatar_image_name = avatar_image_name;
    }
}
