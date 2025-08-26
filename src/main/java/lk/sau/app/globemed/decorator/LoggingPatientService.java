/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.decorator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lk.sau.app.globemed.entity.Patient;

/**
 *
 * @author Saumya
 */
public class LoggingPatientService extends PatientServiceDecorator {

    private static final String LOG_DIR = "D:\\GlobeMed_Logs";
    private static final String LOG_FILE = LOG_DIR + "\\patient_log.txt";

    public LoggingPatientService(PatientService decoratedService) {
        super(decoratedService);
    }

    @Override
    public void savePatient(Patient patient) {
        log("[LOG] Saving patient: " + patient.getFname() + " " + patient.getLname());

        super.savePatient(patient);

        log("[LOG] Patient saved successfully.");
    }

    private void log(String message) {
        try {
            // Ensure log directory exists
            File dir = new File(LOG_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Format timestamp
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Write to log file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
                writer.write(timestamp + " - " + message);
                writer.newLine();
            }

            // Also print to console
            System.out.println(message);

        } catch (IOException e) {
            System.err.println("[ERROR] Failed to write log: " + e.getMessage());
        }

    }
}
