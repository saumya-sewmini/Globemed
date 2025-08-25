/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.visitor;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.*;
import lk.sau.app.globemed.dto.MedicalReportDTO;
import lk.sau.app.globemed.entity.MedicalRecord;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

/**
 *
 * @author Saumya
 */
public class PdfMedicalReportVisitor implements ReportVisitor {

    @Override
    public void visit(MedicalReportDTO report) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - 70;
            float pageWidth = page.getMediaBox().getWidth();

            PDPageContentStream cs = new PDPageContentStream(document, page);

            // ===== HEADER =====
            cs.setFont(PDType1Font.HELVETICA_BOLD, 20);
            cs.beginText();
            cs.newLineAtOffset((pageWidth / 2) - 80, yStart);
            cs.showText("GlobeMed Healthcare - Medical Report");
            cs.endText();

            yStart -= 40;

            // ===== PATIENT INFO =====
            cs.setFont(PDType1Font.HELVETICA, 12);
            String[] patientInfo = {
                "Patient: " + report.getPatientName(),
                "DOB: " + report.getDob(),
                "Gender: " + report.getGender()
            };

            for (String line : patientInfo) {
                cs.beginText();
                cs.newLineAtOffset(margin, yStart);
                cs.showText(line);
                cs.endText();
                yStart -= 20;
            }

            yStart -= 15;
            cs.moveTo(margin, yStart);
            cs.lineTo(pageWidth - margin, yStart);
            cs.stroke();
            yStart -= 25;

            // ===== RECORDS TABLE =====
            cs.setFont(PDType1Font.HELVETICA_BOLD, 13);
            cs.beginText();
            cs.newLineAtOffset(margin, yStart);
            cs.showText("Medical History");
            cs.endText();
            yStart -= 25;

            cs.setFont(PDType1Font.HELVETICA, 11);

            for (MedicalRecord rec : report.getRecords()) {
                String line = String.format(
                        "[%s] %s | Medicine: %s | Note: %s",
                        rec.getRecordDate(),
                        rec.getTreatmentTypeId().getTreatmentType(),
                        rec.getMedicine(),
                        rec.getNote()
                );

                cs.beginText();
                cs.newLineAtOffset(margin, yStart);
                cs.showText(line.length() > 120 ? line.substring(0, 120) + "..." : line);
                cs.endText();
                yStart -= 18;

                if (yStart < 100) { // add new page if near bottom
                    cs.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    cs = new PDPageContentStream(document, page);
                    yStart = page.getMediaBox().getHeight() - 70;
                }
            }

            cs.close();

            // ===== PREVIEW =====
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            document.close();
            byte[] pdfBytes = baos.toByteArray();

            PDDocument docForRender = PDDocument.load(pdfBytes);
            PDFRenderer renderer = new PDFRenderer(docForRender);
            BufferedImage image = renderer.renderImageWithDPI(0, 150);
            docForRender.close();

            JLabel pdfLabel = new JLabel(new ImageIcon(image));
            JScrollPane scrollPane = new JScrollPane(pdfLabel);

            JFrame frame = new JFrame("Medical Report Preview");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(900, 700);
            frame.setLocationRelativeTo(null);

            JPanel buttonPanel = new JPanel();
            JButton printBtn = new JButton("Print PDF");
            JButton saveBtn = new JButton("Save PDF");

            buttonPanel.add(printBtn);
            buttonPanel.add(saveBtn);

            frame.setLayout(new BorderLayout());
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.setVisible(true);

            // ===== PRINT =====
            printBtn.addActionListener(e -> {
                try {
                    PDDocument docPrint = PDDocument.load(pdfBytes);
                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setJobName("Medical Report");
                    job.setPrintable(new PDFPrintable(docPrint, Scaling.SHRINK_TO_FIT));
                    if (job.printDialog()) {
                        job.print();
                    }
                    docPrint.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Printing Failed: " + ex.getMessage());
                }
            });

            // ===== SAVE =====
            saveBtn.addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(new File("MedicalReport_" + report.getPatientName() + ".pdf"));
                int option = chooser.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    try (FileOutputStream fos = new FileOutputStream(chooser.getSelectedFile())) {
                        fos.write(pdfBytes);
                        JOptionPane.showMessageDialog(frame, "PDF Saved Successfully ✅");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Save Failed: " + ex.getMessage());
                    }
                }
            });

            System.out.println("PDF Generated & Preview Opened ✅");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(MedicalRecord medicalRecord) {
        System.out.println("fffffffff");
    }

}
