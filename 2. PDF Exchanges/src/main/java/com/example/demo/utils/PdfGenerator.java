package com.example.demo.utils;

import com.example.demo.models.Statement;

public interface PdfGenerator {

    void generatePdfDocs(Statement statement, String key, String language);
}
