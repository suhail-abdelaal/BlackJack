package blackjack;

import java.util.Scanner;

public class BlackJack {
    static Game blackJack = new Game();
    static Player[] player;
    static Scanner in;
    static GUI gui = new GUI();
    public static void main(String[] args) {
        blackJack.generateCards();
        setPlayers();
        gui.runGUI(
                blackJack.getCards().toArray(new Card[0]),
                player[0].getCards().toArray(new Card[0]),
                player[1].getCards().toArray(new Card[0]),
                player[2].getCards().toArray(new Card[0]),
                player[3].getCards().toArray(new Card[0])
        );

        play();
        blackJack.gameStatus(player);
    }

    public static void setPlayers() {
        System.out.print("Enter Player1's Name: ");
        String p1 = in.nextLine();
        System.out.print("Enter Player2's Name: ");
        String p2 = in.nextLine();
        System.out.print("Enter Player3's Name: ");
        String p3 = in.nextLine();
        blackJack.setPlayersInfo(p1, p2, p3);
        System.out.println(player[0].getScore());
        System.out.println(player[1].getScore());
        System.out.println(player[2].getScore());
        System.out.println(player[3].getScore());
    }

    public static void play() {
        for(int i = 0; i < 3; ++i) {
            System.out.print('╔');

            int j;
            for(j = 0; j < player[i].getName().length() + 12; ++j) {
                System.out.print('═');
            }

            System.out.println('╗');
            System.out.print(i + 1);
            System.out.println(' ' + player[i].getName().toUpperCase() + "'s turn..." + " ║");
            System.out.print('╚');

            for(j = 0; j < player[i].getName().length() + 12; ++j) {
                System.out.print('═');
            }

            System.out.println('╝');
            setInput(i);
        }

        blackJack.updateMaxScore();
        if (player[3].getScore() <= blackJack.getMaxScore()) {
            dealerDrawing();
        }

    }

    public static void setInput(int i) {
        while(true) {
            System.out.println("****** Enter your choice ******");
            System.out.println("1) Hit \n2) Stand");
            if (in.hasNextInt()) {
                int input = in.nextInt();
                if (input == 1) {
                    System.out.println();
                    System.out.println("**Dealer is giving " + player[i].getName().toUpperCase() + " a card...");
                    Card drawnCard = blackJack.drawRandomCard();
                    System.out.println("#Drawn card's value is: " + drawnCard.getValue());
                    player[i].addCard(drawnCard);
                    gui.updatePlayerHand(drawnCard, i);
                    player[i].setScore(drawnCard.getValue());

                    if (player[i].isBusted()) {
                        System.out.println("~" + player[i].getName().toUpperCase() + " is Busted with '" + player[i].getScore() + "' in hand");
                        System.out.println("---------------------------------------------------------------");
                        System.out.println();
                        return;
                    } else if (player[i].hasWon()) {
                        System.out.println("♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠");
                        System.out.println(player[i].getName().toUpperCase() + " has a " + "\ud835\udcd1\ud835\udcdb\ud835\udcd0\ud835\udcda \ud835\udcd9\ud835\udcd0\ud835\udcd2\ud835\udcda");
                        System.out.println("♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠");
                        System.out.println();
                        return;
                    }

                    System.out.println("~" + player[i].getName().toUpperCase() + " has: " + player[i].getScore());
                    System.out.println();

                } else if (input == 2) {
                    System.out.print('╭');

                    int j;
                    for(j = 0; j < player[i].getName().length() + 18; ++j) {
                        System.out.print('╴');
                    }

                    System.out.println('╮');
                    System.out.println("| " + player[i].getName().toUpperCase() + " stands with: " + player[i].getScore() + " |");
                    System.out.print('╰');

                    for(j = 0; j < player[i].getName().length() + 18; ++j) {
                        System.out.print('╴');
                    }

                    System.out.println('╯');
                    System.out.println("---------------------------------------------------------------");
                    System.out.println();
                    return;
                } else {
                    System.out.println("Invalid input, try again!");
                    System.out.println();
                    setInput(i);
                }
            }
        }
    }

    public static void dealerDrawing() {
        System.out.println("**Dealer is drawing cards");

        while (player[3].getScore() <= blackJack.getMaxScore()){

            Card drawnCard = blackJack.drawRandomCard();
            System.out.println("#Drawn card's value is: " + drawnCard.getValue());
            player[3].addCard(drawnCard);
            gui.updateDealerHand(drawnCard, (Card[])blackJack.getCards().toArray(new Card[0]));
            player[3].setScore(drawnCard.getValue());
            if (player[3].isBusted()) {
                System.out.println('~' + player[3].getName().toUpperCase() + " is Busted with '" + player[3].getScore() + "' in hand");
                System.out.println();
                return;
            } else if (player[3].hasWon()) {
                System.out.println("♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠");
                System.out.println(player[3].getName().toUpperCase() + " has a " + "\ud835\udcd1\ud835\udcdb\ud835\udcd0\ud835\udcda \ud835\udcd9\ud835\udcd0\ud835\udcd2\ud835\udcda");
                System.out.println("♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠");
                System.out.println();
                return;
            }
        }
    }

    static {
        player = blackJack.getPlayer();
        in = new Scanner(System.in);
    }
}
