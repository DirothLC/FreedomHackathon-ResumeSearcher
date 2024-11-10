package kz.terricon.freedomhackathon.dariusarman.utils;

import jakarta.annotation.PostConstruct;
import kz.terricon.freedomhackathon.dariusarman.entities.Resume;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class Manager {

    private List<Resume> resumes = new ArrayList<>();

    @PostConstruct
    public void initializeResumes() {
        resumes = readFullText("resume");
    }
    @GetMapping("/api/resumes")
public List<Resume> readFullText(@RequestParam String path){

    List<Resume> resumes=new ArrayList<>();
    File folder=new File(path);
    File[] files = folder.listFiles((dir, name) -> name.endsWith(".pdf"));

    if (files != null) {
        for (File file : files) {
            try {
                String fullText = extractTextFromPDF(file);
                Resume resume = new Resume(fullText);
                resumes.add(resume);
            } catch (IOException e) {
                System.out.println("Ошибка при обработке файла: " + file.getName());
            }
        }
    }

    return resumes;
}
    @GetMapping("/api/resumes")
    public List<Resume> getResumes(@RequestParam(required = false) String phone,
                                   @RequestParam(required = false) String eMail,
                                   @RequestParam(required = false) String sex,
                                   @RequestParam(required = false) String experience) {
        return resumes.stream()
                .filter(resume -> (phone == null || resume.getPhone().contains(phone)) &&
                        (eMail == null || resume.getEMail().contains(eMail)) &&
                        (sex == null || resume.getSex().equalsIgnoreCase(sex)) &&
                        (experience == null || resume.getExperience().contains(experience)))
                .collect(Collectors.toList());
    }
    private String extractTextFromPDF(File file) throws IOException {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }
    public List<Resume> findResume(List<Resume> resumes,String sex, String experience){
        List<Resume>correctResumes=new ArrayList<>();
        for(Resume resume:resumes){
            if(sex.equals(resume.getSex())&&experience.equals(resume.getExperience())){correctResumes.add(resume);}
        }
        return correctResumes;
    }
    public List<Resume> findResume(List<Resume> resumes,String something){
        List<Resume>correctResumes=new ArrayList<>();
        for(Resume resume:resumes){
            if(something.equals(resume.getSex())||something.equals(resume.getExperience())){correctResumes.add(resume);}
        }
        return correctResumes;
    }

}
