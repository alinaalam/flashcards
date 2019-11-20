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
        cardMap.remove(term);
        invertedCardMap.remove(term);
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

//    public int importCards(String fileContent) {
//        String[] cardArray = fileContent.split("\n");
//        for (String cardDefinition : cardArray) {
//            String[] content = cardDefinition.split(":");
//            cards.put(content[0], content[1]);
//            invertedCards.put(content[1], content[0]);
//        }
//        return cardArray.length;
//    }
}
