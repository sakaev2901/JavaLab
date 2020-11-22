package com.example.demo.consumer.app1;

import com.example.demo.consumer.dtos.UserDto;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.font.FontSet;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AcademPdfGenerator {

    @SneakyThrows
    public void generate(UserDto userDto) {
        PdfWriter writer = new PdfWriter("C:\\Users\\LDAR\\Desktop\\LRNG\\3 курс\\JavaLab\\1. PDF CONSUMER\\academ\\A-" + userDto.getFullName().split(" ")[0] +
                UUID.randomUUID() + ".pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        final FontSet set = new FontSet();
        set.addFont("C:\\Windows\\Fonts\\arial.ttf");
        document.setFontProvider(new FontProvider(set));
        document.setProperty(Property.FONT, new String[]{"MyFontFamilyName"});
        Paragraph p = new Paragraph();
        p.add(new Tab());
        p.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        p.add("Зам. директора по воспит части");
        document.add(p);
        Paragraph p2 = new Paragraph();
        p2.add(new Tab());
        p2.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        p2.add("КФУ ВШ ИТИС");
        document.add(p2);
        Paragraph p3 = new Paragraph();
        p3.add(new Tab());
        p3.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        p3.add("Бакировой А.Н.");
        document.add(p3);
        Paragraph p4 = new Paragraph();
        p4.add(new Tab());
        p4.addTabStops(new TabStop(1000, TabAlignment.CENTER));
        p4.add("от " + userDto.getFullName());
        document.add(p4);
        document.add(new Paragraph());
        document.add(new Paragraph());
        document.add(new Paragraph());
        List<TabStop> tabStops = new ArrayList<>();
        tabStops.add(new TabStop(500 / 2, TabAlignment.CENTER));
        tabStops.add(new TabStop(500, TabAlignment.LEFT));
        Paragraph p6 = new Paragraph().addTabStops(tabStops);
        p6
                .add(new Tab())
                .add("Заявление")
                .add(new Tab());
        document.add(p6);
        document.add(new Paragraph());
        document.add(new Paragraph());
        document.add(new Paragraph("Прошу Вас, меня "+userDto.getFullName()+" "+userDto.getBirthday()
                +" с паспортом "+userDto.getId()+" выдачи "+userDto.getIssued() + "(" +
                userDto.getAge() + " лет"+ ")"+" выдать академический отпуск по болезни"));
        document.add(new Paragraph());
        document.add(new Paragraph());
        Paragraph p5 = new Paragraph(userDto.getFullName());
        p5.add(new Tab());
        p5.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
        p5.add(formatForDateNow.format(dateNow));
        document.add(p5);
        document.close();
    }

    public Cell getCell(String text, TextAlignment alignment) {
        Cell cell = new Cell().add(new Paragraph(text));
        cell.setPadding(0);
        cell.setTextAlignment(alignment);
        cell.setBorder(Border.NO_BORDER);
        return cell;
    }

    public void manipulatePdf(String src, String dest) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
        PdfPage page = pdfDoc.getFirstPage();
        PdfDictionary dict = page.getPdfObject();

        PdfObject object = dict.get(PdfName.Contents);
        if (object instanceof PdfStream) {
            PdfStream stream = (PdfStream) object;
            byte[] data = stream.getBytes();
            String replacedData = new String(data);
            System.out.println(replacedData);
            stream.setData(replacedData.getBytes(Charset.defaultCharset()));
        }

        pdfDoc.close();
    }
}
