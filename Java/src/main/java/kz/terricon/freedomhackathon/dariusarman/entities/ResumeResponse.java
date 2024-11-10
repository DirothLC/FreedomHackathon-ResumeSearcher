package kz.terricon.freedomhackathon.dariusarman.entities;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResumeResponse {
    private String fullText;
    private String phone;
    private String email;
    private String sex;
    private String experience;
    private String languageProficiency;

    public static ResumeResponse fromResume(Resume resume) {
        ResumeResponse response = new ResumeResponse();

        response.setFullText(resume.getFullText() != null && !resume.getFullText().isEmpty() ? resume.getFullText() : "Не указан");
        response.setPhone(resume.getPhone() != null && !resume.getPhone().isEmpty() ? resume.getPhone() : "Не указан");
        response.setEmail(resume.getEMail() != null && !resume.getEMail().isEmpty() ? resume.getEMail() : "Не указан");
        response.setSex(resume.getSex() != null && !resume.getSex().isEmpty() ? resume.getSex() : "Не указан");
        response.setExperience(resume.getExperience() != null && !resume.getExperience().isEmpty() ? resume.getExperience() : "Не указан");
        response.setLanguageProficiency(resume.getLanguageProficiency() != null && !resume.getLanguageProficiency().isEmpty() ? resume.getLanguageProficiency() : "Не указан");

        return response;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getLanguageProficiency() {
        return languageProficiency;
    }

    public void setLanguageProficiency(String languageProficiency) {
        this.languageProficiency = languageProficiency;
    }

    public String toJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
