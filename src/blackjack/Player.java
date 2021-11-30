package blackjack;

import java.util.ArrayList;

public class Player {
    private String name;
    private int score;
    private final ArrayList<Card> cards;
    private boolean hasWon;
    private boolean isBusted;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
        this.cards = new ArrayList();
        this.hasWon = false;
        this.isBusted = false;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score += score;
        if (this.score == 21) {
            this.hasWon = true;
        }

        if (this.score > 21) {
            this.isBusted = true;
        }

    }

    public boolean hasWon() {
        return this.hasWon;
    }

    public void setWon(boolean blackJack) {
        this.hasWon = blackJack;
    }

    public boolean isBusted() {
        return this.isBusted;
    }

    public void setBusted(boolean busted) {
        this.isBusted = busted;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
