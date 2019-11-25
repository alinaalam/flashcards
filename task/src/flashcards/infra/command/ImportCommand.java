package flashcards.infra.command;

import flashcards.game.CardHolder;
import flashcards.infra.ScannerFactory;
import flashcards.util.ImportCards;

import java.io.IOException;

public class ImportCommand implements Command {

    private CardHolder cardHolder;

    public ImportCommand(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public void execute() {
        String filename = ScannerFactory.displayOutputAndGetInput("File name:");
        try {
            int totalCards = ImportCards.importCards(filename, cardHolder);
            ScannerFactory.displayOutput(totalCards + " cards have been loaded");
        } catch (IOException e) {
            ScannerFactory.displayOutput("File not found.");
        }
    }
}