package flashcards.domain;

public enum MenuOptions {
    ADD("add"),
    REMOVE("remove"),
    IMPORT("import"),
    EXPORT("export"),
    ASK("ask"),
    EXIT("exit");

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
