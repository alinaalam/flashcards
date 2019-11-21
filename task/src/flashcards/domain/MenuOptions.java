package flashcards.domain;

public enum MenuOptions {
    ADD("add"),
    REMOVE("remove"),
    IMPORT("import"),
    EXPORT("export"),
    ASK("ask"),
    EXIT("exit"),
    LOG("log"),
    HARDEST_CARD("hardest card"),
    RESET_STATS("reset stats");

    private String value;

    MenuOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MenuOptions getOption(String value) {
        for (MenuOptions option : MenuOptions.values()) {
            if (option.getValue().equals(value)) {
                return option;
            }
        }
        return null;
    }
}
