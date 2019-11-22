package flashcards.infra.command;

import flashcards.game.MistakeKeeper;
import flashcards.infra.ScannerFactory;

public class ResetStatsCommand implements Command {

    private MistakeKeeper mistakeKeeper;

    public ResetStatsCommand(MistakeKeeper mistakeKeeper) {
        this.mistakeKeeper = mistakeKeeper;
    }

    @Override
    public void execute() {
        mistakeKeeper.resetMistakes();
        ScannerFactory.displayOutput("Card statistics has been reset.");
    }
}
