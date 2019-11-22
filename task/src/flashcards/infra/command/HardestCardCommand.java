package flashcards.infra.command;

import flashcards.domain.Card;
import flashcards.game.CardHolder;
import flashcards.infra.ScannerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class HardestCardCommand implements Command {

    private CardHolder cardHolder;

    public HardestCardCommand(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public void execute() {
        List<Card> hardestCards = getTheHardestCards(cardHolder.getCards());

        if (hardestCards.isEmpty()) {
            ScannerFactory.displayOutput("There are no cards with errors.");
            return;
        }

        int mistakes = hardestCards.get(0).getMistakes();

        if (hardestCards.size() == 1) {
            ScannerFactory.displayOutput("The hardest card is " + getCardTerms(hardestCards) + ". You have " + mistakes + " errors answering it.");
            return;
        }

        ScannerFactory.displayOutput("The hardest cards are " + getCardTerms(hardestCards) + ". You have " + mistakes + " errors answering them.");
    }

    private String getCardTerms(List<Card> cards) {
        return cards.stream()
                .map(s -> "\"" + s.getTerm() + "\"")
                .collect(Collectors.joining());
    }

    private List<Card> getTheHardestCards(List<Card> cards) {

        if (cards.isEmpty()) {
            return new ArrayList<>();
        }

        Collections.sort(cards);
        int maximumMistakes = cards.get(0).getMistakes();

        if (maximumMistakes == 0) {
            return new ArrayList<>();
        }

        return cards.stream()
                .filter(card -> card.getMistakes() == maximumMistakes)
                .collect(Collectors.toList());
    }
}
