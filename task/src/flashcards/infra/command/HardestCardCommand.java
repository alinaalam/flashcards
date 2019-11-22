package flashcards.infra.command;

import flashcards.domain.Card;
import flashcards.game.MistakeKeeper;
import flashcards.infra.ScannerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class HardestCardCommand implements Command {

    private MistakeKeeper mistakeKeeper;

    public HardestCardCommand(MistakeKeeper mistakeKeeper) {
        this.mistakeKeeper = mistakeKeeper;
    }

    @Override
    public void execute() {
        Map<Card, Integer> mistakes = mistakeKeeper.getTheHardestCard();

        if (mistakes.isEmpty()) {
            ScannerFactory.displayOutput("There are no cards with errors.");
            return;
        }

        List<Integer> counter = new ArrayList<>(mistakes.values());

        if (mistakes.size() == 1) {
            ScannerFactory.displayOutput("The hardest card is " + getCardTerms(mistakes) + ". You have " + counter.get(0) + " errors answering it.");
            return;
        }

        ScannerFactory.displayOutput("The hardest cards are " + getCardTerms(mistakes) + ". You have " + counter.get(0) + " errors answering them.");
    }

    private String getCardTerms(Map<Card, Integer> mistakes) {
        return mistakes.keySet().stream()
                .map(s -> "\"" + s.getTerm() + "\"")
                .collect(Collectors.joining());
    }
}
