package blackjack;

public class Card {
    private final int suit;
    private final int rank;
    private final int value;

    public Card(int suit, int rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public Card(Card card) {
        this.suit = card.suit;
        this.rank = card.rank;
        this.value = card.value;
    }

    public int getSuit() {
        return this.suit;
    }

    public int getRank() {
        return this.rank;
    }

    public int getValue() {
        return this.value;
    }
}
