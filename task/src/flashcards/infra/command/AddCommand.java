package flashcards.infra.command;

import flashcards.domain.Card;
import flashcards.game.CardHolder;
import flashcards.infra.ScannerFactory;

import java.util.Scanner;

public class AddCommand implements Command {

    private CardHolder cardHolder;

    public AddCommand(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public void execute() {

        Scanner scanner = ScannerFactory.getScanner();
        System.out.println("The card:");
        String term = scanner.nextLine();

        if (cardHolder.getCardFromTerm(term) != null) {
            System.out.println("The card \"" + term + "\" already exists.");
            return;
        }

        System.out.println("The definition of the card:");
        String definition = scanner.nextLine();

        if (cardHolder.getCardFromDefinition(definition) != null) {
            System.out.println("The definition \"" + definition + "\" already exists.");
            return;
        }

        Card card =  new Card(term, definition);
        cardHolder.add(card);

        System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
    }
}
