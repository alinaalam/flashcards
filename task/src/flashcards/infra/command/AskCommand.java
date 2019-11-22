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

        int turns = Integer.parseInt(ScannerFactory.displayOutputAndGetInput("How many times?"));
        List<Card> randomCards = cardHolder.getRandomCards(turns);

        for (Card card : randomCards) {
            String answer = ScannerFactory.displayOutputAndGetInput("Print the definition of \"" + card.getTerm() + "\":");
            if (card.getDefinition().equals(answer)) {
                ScannerFactory.displayOutput("Correct answer.");
                continue;
            }
            // add mistake and update this card in card holder
            card.incrementMistakes();
           cardHolder.updateCard(card);
            // check if the answer belongs to some other flash card
            Card otherCard = cardHolder.getCardFromDefinition(answer);
            if (otherCard == null) {
                ScannerFactory.displayOutput("Wrong answer. The correct one is \"" + card.getDefinition() + "\".");
                continue;
            }
            StringBuilder sb = new StringBuilder("Wrong answer. The correct one is \"" + card.getDefinition() + "\", ");
            sb.append("you've just written the definition of \"" + otherCard.getTerm() + "\".");

            ScannerFactory.displayOutput(sb.toString());
        }
    }
}
