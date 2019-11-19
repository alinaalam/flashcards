package flashcards.infra;

import java.util.Scanner;

public class ScannerFactory {

    private static Scanner scanner;

    private ScannerFactory() { }

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
