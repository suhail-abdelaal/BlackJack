public class Player {

    // Attributes
    private String name;
    private int score;
    private Card[] playerCards;
    private int index;
    private boolean hasWon;
    private boolean isBusted;

    // Constructors
    public Player(String name) {
        this.name = name;
        playerCards = new Card[11];
        this.index = 0;
        this.hasWon = false;
        this.isBusted = false;
    }

    // Setters & Getters
    public int getScore() {
        return this.score;
    }


    public void setName(String name) {
        this.name = name;
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
        this.playerCards[index++] = card;
        this.score+=card.getValue();
        if (this.score == 21) {
            this.hasWon = true;
        }

        if (this.score > 21) {
            this.isBusted = true;
        }
    }

    public String getName() {
        return this.name;
    }

    public Card[] getPlayerCards() {
        return playerCards;
    }
}
