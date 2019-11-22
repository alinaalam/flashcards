package flashcards.game;

import flashcards.domain.MenuOptions;
import flashcards.infra.ScannerFactory;
import flashcards.infra.command.*;

public class CardGame {

    private CardHolder cardHolder;
    private MistakeKeeper mistakeKeeper;

    public CardGame() {
        this.cardHolder = new CardHolder();
        this.mistakeKeeper = new MistakeKeeper();
    }

    public void startGame() {
        displayMenu();
    }

    private void displayMenu() {
        MenuOptions option;
        Command command = null;

        do {
            String menu = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";
            String o = ScannerFactory.displayOutputAndGetInput(menu);

            option = MenuOptions.getOption(o);

            switch (option) {
                case ADD:
                    command = new AddCommand(cardHolder);
                    break;
                case REMOVE:
                    command = new RemoveCommand(cardHolder);
                    break;
                case IMPORT:
                    command = new ImportCommand(cardHolder);
                    break;
                case EXPORT:
                    command = new ExportCommand(cardHolder);
                    break;
                case ASK:
                    command = new AskCommand(cardHolder, mistakeKeeper);
                    break;
                case EXIT:
                    command = new ExitCommand();
                    break;
                case LOG:
                    command = new LogCommand();
                    break;
                case HARDEST_CARD:
                    command = new HardestCardCommand(mistakeKeeper);
                    break;
                case RESET_STATS:
                    command = new ResetStatsCommand(mistakeKeeper);
            }
            command.execute();
        } while (option != MenuOptions.EXIT);

    }
}
