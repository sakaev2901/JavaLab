package com.example.demo.producer;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.*;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        // IO
        File htmlSource = new File("input.html");
        FileInputStream fileInputStream = new FileInputStream(htmlSource);
        Reader reader = new FileReader(htmlSource);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String c;
        StringBuilder sum = new StringBuilder();
        while ((c = bufferedReader.readLine()) != null) {
            System.out.println(c);
            sum.append(c);
        }
        String newS  = new String(sum);
        newS = newS.replace("программист", "пидарас");
        FileWriter fileWriter = new FileWriter(htmlSource);
        fileWriter.write(newS);
        fileWriter.close();
        File htmlSource2 = new File("input.html");

        File pdfDest = new File("output.pdf");
        // pdfHTML specific code
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(new ByteArrayInputStream(newS.getBytes()),
                new FileOutputStream(pdfDest), converterProperties);


        PdfDocument pdfDoc = new PdfDocument(new PdfReader("output.pdf"), new PdfWriter("output-2.pdf"));
        PdfPage page = pdfDoc.getFirstPage();
        PdfDictionary dict = page.getPdfObject();

        PdfObject object = dict.get(PdfName.Contents);
        if (object instanceof PdfStream) {
            PdfStream stream = (PdfStream) object;
            byte[] data = stream.getBytes();
            String replacedData = new String(data).replace("good", "ппп");
            stream.setData(replacedData.getBytes(StandardCharsets.UTF_8));
        }

        pdfDoc.close();
    }
}
