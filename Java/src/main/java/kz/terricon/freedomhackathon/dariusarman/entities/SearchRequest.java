package kz.terricon.freedomhackathon.dariusarman.entities;

public class SearchRequest {
    private String sex;
    private String experience;
    private String languageProficiency;

    public String getSex() {
        return sex != null ? sex : "не указан";
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getExperience() {
        return experience != null ? experience : "не указан";
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLanguageProficiency() {
        return languageProficiency != null ? languageProficiency : "не указан";
    }

    public void setLanguageProficiency(String languageProficiency) {
        this.languageProficiency = languageProficiency;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "sex='" + sex + '\'' +
                ", experience='" + experience + '\'' +
                ", languageProficiency='" + languageProficiency + '\'' +
                '}';
    }
}