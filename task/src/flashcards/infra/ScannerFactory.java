package flashcards.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerFactory {

    private static Scanner scanner = new Scanner(System.in);
    private static List<String> logFiles = new ArrayList<>();

    private ScannerFactory() { }

    public static String displayOutputAndGetInput(String output) {
        System.out.println(output);
        logFiles.add(output);
        String input =  scanner.nextLine();
        logFiles.add(input);
        return input;
    }

    public static void displayOutput(String output) {
        System.out.println(output);
        logFiles.add(output);
    }

    public static void clearLog() {
        logFiles.clear();
    }

    public static List<String> getLogFiles() {
        return logFiles;
    }
}
