package flashcards.infra.command;

import flashcards.infra.ScannerFactory;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        ScannerFactory.displayOutput("\"Bye bye!\"");
    }
}
