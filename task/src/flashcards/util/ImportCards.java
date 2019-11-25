package flashcards.util;

import flashcards.domain.Card;
import flashcards.game.CardHolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImportCards {

    public static int importCards(String filename, CardHolder cardHolder) throws IOException {
        try {
            Path path = Path.of(filename);
            String fileContent = Files.readString(path);
            int totalCards = helper(fileContent, cardHolder);
            return totalCards;
        } catch (IOException e) {
            throw e;
        }
    }

    private static int helper(String fileContent, CardHolder cardHolder) {
        String[] cardArray = fileContent.split("\n");
        for (String cardDefinition : cardArray) {
            String[] content = cardDefinition.split(":");
            Card card = new Card(content[0], content[1], Integer.parseInt(content[2]));
            cardHolder.add(card);
        }
        return cardArray.length;
    }
}
