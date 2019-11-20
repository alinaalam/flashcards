package flashcards.infra.command;

import flashcards.domain.Card;
import flashcards.game.CardHolder;
import flashcards.infra.ScannerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ExportCommand implements Command {

    private CardHolder cardHolder;

    public ExportCommand(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public void execute() {
        System.out.println("File name:");
        Scanner scanner = ScannerFactory.getScanner();

        String filename = scanner.nextLine();
        File file = new File(filename);
        try {
            FileWriter fileWriter = new FileWriter(file);
            List<Card> cards = cardHolder.getCards();
            for (Card card :  cards) {
                fileWriter.write(card.getTerm() + ":" + card.getDefinition());
                fileWriter.write("\n");
            }
            System.out.println(cards.size() + " cards have been saved.");
            fileWriter.close();
        } catch (IOException e) {

        }
    }
}
