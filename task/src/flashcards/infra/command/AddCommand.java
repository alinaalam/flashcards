package flashcards.infra.command;

import flashcards.domain.Card;
import flashcards.game.CardHolder;
import flashcards.infra.ScannerFactory;

public class AddCommand implements Command {

    private CardHolder cardHolder;

    public AddCommand(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public void execute() {
        String term = ScannerFactory.displayOutputAndGetInput("The card:");

        if (cardHolder.getCardFromTerm(term) != null) {
            System.out.println("The card \"" + term + "\" already exists.");
            return;
        }

        String definition = ScannerFactory.displayOutputAndGetInput("The definition of the card:");

        if (cardHolder.getCardFromDefinition(definition) != null) {
           ScannerFactory.displayOutput("The definition \"" + definition + "\" already exists.");
            return;
        }

        Card card =  new Card(term, definition);
        cardHolder.add(card);

        ScannerFactory.displayOutput("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
    }
}
