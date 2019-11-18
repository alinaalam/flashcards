package flashcards;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CardHolder {
    private final Scanner scanner;
    private Map<String, String> cards;
    private Map<String, String> invertedCards;

    public CardHolder() {
        scanner = ScannerFactory.getScanner();
        cards = new LinkedHashMap<>();
        invertedCards = new HashMap<>();
    }

    public void addCard() {
        System.out.println("The card:");
        String term = scanner.nextLine();

        if (cards.containsKey(term)) {
            System.out.println("The card \"" + term + "\" already exists.");
            return;
        }

        System.out.println("The definition of the card:");
        String definition = scanner.nextLine();

        if (invertedCards.containsKey(definition)) {
            System.out.println("The definition \"" + definition + "\" already exists.");
            return;
        }

        cards.put(term, definition);
        invertedCards.put(definition, term);

        System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
    }

    public void removeCard() {

    }

    public Map<String, String> getCards() {
        return cards;
    }

    public Map<String, String> getInvertedCards() {
        return invertedCards;
    }
}
