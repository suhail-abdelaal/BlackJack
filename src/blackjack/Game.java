package blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static ArrayList<Player> winner;
    public static final String bj = "\ud835\udcd1\ud835\udcdb\ud835\udcd0\ud835\udcda \ud835\udcd9\ud835\udcd0\ud835\udcd2\ud835\udcda";
    private static int maxScore;
    private final Player[] player;
    private final ArrayList<Card> cards;

    public Game() {
        winner = new ArrayList();
        this.player = new Player[4];
        this.cards = new ArrayList();
        maxScore = 0;
    }

    public void generateCards() {
        int rankCounter = 0;
        int suitCounter = 0;

        while(suitCounter < 4) {
            if (rankCounter >= 13) {
                rankCounter = 0;
                ++suitCounter;
            }

            int v = rankCounter < 10 ? rankCounter + 1 : 10;
            this.cards.add(new Card(suitCounter, rankCounter++, v));
        }

    }

    public Card drawRandomCard() {
        Random rand = new Random();
        int randChoice = rand.nextInt(this.cards.size());
        Card drawnCard = (Card)this.cards.get(randChoice);
        this.cards.remove(randChoice);
        return drawnCard;
    }

    public void setPlayersInfo(String p1, String p2, String p3) {
        this.player[0] = new Player(p1, this.drawRandomCard().getValue() + this.drawRandomCard().getValue());
        this.player[1] = new Player(p2, this.drawRandomCard().getValue() + this.drawRandomCard().getValue());
        this.player[2] = new Player(p3, this.drawRandomCard().getValue() + this.drawRandomCard().getValue());
        this.player[3] = new Player("Dealer", this.drawRandomCard().getValue() + this.drawRandomCard().getValue());
    }

    public void updateMaxScore() {
        for(int i = 0; i < 4; ++i) {
            if (!this.player[i].isBusted() && this.player[i].getScore() >= maxScore) {
                maxScore = this.player[i].getScore();
                winner.clear();
                winner.add(this.player[i]);
            }
        }

    }

    public void gameStatus(Player[] p) {
        this.updateMaxScore();
        int c = 0;

        for(int i = 0; i < 4; ++i) {
            if (p[i].getScore() >= maxScore && !p[i].isBusted()) {
                ++c;
            }
        }

        boolean isPush = c > 1;
        if (isPush) {
            System.out.println("It's a Push.");
        } else {
            if (maxScore == 21) {
                System.out.println(((Player)winner.get(0)).getName().toUpperCase() + " won with a " + "\ud835\udcd1\ud835\udcdb\ud835\udcd0\ud835\udcda \ud835\udcd9\ud835\udcd0\ud835\udcd2\ud835\udcda" + '.');
            } else {
                System.out.println(((Player)winner.get(0)).getName().toUpperCase() + " won with '" + maxScore + "' in hand.");
            }

        }
    }

    public Player[] getPlayer() {
        return this.player;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
