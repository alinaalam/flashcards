package flashcards;

import flashcards.infra.ScannerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CardGame {

    private final Scanner scanner;
    private CardHolder cardHolder;

    public CardGame() {
        scanner = ScannerFactory.getScanner();
        this.cardHolder = new CardHolder();
    }

    public void startGame() {
        displayMenu();
    }

    private void displayMenu() {
        MenuOptions option;
        do {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String o = scanner.nextLine();
            option = MenuOptions.getOption(o);
            switch (option) {
                case ADD:
                    cardHolder.addCard();
                    break;
                case REMOVE:
                    cardHolder.removeCard();
                    break;
                case IMPORT:
                    importCards();
                    break;
                case EXPORT:
                    export();
                    break;
                case ASK:
                    play();
                    break;
            }
        } while (option != MenuOptions.EXIT);
        System.out.println("Bye bye!");
    }

    private void importCards() {
        System.out.println("File name:");
        String filename = scanner.nextLine();
        try {
            Path path = Path.of(filename);
            String fileContent = Files.readString(path);
            int totalCards = cardHolder.importCards(fileContent);
            System.out.println(totalCards + " cards have been loaded");
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    private void export() {
        System.out.println("File name:");
        String filename = scanner.nextLine();
        File file = new File(filename);
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Map.Entry<String, String> entry : cardHolder.getCards().entrySet()) {
                fileWriter.write(entry.getKey() + ":" + entry.getValue());
                fileWriter.write("\n");
            }
            System.out.println(cardHolder.getCards().size() + " cards have been saved.");
            fileWriter.close();
        } catch (IOException e) {

        }
    }

    private void play() {
        Map<String, String> cards = cardHolder.getCards();
        Map<String, String> invertedCards = cardHolder.getInvertedCards();
        List<String> keys = new ArrayList<>(cards.keySet());

        System.out.println("How many times?");
        int turns = Integer.parseInt(scanner.nextLine());
        Random random = new Random();

        while (turns > 0) {
            int index = random.nextInt(keys.size());
            System.out.println("Print the definition of \"" + keys.get(index) + "\":");
            String answer = scanner.nextLine();
            if (cards.get(keys.get(index)).equals(answer)) {
                System.out.println("Correct answer.");
            }
            else {
                // check if the answer belongs to some other flash card
                if (cards.containsValue(answer)) {
                    System.out.print("Wrong answer. The correct one is \"" + cards.get(keys.get(index)) + "\", ");
                    System.out.println("you've just written the definition of \"" + invertedCards.get(answer) + "\".");
                }
                else {
                    System.out.println("Wrong answer. The correct one is \"" + cards.get(keys.get(index)) + "\".");
                }
            }
            turns--;
        }
    }
}
