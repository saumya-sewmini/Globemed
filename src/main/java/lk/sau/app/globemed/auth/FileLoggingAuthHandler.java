/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.auth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lk.sau.app.globemed.entity.User;

/**
 *
 * @author Saumya
 */
public class FileLoggingAuthHandler extends AuthHandlerDecorator {

    private static final String LOG_FILE_PATH = "D:\\GlobeMed_Logs\\login.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public FileLoggingAuthHandler(AuthHandler wrappedHandler) {
        super(wrappedHandler);
    }

    @Override
    public void handle(User user) {
        String logEntry = String.format(
                "%s - [LOGIN] User: %s, Role: %s",
                LocalDateTime.now().format(formatter),
                user.getUsername(),
                user.getRole().getRoleName()
        );

        writeLogToFile(logEntry);

        super.handle(user); // continue chain
    }

    private void writeLogToFile(String logEntry) {
        try {
            File logDir = new File("D:\\GlobeMed_Logs");
            if (!logDir.exists()) {
                logDir.mkdirs(); // create folder if missing
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
                writer.write(logEntry);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing login log: " + e.getMessage());
        }
    }

}
