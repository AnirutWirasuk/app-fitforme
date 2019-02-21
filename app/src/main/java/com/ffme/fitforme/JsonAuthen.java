package com.ffme.fitforme;

public class JsonAuthen {
    private Integer user_id;
    private String user_img;
    private String user_fname;
    private String user_lname;
    private String user_tel;
    private String user_email;
    private Integer dis_id;

    public Integer getId() {
        return user_id;
    }

    public void setId(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getDis() {
        return dis_id;
    }

    public void setDis(Integer dis_id) {
        this.dis_id = dis_id;
    }

    public String getImg() {
        return user_img;
    }

    public void setImg(String user_img) {
        this.user_img = user_img;
    }

    public String getFname() {
        return user_fname;
    }

    public void setFname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getLname() {
        return user_lname;
    }

    public void setLname(String user_lname) {
        this.user_lname = user_lname;
    }

    public String getTel() {
        return user_tel;
    }

    public void setTel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getEmail() {
        return user_email;
    }

    public void setEmail(String user_email) {
        this.user_email = user_email;
    }
}
