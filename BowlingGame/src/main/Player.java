package main;

public class Player {

    //Fields
    private int id;
    private Frame[] frames;

    //Constructor
    public Player(int id){
        this.id = id;
        this.frames = new Frame[10];

        for(int i = 0; i < 10; i++){
            frames[i] = new Frame(i);
        }
    }

    //Getters and Setters
    public Frame[] getFrames() {
        return frames;
    }

    public void setFrames(Frame[] frames) {
        this.frames = frames;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
