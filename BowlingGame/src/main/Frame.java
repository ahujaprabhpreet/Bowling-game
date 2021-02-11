package main;

public class Frame {

    //Fields
    private int id;
    private int[] rolls;
    private int rollNumber;
    private boolean isSpare;
    private boolean isStrike;
    private int frameScore;
    private int totalScore;
    private int cumulativeScore;

    //Constructor
    public Frame(int id) {
        this.id = id;
        this.rolls = new int[3];
        this.rollNumber = 1;
        this.isSpare = false;
        this.isStrike = false;
        this.frameScore = 0;
        this.totalScore = 0;
        this.cumulativeScore = 0;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getRolls() {
        return rolls;
    }

    public void setRolls(int[] rolls) {
        this.rolls = rolls;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public void setFrameScore(int frameScore) {
        this.frameScore = frameScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getCumulativeScore() {
        return cumulativeScore;
    }

    public void setCumulativeScore(int cumulativeScore) {
        this.cumulativeScore = cumulativeScore;
    }

    //Methods
    public void addFrameScore(int score, Frame prevFrame){

        this.frameScore += score;
        this.totalScore += score;

        if(checkSpare()) this.isSpare = true;

        if(checkStrike()) this.isStrike = true;

        if(prevFrame != null)
            updatePrevFrame(score, prevFrame);

        this.calculateCumulativeScore(prevFrame);

        this.rollNumber++;

    }

    private void updatePrevFrame(int score, Frame prevFrame){

        if(prevFrame.isSpare() && rollNumber == 1){
            updatePrevSpareOrStrikeScore(score, prevFrame);
        }

        if(prevFrame.isStrike() && rollNumber >= 1){
            updatePrevSpareOrStrikeScore(score, prevFrame);
        }

        calculateCumulativeScore(prevFrame);

    }

    public boolean checkSpare(){
        if(frameScore == 10 && rollNumber == 2){
            return true;
        }
        return false;
    }

    public boolean checkStrike(){
        if(frameScore == 10 && rollNumber == 1){
            return true;
        }
        return false;
    }

    public void updatePrevSpareOrStrikeScore(int score, Frame prevFrame){

        prevFrame.setTotalScore(prevFrame.getTotalScore() + score);
        prevFrame.setCumulativeScore(prevFrame.getCumulativeScore() + score);

    }

    private void calculateCumulativeScore(Frame prevFrame){
        this.cumulativeScore = this.totalScore;

        if(prevFrame != null){
            this.cumulativeScore += prevFrame.cumulativeScore;
        }
    }
}