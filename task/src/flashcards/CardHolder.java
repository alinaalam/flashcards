package flashcards;

import flashcards.infra.ScannerFactory;

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

    public int importCards(String fileContent) {
        String[] cardArray = fileContent.split("\n");
        for (String cardDefinition : cardArray) {
            String[] content = cardDefinition.split(":");
            cards.put(content[0], content[1]);
            invertedCards.put(content[1], content[0]);
        }
        return cardArray.length;
    }

    public void removeCard() {
        System.out.println("The card:");
        String term = scanner.nextLine();
        if (cards.containsKey(term)) {
            String definition = cards.get(term);
            cards.remove(term);
            invertedCards.remove(definition);
            System.out.println("The card has been removed.");
        }
        else {
            System.out.println("Can't remove \"" + term + "\": there is no such card.");
        }
    }

    public Map<String, String> getCards() {
        return cards;
    }

    public Map<String, String> getInvertedCards() {
        return invertedCards;
    }
}
