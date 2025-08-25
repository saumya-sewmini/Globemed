/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.sau.app.globemed.visitor;

import lk.sau.app.globemed.entity.Bill;

/**
 *
 * @author Saumya
 */
public interface BillVisitor {
    
    void visit(Bill bill);
    
}
