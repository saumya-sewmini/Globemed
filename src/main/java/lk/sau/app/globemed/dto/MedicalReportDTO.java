/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.dto;

import java.time.LocalDate;
import java.util.List;
import lk.sau.app.globemed.entity.MedicalRecord;
import lk.sau.app.globemed.visitor.MedicalRecordVisitor;
import lk.sau.app.globemed.visitor.PdfMedicalReportVisitor;
import lk.sau.app.globemed.visitor.ReportVisitor;
import lk.sau.app.globemed.visitor.VisitableMedicalRecord;

/**
 *
 * @author Saumya
 */
public class MedicalReportDTO implements VisitableMedicalRecord {

    private String patientName;
    private LocalDate dob;
    private String gender;
    private List<MedicalRecord> records;

    public MedicalReportDTO(String patientName, LocalDate dob, String gender, List<MedicalRecord> records) {
        this.patientName = patientName;
        this.dob = dob;
        this.gender = gender;
        this.records = records;
    }

    public String getPatientName() {
        return patientName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public List<MedicalRecord> getRecords() {
        return records;
    }

    @Override
    public void accept(MedicalRecordVisitor visitor) {
        if (visitor instanceof ReportVisitor) {
            ((ReportVisitor) visitor).visit(this);
        }
    }

}
