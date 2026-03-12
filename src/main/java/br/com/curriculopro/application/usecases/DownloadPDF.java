package br.com.curriculopro.application.usecases;

import br.com.curriculopro.domain.entities.Education;
import br.com.curriculopro.domain.entities.Link;
import br.com.curriculopro.domain.entities.Experience;
import br.com.curriculopro.domain.entities.Resume;
import br.com.curriculopro.domain.entities.Skills;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;


import java.io.ByteArrayOutputStream;

import java.time.format.DateTimeFormatter;

public class DownloadPDF {

    public static ByteArrayOutputStream generate(Resume resume) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfDocument pdf = new PdfDocument(new PdfWriter(out));
        Document doc = new Document(pdf, PageSize.A4);
        doc.setMargins(30, 30, 30, 30);

        var font = PdfFontFactory.createFont("Helvetica");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/yyyy");

        // ===== HEADER =====
        Paragraph name = new Paragraph(resume.getName())
                .setFont(font)
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);

        String info = resume.getPosition() + " | " + resume.getPhone() + " | " + resume.getEmail() + " | " + resume.getState() + " - " + resume.getCity() ;
        Paragraph contact = new Paragraph(info)
                .setFont(font)
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER);

        doc.add(name);
        doc.add(contact);
        doc.add(new LineSeparator(new SolidLine()));

        // ===== LINKS =====
        for (Link links: resume.getLinks()) {
            doc.add(titleLinks(links.getDescription(), font));
        }

        // ===== SUMMARY =====
        if (resume.getSummary() != null) {
            doc.add(sectionTitle("Resumo Profissional", font));
            doc.add(new Paragraph(resume.getSummary()).setFont(font).setFontSize(11));
        }

        // ===== EXPERIENCES =====
        doc.add(sectionTitle("Experiência Profissional", font));
        for (Experience e : resume.getExperiences()) {
            doc.add(jobTitle(e.getPosition() + " - " + e.getNameCompany(), font));
            doc.add(dateCity(
                    e.getStart().format(fmt) + " - " +
                            (e.getEnd() != null ? e.getEnd().format(fmt) : "Atual")
                            + " | " + e.getCity() + " - " + e.getState(),
                    font
            ));
            doc.add(new Paragraph(e.getDescription()).setFont(font).setFontSize(11));
        }

        // ===== EDUCATION =====
        doc.add(sectionTitle("Formação Acadêmica", font));
        for (Education e : resume.getEducations()) {
            doc.add(jobTitle(e.getCourse() + " - " + e.getInstitution(), font));
            doc.add(dateCity(
                    e.getStart().format(fmt) + " - " +
                            (e.getEnd() != null ? e.getEnd().format(fmt) : "Atual"),
                    font
            ));
        }

        // ===== SKILLS =====
        doc.add(sectionTitle("Habilidades", font));
        for (Skills s : resume.getSkills()) {
            doc.add(new Paragraph("• " + s.getName() + " — " + s.getDescription())
                    .setFont(font)
                    .setFontSize(11));
        }

        doc.close();
        return out;
    }

    private static Paragraph sectionTitle(String text, com.itextpdf.kernel.font.PdfFont font) {
        return new Paragraph(text)
                .setFont(font)
                .setFontSize(14)
                .setBold()
                .setFontColor(ColorConstants.BLUE)
                .setMarginTop(15);
    }

    private static Paragraph jobTitle(String text, com.itextpdf.kernel.font.PdfFont font) {
        return new Paragraph(text)
                .setFont(font)
                .setFontSize(12)
                .setBold();
    }

    private static Paragraph dateCity(String text, com.itextpdf.kernel.font.PdfFont font) {
        return new Paragraph(text)
                .setFont(font)
                .setFontSize(10)
                .setItalic();
    }

    private static Paragraph titleLinks(String text, com.itextpdf.kernel.font.PdfFont font) {
        return new Paragraph(text)
                .setFont(font)
                .setMarginBottom(-5)
                .setFontSize(10)
                .setFontColor(ColorConstants.GRAY)
                .setItalic();
    }
}
