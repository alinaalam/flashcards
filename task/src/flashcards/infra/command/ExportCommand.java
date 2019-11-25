package flashcards.infra.command;

import flashcards.game.CardHolder;
import flashcards.infra.ScannerFactory;
import flashcards.util.ExportCards;

import java.io.IOException;

public class ExportCommand implements Command {

    private CardHolder cardHolder;

    public ExportCommand(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public void execute() {
        String filename = ScannerFactory.displayOutputAndGetInput("\"File name:\"");
        try {
            int totalCards = ExportCards.exportCards(filename, cardHolder);
            ScannerFactory.displayOutput(totalCards + " cards have been saved.");
        } catch (IOException e) {
        }
    }
}
