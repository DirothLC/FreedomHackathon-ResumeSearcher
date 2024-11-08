package kz.terricon.freedomhackathon.dariusarman.entities;

public class Resume {
    String fullText;
String phone;
String eMail;
String sex;
String experience;

    public Resume() {
    }

    public Resume(String fullText) {
        this.fullText = fullText;
    }

    public Resume(String fullText, String phone, String eMail, String sex, String experience) {
        this.fullText = fullText;
        this.phone = phone;
        this.eMail = eMail;
        this.sex = sex;
        this.experience = experience;
    }

    public String getFullText() {
        return fullText;
    }

    public String getPhone() {
        return phone;
    }

    public String getEMail() {
        return eMail;
    }

    public String getSex() {
        return sex;
    }

    public String getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "fullText='" + fullText + '\'' +
                ", phone='" + phone + '\'' +
                ", eMail='" + eMail + '\'' +
                ", sex='" + sex + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
