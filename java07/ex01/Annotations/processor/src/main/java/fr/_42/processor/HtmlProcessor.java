package fr._42.processor;

import com.google.auto.service.AutoService;
import javax.annotation.processing.Processor;

import fr._42.annotations.HtmlForm;
import fr._42.annotations.HtmlInput;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.StandardLocation;

@AutoService(Processor.class)

@SupportedAnnotationTypes("fr._42.annotations.HtmlForm")
@SupportedSourceVersion(SourceVersion.RELEASE_25)
public class HtmlProcessor extends AbstractProcessor {

    @Override
    public boolean process(
            Set<? extends TypeElement> annotations,
            RoundEnvironment roundEnv) {

        for (Element element : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {

            HtmlForm form = element.getAnnotation(HtmlForm.class);

            try (Writer writer = processingEnv.getFiler()
                    .createResource(
                            StandardLocation.CLASS_OUTPUT,
                            "",
                            form.fileName(),
                            element)
                    .openWriter()) {

                writer.write("<form action=\"" + form.action()
                        + "\" method=\"" + form.method() + "\">\n");

                for (Element field : element.getEnclosedElements()) {
                    HtmlInput input = field.getAnnotation(HtmlInput.class);

                    if (input != null) {
                        writer.write("    <input type=\"" + input.type()
                                + "\" name=\"" + input.name()
                                + "\" placeholder=\"" + input.placeholder()
                                + "\">\n");
                    }
                }

                writer.write("<input type = \"submit\" value = \"Send\">\n");
                writer.write("</form>\n");

                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.NOTE,
                        "Generated " + form.fileName());

            } catch (IOException e) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "Failed to generate HTML: " + e.getMessage(),
                        element);
            }
        }

        return true;
    }
}
