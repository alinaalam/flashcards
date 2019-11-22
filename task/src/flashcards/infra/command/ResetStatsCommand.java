package flashcards.infra.command;

import flashcards.domain.Card;
import flashcards.game.CardHolder;
import flashcards.infra.ScannerFactory;

import java.util.List;

public class ResetStatsCommand implements Command {

    private CardHolder cardHolder;

    public ResetStatsCommand(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public void execute() {
        List<Card> cards = cardHolder.getCards();

        for (Card card : cards) {
            card.setMistakes(0);
            cardHolder.updateCard(card);
        }

        ScannerFactory.displayOutput("Card statistics has been reset.");
    }
}
