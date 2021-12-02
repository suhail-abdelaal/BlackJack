package blackjack;


public class Player {

    // Attributes
    private String name;
    private int score;
    private Card[] playerCards= new Card[11];
    private int index;
    private boolean hasWon;
    private boolean isBusted;

    // Constructors
    public Player(String name) {
        this.name = name;
        this.index = 0;
        this.hasWon = false;
        this.isBusted = false;
        for(int i=0;i<11;i++)
        {
            playerCards[i]=new Card(0,0,0);
        }
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
