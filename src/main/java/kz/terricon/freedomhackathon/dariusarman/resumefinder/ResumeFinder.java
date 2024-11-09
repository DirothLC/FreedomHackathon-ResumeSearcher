package kz.terricon.freedomhackathon.dariusarman.resumefinder;

import kz.terricon.freedomhackathon.dariusarman.entities.Resume;
import kz.terricon.freedomhackathon.dariusarman.utils.Manager;
import kz.terricon.freedomhackathon.dariusarman.utils.Reporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
@SpringBootApplication
public class ResumeFinder {

    public static void main(String[] args){

        //SpringApplication.run(ResumeFinder.class, args);
       Manager manager=new Manager();
        List<Resume> resumes=manager.readFullText("resume");

        //Reporter.printResumeFullText(resumes);
        for (Resume resume : resumes) {
            System.out.println("---- Резюме ----");
            System.out.println("Полный текст: " + resume.getFullText());
            System.out.println("Телефон: " + resume.getPhone());
            System.out.println("E-mail: " + resume.getEMail());
            System.out.println("Пол: " + resume.getSex());
            System.out.println("Опыт работы: " + resume.getExperience());
            System.out.println();
        }
    }
}
