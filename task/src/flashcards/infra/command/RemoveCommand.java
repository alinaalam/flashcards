package flashcards.infra.command;

import flashcards.game.CardHolder;
import flashcards.infra.ScannerFactory;

public class RemoveCommand implements Command {

    private CardHolder cardHolder;

    public RemoveCommand(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public void execute() {
        String term = ScannerFactory.displayOutputAndGetInput("The card:");

        if (cardHolder.getCardFromTerm(term) != null) {
            cardHolder.remove(term);
            ScannerFactory.displayOutput("The card has been removed.");
        }
        else {
            ScannerFactory.displayOutput("Can't remove \"" + term + "\": there is no such card.");
        }
    }
}
