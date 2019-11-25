package flashcards.game;

import flashcards.domain.MenuOptions;
import flashcards.infra.ScannerFactory;
import flashcards.infra.cmd.Params;
import flashcards.infra.command.*;
import flashcards.util.ExportCards;
import flashcards.util.ImportCards;

import java.io.IOException;

public class CardGame {

    private Params params;
    private CardHolder cardHolder;

    public CardGame(Params params) {
        this.params = params;
        this.cardHolder = new CardHolder();
    }

    public void startGame() {
        checkIfThereIsAnythingToImport();
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
                    command = new AskCommand(cardHolder);
                    break;
                case EXIT:
                    command = new ExitCommand();
                    break;
                case LOG:
                    command = new LogCommand();
                    break;
                case HARDEST_CARD:
                    command = new HardestCardCommand(cardHolder);
                    break;
                case RESET_STATS:
                    command = new ResetStatsCommand(cardHolder);
            }
            command.execute();
        } while (option != MenuOptions.EXIT);
        checkIfThereIsAnythingToExport();
    }

    private void checkIfThereIsAnythingToImport() {
        if (params.shouldImport()) {
            try {
                int totalCards = ImportCards.importCards(params.getFileToImport(), cardHolder);
                ScannerFactory.displayOutput(totalCards + " cards have been loaded.");
            } catch (IOException e) {

            }
        }
    }

    private void checkIfThereIsAnythingToExport() {
        if (params.shouldExport()) {
            try {
                int totalCards = ExportCards.exportCards(params.getFileToExportTo(), cardHolder);
                ScannerFactory.displayOutput(totalCards + " cards have been saved.");
            } catch (IOException e) {

            }
        }
    }
}
