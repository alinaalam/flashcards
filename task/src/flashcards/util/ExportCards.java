package flashcards.util;

import flashcards.domain.Card;
import flashcards.game.CardHolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportCards {

    public static int exportCards(String filename, CardHolder cardHolder) throws IOException {
        File file = new File(filename);
        try {
            FileWriter fileWriter = new FileWriter(file);
            List<Card> cards = cardHolder.getCards();
            for (Card card :  cards) {
                fileWriter.write(card.getTerm() + ":" + card.getDefinition() + ":" + card.getMistakes());
                fileWriter.write("\n");
            }
            fileWriter.close();
            return cards.size();
        } catch (IOException e) {
            throw e;
        }
    }
}
