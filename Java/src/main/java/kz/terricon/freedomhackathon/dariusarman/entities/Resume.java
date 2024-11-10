package kz.terricon.freedomhackathon.dariusarman.entities;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resume {
    private String fullText;
    private String name;
    private String phone;
    private String eMail;
    private String sex;
    private String experience;
    private String languageProficiency;

    public Resume() {}

    public Resume(String fullText) {
        this.fullText = fullText;
        parseFields();
    }

    private void parseFields() {
        this.name = extractName(fullText);
        this.phone = extractPhone(fullText);
        this.eMail = extractEmail(fullText);
        this.sex = extractSex(fullText);
        this.experience = extractExperience(fullText);
        this.languageProficiency = extractLanguageProficiency(fullText);

        System.out.println("---- Отладка парсинга ----");
        System.out.println("Текст: " + fullText);
        System.out.println("Имя: " + name);
        System.out.println("Телефон: " + phone);
        System.out.println("E-mail: " + eMail);
        System.out.println("Пол: " + sex);
        System.out.println("Опыт работы: " + experience);
        System.out.println("Уровень владения языком: " + languageProficiency);
    }

    private String extractName(String text) {
        Pattern namePattern = Pattern.compile("^Имя: ([А-Яа-яA-Za-z\\s]+)");
        Matcher matcher = namePattern.matcher(text);
        return matcher.find() ? matcher.group(1).trim() : "не указан";
    }

    private String extractPhone(String text) {
        Pattern phonePattern = Pattern.compile("(\\+7|8)(\\d{10})");
        Matcher matcher = phonePattern.matcher(text);
        return matcher.find() ? matcher.group() : "не указан";
    }

    private String extractEmail(String text) {
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}");
        Matcher matcher = emailPattern.matcher(text);
        return matcher.find() ? matcher.group() : "не указан";
    }

    private String extractSex(String text) {
        Pattern sexPattern = Pattern.compile("Пол: (Мужской|Женский)");
        Matcher matcher = sexPattern.matcher(text);
        return matcher.find() ? matcher.group(1) : "не указан";
    }

    private String extractExperience(String text) {
        Pattern experiencePattern = Pattern.compile("Опыт работы: (\\d+) год(а|ов)?(?: (\\d+) месяц(а|ев)?)?");
        Matcher matcher = experiencePattern.matcher(text);
        if (matcher.find()) {
            String years = matcher.group(1) != null ? matcher.group(1) + " лет" : "";
            String months = matcher.group(3) != null ? " " + matcher.group(3) + " месяцев" : "";
            return years + months;
        }
        return "не указан";
    }

    private String extractLanguageProficiency(String text) {
        Pattern languagePattern = Pattern.compile("Уровень владения языком: (\\w+)");
        Matcher matcher = languagePattern.matcher(text);
        return matcher.find() ? matcher.group(1) : "не указан";
    }

    public String getName() {
        return name;
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

    public String getLanguageProficiency() {
        return languageProficiency;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setLanguageProficiency(String languageProficiency) {
        this.languageProficiency = languageProficiency;
    }

    public String getFullText() {
        return fullText;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "fullText='" + fullText + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", eMail='" + eMail + '\'' +
                ", sex='" + sex + '\'' +
                ", experience='" + experience + '\'' +
                ", languageProficiency='" + languageProficiency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(fullText, resume.fullText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullText);
    }
}
