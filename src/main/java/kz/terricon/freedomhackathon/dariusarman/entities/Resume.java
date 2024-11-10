package kz.terricon.freedomhackathon.dariusarman.entities;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resume {
    String fullText;
    String name;
String phone;
String eMail;
String sex;
String experience;


    public Resume() {
    }

    public Resume(String fullText) {
        this.fullText = fullText;
        parseFields();
    }

    public Resume(String fullText, String phone, String eMail, String sex, String experience) {
        this.fullText = fullText;
        this.phone = phone;
        this.eMail = eMail;
        this.sex = sex;
        this.experience = experience;

    }

    public Resume(String fullText, String name, String phone, String eMail, String sex, String experience) {
        this.fullText = fullText;
        this.name = name;
        this.phone = phone;
        this.eMail = eMail;
        this.sex = sex;
        this.experience = experience;
    }


    private void parseFields() {
        this.name = extractName(fullText);
        this.phone = extractPhone(fullText);
        this.eMail = extractEmail(fullText);
        this.sex = extractSex(fullText);
        this.experience = extractExperience(fullText);

        System.out.println("---- Отладка парсинга ----");
        System.out.println("Текст: " + fullText);
        System.out.println("Имя: " + name);
        System.out.println("Телефон: " + phone);
        System.out.println("E-mail: " + eMail);
        System.out.println("Пол: " + sex);
        System.out.println("Опыт работы: " + experience);
    }

    private String extractName(String text) {
        Pattern namePattern = Pattern.compile("^Имя: ([А-Яа-яA-Za-z\\s]+)");
        Matcher matcher = namePattern.matcher(text);
        return matcher.find() ? matcher.group(1).trim() : "не указано";
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

    public String getName() {
        return name;
    }

    public String geteMail() {
        return eMail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
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

    public void setFullText(String fullText) {
        this.fullText = fullText;
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

    public String getExperience() {
        return experience;
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
