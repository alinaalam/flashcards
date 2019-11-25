package flashcards;

import flashcards.game.CardGame;
import flashcards.infra.cmd.ParamsParser;

public class Main {
    public static void main(String[] args) {
        ParamsParser paramsParser = new ParamsParser(args);
        CardGame cardGame = new CardGame(paramsParser.getParams());
        cardGame.startGame();
    }
}
