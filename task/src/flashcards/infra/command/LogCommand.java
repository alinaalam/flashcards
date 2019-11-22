package flashcards.infra.command;

import flashcards.infra.ScannerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LogCommand implements Command {
    public LogCommand() {
    }

    @Override
    public void execute() {
        String filename = ScannerFactory.displayOutputAndGetInput("File name: ");
        File file = new File(filename);
        try {
            FileWriter fileWriter = new FileWriter(file);
            List<String> logs = ScannerFactory.getLogFiles();
            for (String log :  logs) {
                fileWriter.write(log);
                fileWriter.write("\n");
            }
            ScannerFactory.displayOutput("The log has been saved.");
            fileWriter.close();
        } catch (IOException e) {

        }
    }
}
