package flashcards.infra.command;

import flashcards.domain.Card;
import flashcards.game.CardHolder;
import flashcards.infra.ScannerFactory;

import java.util.*;

public class AskCommand implements Command {

    private CardHolder cardHolder;

    public AskCommand(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public void execute() {
        Scanner scanner = ScannerFactory.getScanner();

        System.out.println("How many times?");
        int turns = Integer.parseInt(scanner.nextLine());
        List<Card> randomCards = cardHolder.getRandomCards(turns);

        for (Card card : randomCards) {
            System.out.println("Print the definition of \"" + card.getTerm() + "\":");
            String answer = scanner.nextLine();
            if (card.getDefinition().equals(answer)) {
                System.out.println("Correct answer.");
                continue;
            }
            // check if the answer belongs to some other flash card
            Card otherCard = cardHolder.getCardFromDefinition(answer);
            if (otherCard == null) {
                System.out.println("Wrong answer. The correct one is \"" + card.getDefinition() + "\".");
                continue;
            }
            System.out.print("Wrong answer. The correct one is \"" + card.getDefinition() + "\", ");
            System.out.println("you've just written the definition of \"" + otherCard.getTerm() + "\".");
        }
    }
}
