import java.util.Random;

public class Game {

    // Attribute
    public static final String bj = "\ud835\udcd1\ud835\udcdb\ud835\udcd0\ud835\udcda \ud835\udcd9\ud835\udcd0\ud835\udcd2\ud835\udcda";
    private static int maxScore;
    public Player[] player;
    public Card[] cards;

    // Constructors
    public Game() {
        maxScore = 0;
        player = new Player[4];
        cards = new Card[52];
    }

    // Functions
    public void generateCards() {
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                int value = (j < 10) ? j + 1 : 10;
                cards[index++] = new Card(i, j, value);
            }
        }
    }

    public Card drawRandomCard() {
        Random rand = new Random();

        while (true) {
            int randChoice = rand.nextInt(52);
            if (cards[randChoice] == null)
                continue;

            Card drawnCard = cards[randChoice];
            cards[randChoice] = null;
            return drawnCard;
        }
    }

    public void setPlayersInfo(String p1, String p2, String p3, String p4) {
        player[0] = new Player(p1);
        player[0].addCard(drawRandomCard());
        player[0].addCard(drawRandomCard());

        player[1] = new Player(p2);
        player[1].addCard(drawRandomCard());
        player[1].addCard(drawRandomCard());

        player[2] = new Player(p3);
        player[2].addCard(drawRandomCard());
        player[2].addCard(drawRandomCard());

        player[3] = new Player(p4);
        player[3].addCard(drawRandomCard());
        player[3].addCard(drawRandomCard());
    }

    public void updateMaxScore() {
        for (int i = 0; i < 4; ++i) {
            if (!this.player[i].isBusted() && this.player[i].getScore() >= maxScore)
                maxScore = this.player[i].getScore();
        }
    }

    public void gameStatus(Player[] p) {
        this.updateMaxScore();
        int c = 0;
        Player winner = new Player("");
        for (int i = 0; i < 4; ++i) {
            if (p[i].getScore() >= maxScore && !p[i].isBusted()) {
                c++;
                winner = p[i];
            }
        }

        boolean isPush = c > 1;
        if (isPush) {
            System.out.println("It's a Push.");
        } else {
            if (maxScore == 21) {
                System.out.println(winner.getName().toUpperCase() + " won with a BlackJack.");
            } else {
                System.out.println(winner.getName().toUpperCase() + " won with '" + maxScore + "' in hand.");
            }
        }
    }

    // Setters & Getters
    public Player[] getPlayer() {
        return this.player;
    }

    public Card[] getCards() {
        return cards;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
