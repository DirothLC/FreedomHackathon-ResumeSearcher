package kz.terricon.freedomhackathon.dariusarman.utils;

import jakarta.annotation.PostConstruct;
import kz.terricon.freedomhackathon.dariusarman.entities.Resume;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "192.168.0.110:8080")
public class Manager {

    private List<Resume> resumes = new ArrayList<>();
    private final OkHttpClient httpClient = new OkHttpClient();

    @PostConstruct
    public void initializeResumes() {
        this.resumes = loadResumesFromDirectory("resume");
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    private List<Resume> loadResumesFromDirectory(String path) {
        List<Resume> resumes = new ArrayList<>();
        File folder = new File(path);
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

    @GetMapping("/api/resumes")
    public List<Resume> getFilteredResumes(@RequestParam(required = false) String phone,
                                           @RequestParam(required = false) String eMail,
                                           @RequestParam(required = false) String sex,
                                           @RequestParam(required = false) String experience) {
        return resumes.stream()
                .filter(resume ->
                        (Optional.ofNullable(phone).map(p -> resume.getPhone() != null && resume.getPhone().contains(p)).orElse(true)) &&
                                (Optional.ofNullable(eMail).map(e -> resume.getEMail() != null && resume.getEMail().contains(e)).orElse(true)) &&
                                (Optional.ofNullable(sex).map(s -> resume.getSex() != null && resume.getSex().equalsIgnoreCase(s)).orElse(true)) &&
                                (Optional.ofNullable(experience).map(exp -> resume.getExperience() != null && resume.getExperience().contains(exp)).orElse(true))
                )
                .collect(Collectors.toList());
    }

    private String searchByKeywords(String keywords) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String jsonBody = "{\"query\":\"" + keywords + "\"}";

        RequestBody body = RequestBody.create(jsonBody, JSON);

        Request request = new Request.Builder()
                .url("https://api.neuralnetwork.com/search")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Неудачный ответ сервера: " + response);
            }
            return response.body().string();
        }
    }

    @PostMapping("/api/resumes")
    public List<Resume> getFilteredResumesPost(ResumeFilter filter) {
        List<Resume> filteredResumes = resumes.stream()
                .filter(resume -> (filter.getPhone() == null || resume.getPhone().contains(filter.getPhone())) &&
                        (filter.getEMail() == null || resume.getEMail().contains(filter.getEMail())) &&
                        (filter.getSex() == null || resume.getSex().equalsIgnoreCase(filter.getSex())) &&
                        (filter.getExperience() == null || resume.getExperience().contains(filter.getExperience())))
                .collect(Collectors.toList());

        // Фильтрация по ключевым словам
        if (filter.getKeywords() != null && !filter.getKeywords().isEmpty()) {
            try {
                String response = searchByKeywords(filter.getKeywords());
                List<String> keywordList = parseKeywordsFromResponse(response);
                filteredResumes = filteredResumes.stream()
                        .filter(resume -> keywordList.stream().anyMatch(keyword -> resume.getText().contains(keyword)))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                System.out.println("Ошибка при поиске по ключевым словам: " + e.getMessage());
            }
        }

        return filteredResumes;
    }

    // Метод для парсинга ответа от API, который предполагает, что в ответе находятся ключевые слова
    private List<String> parseKeywordsFromResponse(String response) {
        // Предположим, что API возвращает список ключевых слов в формате JSON
        // Здесь вам нужно будет преобразовать ответ в список слов
        // Например, если это строка, вы можете разбить ее по пробелам или использовать JSON-библиотеку для парсинга.

        // Простой пример для строки
        return List.of(response.split(","));
    }
}
