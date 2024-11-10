package kz.terricon.freedomhackathon.dariusarman.utils;

import jakarta.annotation.PostConstruct;
import kz.terricon.freedomhackathon.dariusarman.entities.Resume;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class Manager {

    private List<Resume> resumes = new ArrayList<>();

    @PostConstruct
    public void initializeResumes() {
        resumes = readFullText("resume");
    }

    @PostMapping("/searchResumes")
    public List<Resume> searchResumes(@RequestBody Resume filter) {
        System.out.println("Received filter request: " + filter);

        List<Resume> filteredResumes = resumes.stream()
                .filter(resume -> (filter.getPhone() == null || resume.getPhone().contains(filter.getPhone())) &&
                        (filter.getEMail() == null || resume.getEMail().contains(filter.getEMail())) &&
                        (filter.getSex() == null || resume.getSex().equalsIgnoreCase(filter.getSex())) &&
                        (filter.getExperience() == null || resume.getExperience().contains(filter.getExperience())))
                .collect(Collectors.toList());

        System.out.println("Found " + filteredResumes.size() + " resumes.");
        return filteredResumes;
    }

    public List<Resume> readFullText(String path) {
        List<Resume> resumeList = new ArrayList<>();
        File folder = new File(path);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".pdf"));

        if (files != null) {
            for (File file : files) {
                try {
                    String fullText = extractTextFromPDF(file);
                    Resume resume = new Resume(fullText);
                    resumeList.add(resume);
                } catch (IOException e) {
                    System.out.println("Ошибка при обработке файла: " + file.getName());
                }
            }
        }
        return resumeList;
    }

    private String extractTextFromPDF(File file) throws IOException {
        try (PDDocument document = PDDocument.load(file)) {
            return new PDFTextStripper().getText(document);
        }
    }

    public List<Resume> findResume(List<Resume> resumes, String sex, String experience) {
        return resumes.stream()
                .filter(resume -> sex.equals(resume.getSex()) && experience.equals(resume.getExperience()))
                .collect(Collectors.toList());
    }

    public List<Resume> findResume(List<Resume> resumes, String something) {
        return resumes.stream()
                .filter(resume -> something.equals(resume.getSex()) || something.equals(resume.getExperience()))
                .collect(Collectors.toList());
    }
}
