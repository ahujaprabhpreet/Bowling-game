package main;

public class Game {

    //Fields
    private Player[] players;
    private int noOfPlayers;

    //Constructor
    public Game(int noOfPlayers) {
        this.players = new Player[noOfPlayers];
        this.noOfPlayers = noOfPlayers;

        for(int i = 0; i < noOfPlayers; i++){
            players[i] = new Player(i);
        }
    }

    //Getters and Setters
    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    public void setNoOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }
}
