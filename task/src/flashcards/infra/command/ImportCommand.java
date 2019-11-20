package flashcards.infra.command;

import flashcards.domain.Card;
import flashcards.game.CardHolder;
import flashcards.infra.ScannerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ImportCommand implements Command {

    private CardHolder cardHolder;

    public ImportCommand(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public void execute() {
        System.out.println("File name:");
        Scanner scanner = ScannerFactory.getScanner();

        String filename = scanner.nextLine();
        try {
            Path path = Path.of(filename);
            String fileContent = Files.readString(path);
            int totalCards = importCards(fileContent);
            System.out.println(totalCards + " cards have been loaded");
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    private int importCards(String fileContent) {
        String[] cardArray = fileContent.split("\n");
        for (String cardDefinition : cardArray) {
            String[] content = cardDefinition.split(":");
            Card card = new Card(content[0], content[1]);
            cardHolder.add(card);
        }
        return cardArray.length;
    }
}