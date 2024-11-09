package kz.terricon.freedomhackathon.dariusarman.utils;

public class ResumeFilter {
    private String phone;
    private String eMail;
    private String sex;
    private String experience;
    private String keywords;
    public ResumeFilter(String phone, String eMail, String sex, String experience, String keywords) {
        this.phone = phone;
        this.eMail = eMail;
        this.sex = sex;
        this.experience = experience;
        this.keywords = keywords;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}