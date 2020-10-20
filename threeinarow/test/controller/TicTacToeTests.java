package controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TicTacToeTests {

    private RowGameController gameController;
    @Before
    public void SetUp() {
        gameController = new RowGameControllerTicTacToe(3, 3);
        gameController.gameModel.setBlocksData(1,1, "X");
        gameController.gameModel.setIsLegal(1,1, false);
        gameController.gameModel.setBlocksData(0,1, "O");
        gameController.gameModel.setIsLegal(0,1, false);
        gameController.gameModel.setBlocksData(2,2, "X");
        gameController.gameModel.setIsLegal(2,2, false);
        gameController.gameModel.setBlocksData(1,2, "X");
        gameController.gameModel.setIsLegal(1,2, false);
    }

    @After
    public void TearDown() {
        gameController = null;
    }

    @Test
    public void testReset() {
        gameController.resetGame();
        for(int i=0; i<gameController.gameModel.rows; i++) {
            for(int j=0; j<gameController.gameModel.columns; j++) {
                assertEquals(gameController.gameModel.blocksData[i][j].getContents(), "");
                assertEquals(gameController.gameModel.blocksData[i][j].getIsLegalMove(), true);
            }
        }
    }

    @Test
    public void testIllegalMove() {
        this.gameController.gameModel.setPlayer("2");
        gameController.move(this.gameController.gameModel, 2,2);
        // currently set to "X" by player 1, should remain so if player 2 tries to overwrite
        assertEquals(gameController.gameModel.blocksData[2][2].getContents(), "X");
    }

    @Test
    public void testLegalMove() {
        gameController.move(this.gameController.gameModel, 0,2);
        // currently not set to anything, should get set
        assertEquals(gameController.gameModel.blocksData[0][2].getContents(), "X");
    }

    @Test
    public void OnePlayerWins() {
        gameController.resetGame();
        gameController.move(this.gameController.gameModel, 2, 0);
        gameController.move(this.gameController.gameModel, 2, 1);
        gameController.move(this.gameController.gameModel, 1, 0);
        gameController.move(this.gameController.gameModel, 1, 1);
        gameController.move(this.gameController.gameModel, 0, 0);

        assertEquals(this.gameController.gameModel.getFinalResult(), "Player 1 wins!");
    }

    @Test
    public void EndsInATie() {
        gameController.resetGame();
        gameController.move(this.gameController.gameModel, 2, 0);
        gameController.move(this.gameController.gameModel, 2, 1);
        gameController.move(this.gameController.gameModel, 0, 1);
        gameController.move(this.gameController.gameModel, 0, 0);
        gameController.move(this.gameController.gameModel, 1, 0);
        gameController.move(this.gameController.gameModel, 1, 1);
        gameController.move(this.gameController.gameModel, 2, 2);
        gameController.move(this.gameController.gameModel, 1, 2);
        gameController.move(this.gameController.gameModel, 0, 2);

        assertEquals(this.gameController.gameModel.getFinalResult(), "Game ends in a draw");
    }

}
