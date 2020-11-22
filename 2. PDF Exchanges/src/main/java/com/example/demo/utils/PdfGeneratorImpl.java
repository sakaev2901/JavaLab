package com.example.demo.utils;

import com.example.demo.models.Statement;
import com.example.demo.models.User;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.UUID;

@Component
public class PdfGeneratorImpl implements PdfGenerator {

    private Statement statement;
    private String key;
    private String language;

    @Override
    public void generatePdfDocs(Statement statement, String key, String language) {
        this.statement = statement;
        this.key = key;
        this.language = language;
        try {
          tryToWriteFile();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    private void tryToWriteFile() throws IOException {
        String templateFileName = getTemplateFileName(key);
        String readyTemplateString = setUserFields(statement.getUser(), getStringifyPageTemplate(templateFileName, language));
        String newDir = generateDirName();
        new File("generated/" + newDir).mkdirs();
        String pdfPath = "generated/" + newDir + "/" + UUID.randomUUID() + ".pdf";
        File file = new File(pdfPath);
        file.createNewFile();
        byte[] bytes = readyTemplateString.getBytes();
        HtmlConverter.convertToPdf(new ByteArrayInputStream(bytes), new FileOutputStream(file), new ConverterProperties());
    }


    private String getStringifyPageTemplate(String templateName, String language) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("templates/" + language + "/" + templateName))) {
            String line;
            StringBuilder pageContent = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                pageContent.append(line);
            }
            return pageContent.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String generateDirName() {
        return statement.getDate().toString()
                .replaceAll(" ", "-")
                .replaceAll(":", "-")
                + "-"
                + statement.getId();
    }

    private String getTemplateFileName(String key) {
        String[] parts = key.split("\\.");
        return parts[parts.length - 1] + ".html";
    }


    private String setUserFields(User user, String template) {
        return template
                .replace("${name}", user.getName())
                .replace("${surname}", user.getSurname())
                .replace("${patronymic}", user.getPatronymic())
                .replace("${birthday}", user.getBirthday())
                .replace("${passwordId}", user.getPassportId())
                ;
    }


}
