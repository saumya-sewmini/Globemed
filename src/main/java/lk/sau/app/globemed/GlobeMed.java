package lk.sau.app.globemed;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import gui.LoginForm;

/**
 *
 * @author Saumya
 */
public class GlobeMed {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        LoginForm login = new LoginForm();
        login.setVisible(true);

//        PatientDashboard p = new PatientDashboard();
//        p.setVisible(true);

    }
}
