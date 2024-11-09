package kz.terricon.freedomhackathon.dariusarman.utils;

import kz.terricon.freedomhackathon.dariusarman.entities.Resume;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class Manager {
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
    private String extractTextFromPDF(File file) throws IOException {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }
}
