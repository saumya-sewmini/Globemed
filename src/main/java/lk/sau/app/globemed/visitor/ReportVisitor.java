/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.visitor;

import lk.sau.app.globemed.dto.MedicalReportDTO;

/**
 *
 * @author Saumya
 */
public interface ReportVisitor extends MedicalRecordVisitor{
    
    void visit(MedicalReportDTO reportDTO);
    
}
