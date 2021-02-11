package test;

import main.Game;
import main.Runner;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class RunnerTest {

    private Game game;

    private Runner runner;

    @Before
    public void setUp(){
        int noOfPlayers = 2;
        game = new Game(noOfPlayers);
        runner = new Runner();
    }

    @Test
    public void canDecideWinner_WhenCumulativeScoresGiven(){
        game.getPlayers()[0].getFrames()[9].setCumulativeScore(80);
        game.getPlayers()[1].getFrames()[9].setCumulativeScore(90);
        assertThat(runner.calculateWinner(game).getId(), is(1));

    }
}

