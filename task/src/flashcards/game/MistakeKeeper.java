package flashcards.game;

import flashcards.domain.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MistakeKeeper {

    private Map<Card, Integer> mistakes;

    public MistakeKeeper() {
        this.mistakes = new HashMap<>();
    }

    public void addMistake(Card c) {
        int counter = mistakes.getOrDefault(c, 0);
        mistakes.put(c, ++counter);
    }

    public Map<Card, Integer> getTheHardestCard() {

        Map<Card, Integer> map = new HashMap<>();
        int maximumMistakes = 0;

        // find the maximum number of mistakes
        for (Map.Entry<Card, Integer> entry : mistakes.entrySet()) {
            if (entry.getValue() >= maximumMistakes) {
                maximumMistakes = entry.getValue();
            }
        }

        // for this number of mistakes, get all the corresponding cards
        for (Map.Entry<Card, Integer> entry : mistakes.entrySet()) {
            if (entry.getValue() == maximumMistakes) {
                map.put(entry.getKey(), maximumMistakes);
            }
        }

        return map;

    }

    public void resetMistakes() {
        mistakes.clear();
    }
}
