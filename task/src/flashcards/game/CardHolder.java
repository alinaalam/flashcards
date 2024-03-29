package flashcards.game;

import flashcards.domain.Card;

import java.util.*;

public class CardHolder {

    private Map<String, Card> cardMap;
    private Map<String, Card> invertedCardMap;
    private Random random;

    public CardHolder() {
        cardMap = new HashMap<>();
        invertedCardMap = new HashMap<>();
        random = new Random();
    }

    public void add(Card card) {
        // update the current value
        if (cardMap.containsKey(card.getTerm())) {
            remove(card.getTerm());
        }
        cardMap.put(card.getTerm(), card);
        invertedCardMap.put(card.getDefinition(), card);
    }

    public Card getCardFromTerm(String term) {
        return cardMap.getOrDefault(term, null);
    }

    public Card getCardFromDefinition(String definition) {
        return invertedCardMap.getOrDefault(definition, null);
    }

    public void remove(String term) {
        Card card = cardMap.get(term);
        cardMap.remove(term);
        invertedCardMap.remove(card.getDefinition());
    }

    public List<Card> getRandomCards(int numberOfCards) {

        List<String> keySet = new ArrayList<>(cardMap.keySet());
        List<Card> randomCards = new ArrayList<>();

        while (numberOfCards > 0) {
            int index = random.nextInt(keySet.size());
            randomCards.add(cardMap.get(keySet.get(index)));
            numberOfCards--;
        }

        return randomCards;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cardMap.values());
    }

    public void updateCard(Card card) {
        cardMap.put(card.getTerm(), card);
        invertedCardMap.put(card.getDefinition(), card);
    }
}
