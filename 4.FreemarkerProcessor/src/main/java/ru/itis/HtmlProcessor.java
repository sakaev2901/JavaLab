package ru.itis;

import com.google.auto.service.AutoService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.itis.HtmlField", "ru.itis.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    private final static String templatePath = "C:\\Users\\LDAR\\Desktop\\LRNG\\3_курс\\JavaLab\\4.FreemarkerProcessor\\templates";
    private final static String templateFileName = "form.ftlh";

    private final String outPath;
    private final Configuration freemarkerConfiguration;

    public HtmlProcessor() {
        outPath = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1);
        freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_30);
        try {
            freemarkerConfiguration.setDirectoryForTemplateLoading(new File(templatePath));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        annotatedElements.forEach(element -> {
            generateHtml(getElementAnnotationValues(element), element.getSimpleName().toString());
        });
        return true;
    }

    public Map<String, Object> getElementAnnotationValues(Element element) {
        Map<String, Object> params = new HashMap<>();
        HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);
        params.put("method", htmlForm.method());
        params.put("action", htmlForm.action());
        params.put("inputs", getElementFieldsAnnotationValues(element));
        return params;
    }

    public List<InputDto> getElementFieldsAnnotationValues(Element element) {
        List<? extends Element> elements = element.getEnclosedElements();
        List<InputDto> inputs = new ArrayList<>();
        elements.forEach(fieldElement -> {
            if (fieldElement.getKind().isField()) {
                inputs.add(getElementsInputDto(fieldElement));
            }
        });
        return inputs;
    }

    public InputDto getElementsInputDto(Element element) {
            HtmlField htmlField = element.getAnnotation(HtmlField.class);
            return new InputDto(
                    htmlField.placeholder(),
                    htmlField.name(),
                    htmlField.type().getValue()
            );
    }

    public void generateHtml(Map<String, Object> params, String className) {
        try {
            tryToGenerateHtml(params, className);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }

    private void tryToGenerateHtml(Map<String, Object> params, String className) throws IOException, TemplateException {
        Template template = freemarkerConfiguration.getTemplate(templateFileName);
        Writer writer = new FileWriter(outPath + className + ".html");
        template.process(params, writer);
    }
}
