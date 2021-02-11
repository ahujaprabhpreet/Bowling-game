package main;

import java.util.Scanner;

public class Runner {

    public void startGame(){

        System.out.println("\nBOWLING SCORER\n");

        System.out.println("# Enter the number of players:");
        Scanner in = new Scanner(System.in);

        int noOfPlayers = in.nextInt();

        while(noOfPlayers < 1){
            System.out.println("\n!!! Invalid Input! Please enter a valid number of players!");
            noOfPlayers = in.nextInt();
        }

        Game game = new Game(noOfPlayers);

        for(int i = 0; i < 10; i++){

            for(int p = 0; p < noOfPlayers; p++){

                System.out.println("\n# Enter Pins knocked in 1st roll of " + (i+1) + " frame by Player " + (p+1) );
                int score1 = in.nextInt();

                while(score1 > 10 || score1 < 0) {
                    System.out.println("\n!!! Invalid Score! Please enter a valid Score!");
                    System.out.println("\n# Enter Pins knocked in 1st roll of " + (i+1) + " frame by Player " + (p+1) );
                    score1 = in.nextInt();

                }

                Player player = game.getPlayers()[p];
                Frame frame= player.getFrames()[i];

                frame.addFrameScore(score1, i == 0 ? null : player.getFrames()[i-1]);

                if(frame.isStrike()) {
                    System.out.println("\n*** It's a STRIKE ***\n ");

                    if(i < 9) {
                        System.out.println("\nPlayer " + (p+1) + " Scores " + " || Frame Score: " + frame.getFrameScore() +
                                " || Cumulative Score: " + frame.getCumulativeScore());
                        break;
                    }
                }

                System.out.println("\n# Enter Pins knocked in 2nd roll of " + (i+1) + " frame by Player " + (p+1) );
                int score2 = in.nextInt();

                while(score2 < 0 || (score1 + score2 > 10 && i < 9)) {
                    System.out.println("\n!!! Invalid Score! Please enter a valid Score!");
                    System.out.println("\n# Enter Pins knocked in 2nd roll of " + (i+1) + " frame by Player " + (p+1) );
                    score2 = in.nextInt();
                }

                frame.addFrameScore(score2, i == 0 ? null : player.getFrames()[i-1]);

                if(frame.isSpare()) {
                    System.out.println("\n*** It's a SPARE *** ");
                }

                if(score2 == 10) {
                    System.out.println("\n*** It's a STRIKE ***");
                }

                if(i == 9 && (frame.isSpare() || frame.isStrike())){
                    System.out.println("\n# Enter Pins knocked in 3rd roll of " + (i+1) + " frame by Player " + (p+1) );
                    int score3 = in.nextInt();
                    while(score3 < 0 || score3 > 10) {
                        System.out.println("\n!!! Invalid Score! Please enter a valid Score!");
                        System.out.println("\n# Enter Pins knocked in 3rd roll of " + (i+1) + " frame by Player " + (p+1) );
                        score3 = in.nextInt();
                    }
                    frame.addFrameScore(score3, player.getFrames()[i-1]);
                }

                System.out.println("\nPlayer " + (p+1) + " Scores " + " || Frame Score: " + frame.getFrameScore() +
                        " || Cumulative Score: " + frame.getCumulativeScore());

            }
        }

        Player winner = calculateWinner(game);
        System.out.println("\nWINNER OF THE GAME: PLAYER  " + (winner.getId()+1));

    }

    public Player calculateWinner(Game game){
        Player winner = null;
        int max = Integer.MIN_VALUE;

        for(Player player: game.getPlayers()){
            if(player.getFrames()[9].getCumulativeScore() > max){
                max = player.getFrames()[9].getCumulativeScore();
                winner = player;
            }
        }
        return winner;
    }
}
