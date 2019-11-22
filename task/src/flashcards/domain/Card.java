package flashcards.domain;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private final String term;
    private final String definition;
    private int mistakes;

    public Card(String term, String definition) {
        this(term, definition, 0);
    }

    public Card(String term, String definition, int mistakes) {
        this.term = term;
        this.definition = definition;
        this.mistakes = mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public void incrementMistakes() {
        mistakes++;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public int getMistakes() {
        return mistakes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return mistakes == card.mistakes &&
                Objects.equals(term, card.term) &&
                Objects.equals(definition, card.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term, definition, mistakes);
    }

    @Override
    public int compareTo(@NotNull Card o) {
        return o.mistakes - mistakes;
    }
}
