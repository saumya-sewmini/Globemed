/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.sau.app.globemed.visitor;

/**
 *
 * @author Saumya
 */
public interface VisitableBill {

    void accept(BillVisitor visitor);

}
