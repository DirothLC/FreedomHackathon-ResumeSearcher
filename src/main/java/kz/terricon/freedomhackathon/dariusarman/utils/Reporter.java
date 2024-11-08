package kz.terricon.freedomhackathon.dariusarman.utils;

import kz.terricon.freedomhackathon.dariusarman.entities.Resume;

import java.util.List;

public class Reporter {
    public static void printResumeFullText(List<Resume>resumes){
        for (Resume resume : resumes) {
            System.out.println("Резюме: " + resume.getFullText());
        }
    }
}
