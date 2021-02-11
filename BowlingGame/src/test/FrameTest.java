package test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import main.Frame;
import org.junit.Before;
import org.junit.Test;

public class FrameTest {

    private Frame frame;

    private Frame prevFrame;

    @Before
    public void setUp(){
        int prevId = 1;
        int id = 2;
        frame = new Frame(id);
        prevFrame = new Frame(prevId);
    }

    @Test
    public void canCheckForSpare_WhenRoll2_FrameScore10() {
        frame.setRollNumber(2);
        frame.setFrameScore(10);
        assertThat(frame.checkSpare(), is(true));
    }

    @Test
    public void canCheckForStrike_WhenRoll1_FrameScore10() {
        frame.setRollNumber(1);
        frame.setFrameScore(10);
        assertThat(frame.checkStrike(), is(true));
    }

    @Test
    public void canUpdatePreviousFrameSpareOrStrikeScore_WhenNewScoreGiven(){
        int score = 10;
        frame.updatePrevSpareOrStrikeScore(score, prevFrame);
        assertThat(prevFrame.getCumulativeScore(), is(10));
        assertThat(prevFrame.getTotalScore(), is(10));
    }

    @Test
    public void canAddFrameScore_WhenNewScoreGiven_PrevCumulativeGiven(){
        int score = 5;
        prevFrame.setCumulativeScore(28);
        frame.addFrameScore(score, prevFrame);
        assertThat(frame.getCumulativeScore(), is(33));

    }

    @Test
    public void canSwitchStrikeTrue_WhenNewScoreGiven_PrevCumulativeGiven(){
        int score = 10;
        frame.setRollNumber(1);
        prevFrame.setCumulativeScore(28);
        frame.addFrameScore(score, prevFrame);
        assertThat(frame.isStrike(), is(true));
    }

    @Test
    public void canSwitchSpareTrue_WhenNewScoreGiven_PrevCumulativeGiven(){
        int score = 10;
        frame.setRollNumber(2);
        prevFrame.setCumulativeScore(28);
        frame.addFrameScore(score, prevFrame);
        assertThat(frame.isSpare(), is(true));
    }

}
