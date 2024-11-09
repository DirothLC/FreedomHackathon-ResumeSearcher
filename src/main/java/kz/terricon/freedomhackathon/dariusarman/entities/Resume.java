package kz.terricon.freedomhackathon.dariusarman.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Resume {
    @JsonProperty("text")
    private String text;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("eMail")
    private String eMail;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("experience")
    private String experience;

    // Конструктор
    public Resume(@JsonProperty("text") String text) {
        this.text = text;
    }

    // Геттеры и сеттеры
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "email='" + eMail + '\'' +
                ", phoneNumber='" + phone + '\'' +
                '}';
    }
}
