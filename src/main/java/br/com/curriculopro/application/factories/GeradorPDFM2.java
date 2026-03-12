package br.com.curriculopro.application.factories;

import br.com.curriculopro.application.strategies.GeradorPDFStrategy;
import br.com.curriculopro.domain.entities.*;
import br.com.curriculopro.domain.entities.Link;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

import static br.com.curriculopro.infra.utils.PhoneUtil.formatar;

public class GeradorPDFM2 implements GeradorPDFStrategy {

    @Override
    public ByteArrayOutputStream generatePdf(Resume resume) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfDocument pdf = new PdfDocument(new PdfWriter(out));
        Document doc = new Document(pdf, PageSize.A4);
        doc.setMargins(30, 30, 30, 30);

        PdfFont font = PdfFontFactory.createFont("Helvetica");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/yyyy");

        // ===== HEADER =====
        Paragraph name = new Paragraph(resume.getName())
                .setFont(font)
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);

        String info = resume.getPosition() + " | "
                + formatar(resume.getPhone()) + " | "
                + resume.getEmail() + " | "
                + resume.getState() + " - " + resume.getCity();

        Paragraph contact = new Paragraph(info)
                .setFont(font)
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER);

        doc.add(name);
        doc.add(contact);
        doc.add(new LineSeparator(new SolidLine()));

        // ===== LINKS =====
        for (Link link : resume.getLinks()) {
            doc.add(titleLinks(link.getDescription(), font));
        }

        // ===== SUMMARY =====
        if (resume.getSummary() != null) {
            doc.add(sectionTitle("Resumo Profissional", font));
            doc.add(new Paragraph(resume.getSummary())
                    .setFont(font)
                    .setFontSize(11));
        }

        // ===== EXPERIENCES =====
        if (!resume.getExperiences().isEmpty()) {
            doc.add(sectionTitle("Experiência Profissional", font));

            for (Experience e : resume.getExperiences()) {

                String dateText = e.getStart().format(fmt) + " - " +
                        (e.getEnd() != null ? e.getEnd().format(fmt) : "Atual");

                doc.add(jobWithDate(
                        e.getPosition() + " - " + e.getNameCompany(),
                        dateText,
                        font
                ));

                doc.add(new Paragraph(e.getDescription())
                        .setFont(font)
                        .setFontSize(11)
                        .setMarginBottom(8));
            }
        }
        // ===== EDUCATION =====
        if (!resume.getEducations().isEmpty()) {
            doc.add(sectionTitle("Formação Acadêmica", font));

            for (Education e : resume.getEducations()) {

                String dateText = e.getStart().format(fmt) + " - " + (e.getEnd() != null ? e.getEnd().format(fmt) : "Atual");

                doc.add(jobWithDate(
                        e.getCourse() + " - " + e.getInstitution(),
                        dateText,
                        font
                ));
            }
        }

        // ===== SKILLS =====
        if (!resume.getSkills().isEmpty()) {
            doc.add(sectionTitle("Habilidades", font));

            for (Skills s : resume.getSkills()) {
                doc.add(new Paragraph("• " + s.getName() + " — " + s.getDescription())
                        .setFont(font)
                        .setFontSize(11));
            }
        }

        // ===== Language =====
        if (!resume.getLanguages().isEmpty()) {
            doc.add(sectionTitle("Idioma", font));
            for (Language language : resume.getLanguages()) {
                doc.add(new Paragraph("• " + language.getName() + " — " + language.getLevel().getDescription())
                        .setMarginBottom(-5)
                        .setFont(font)
                        .setFontSize(11));
            }
        }

        doc.close();
        return out;
    }

    // =========================
    // MÉTODOS AUXILIARES
    // =========================

    private static Paragraph sectionTitle(String text, PdfFont font) {
        return new Paragraph(text)
                .setFont(font)
                .setFontSize(14)
                .setBold()
                .setFontColor(ColorConstants.BLUE)
                .setMarginTop(15);
    }

    private static Paragraph titleLinks(String text, PdfFont font) {
        return new Paragraph(text)
                .setFont(font)
                .setFontSize(10)
                .setFontColor(ColorConstants.GRAY)
                .setItalic()
                .setMarginBottom(-5);
    }

    private static Table jobWithDate(String left, String right, PdfFont font) {

        Table table = new Table(UnitValue.createPercentArray(new float[]{80, 20}))
                .useAllAvailableWidth();

        Cell leftCell = new Cell()
                .add(new Paragraph(left)
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                .setBorder(Border.NO_BORDER);

        Cell rightCell = new Cell()
                .add(new Paragraph(right)
                        .setFont(font)
                        .setFontSize(10)
                        .setItalic()
                        .setTextAlignment(TextAlignment.RIGHT))
                .setBorder(Border.NO_BORDER);

        table.addCell(leftCell);
        table.addCell(rightCell);

        return table;
    }
}
