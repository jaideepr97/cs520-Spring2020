package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class ModelTests {
    private RowGameModel gameModel;

    @Before
    public void setUp() {
	    gameModel = new RowGameModel(3, 3);
    }

    @After
    public void tearDown() {
	    gameModel = null;
    }

    @Test
    public void testNewGame() {
        assertEquals ("1", gameModel.player);
        assertEquals (9, gameModel.movesLeft);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewBlockViolatesPrecondition() {
	    RowBlockModel block = new RowBlockModel(null);
    }
}
