package kz.terricon.freedomhackathon.dariusarman.utils;

import kz.terricon.freedomhackathon.dariusarman.entities.Resume;

import java.util.List;

public class Reporter {
    public static void printResumeFullText(List<Resume> resumes) {
        for (Resume resume : resumes) {
            System.out.println("Резюме: " + resume.getFullText());
        }
    }

    public static void printResume(List<Resume> r) {
        for (Resume resume : r) {
            System.out.println("---- Резюме ----");
            System.out.println("Полный текст: " + resume.getFullText());
            System.out.println("Имя: " + resume.getName());
            System.out.println("Телефон: " + resume.getPhone());
            System.out.println("E-mail: " + resume.getEMail());
            System.out.println("Пол: " + resume.getSex());
            System.out.println("Опыт работы: " + resume.getExperience());
            System.out.println();
        }
    }

    public static void printResumeMainInformation(List<Resume> r, int something) {
        for (Resume resume : r) {
            System.out.println("---- Подходящее резюме ----");
            System.out.println("Имя: " + resume.getName());
            System.out.println("Телефон: " + resume.getPhone());
            System.out.println("E-mail: " + resume.getEMail());
        }
    }
}
