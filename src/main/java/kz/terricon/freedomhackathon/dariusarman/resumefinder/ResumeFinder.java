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
        SpringApplication.run(ResumeFinder.class, args);
       /* Manager manager=new Manager();
        List<Resume> resumes=manager.readFullText("resume");

        Reporter.printResumeFullText(resumes);*/
    }
}
