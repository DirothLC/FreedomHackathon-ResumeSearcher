package kz.terricon.freedomhackathon.dariusarman.resumefinder;

import kz.terricon.freedomhackathon.dariusarman.entities.Resume;
import kz.terricon.freedomhackathon.dariusarman.entities.ResumeResponse;
import kz.terricon.freedomhackathon.dariusarman.entities.SearchRequest;
import kz.terricon.freedomhackathon.dariusarman.utils.Manager;
import kz.terricon.freedomhackathon.dariusarman.utils.Reporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class ResumeFinder {

    private final Manager manager;

    public ResumeFinder() {
        this.manager = new Manager();
    }

    public static void main(String[] args) {
        SpringApplication.run(ResumeFinder.class, args);
    }

    @PostMapping("/api/search")
    public List<ResumeResponse> searchResumes(@RequestBody SearchRequest request) {
        System.out.println("Received request: " + request);

        List<Resume> resumes = manager.readFullText("resume");
        List<Resume> foundResumes = filterResumes(resumes, request);

        System.out.println("Found resumes: " + foundResumes.size());
        Reporter.printResumeMainInformation(foundResumes, 1);

        return foundResumes.stream()
                .map(ResumeResponse::fromResume)
                .collect(Collectors.toList());
    }

    private List<Resume> filterResumes(List<Resume> resumes, SearchRequest request) {
        return resumes.stream()
                .filter(resume -> matchesCriteria(resume, request))
                .collect(Collectors.toList());
    }

    private boolean matchesCriteria(Resume resume, SearchRequest request) {
        boolean matchesSex = request.getSex() == null || request.getSex().equalsIgnoreCase(resume.getSex());
        boolean matchesExperience = request.getExperience() == null || request.getExperience().equalsIgnoreCase(resume.getExperience());
        boolean matchesLanguageProficiency = request.getLanguageProficiency() == null || request.getLanguageProficiency().equalsIgnoreCase(resume.getLanguageProficiency());

        return matchesSex && matchesExperience && matchesLanguageProficiency;
    }
}
