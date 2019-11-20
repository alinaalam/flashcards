package flashcards.infra.command;

import flashcards.domain.Card;
import flashcards.game.CardHolder;
import flashcards.infra.ScannerFactory;

import java.util.Scanner;

public class RemoveCommand implements Command {

    private CardHolder cardHolder;

    public RemoveCommand(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public void execute() {
        Scanner scanner = ScannerFactory.getScanner();
        System.out.println("The card:");
        String term = scanner.nextLine();

        if (cardHolder.getCardFromTerm(term) != null) {
            cardHolder.remove(term);
            System.out.println("The card has been removed.");
        }
        else {
            System.out.println("Can't remove \"" + term + "\": there is no such card.");
        }
    }
}
