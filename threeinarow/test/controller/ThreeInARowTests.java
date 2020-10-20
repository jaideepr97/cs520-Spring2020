package controller;

import model.RowGameModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class ThreeInARowTests {

    private RowGameModel gameModel;
    private RowGameController gameController;
    @Before
    public void resetSetUp() {
        gameController = new RowGameControllerThreeInARow(3, 3);
        gameController.gameModel.setBlocksData(2,1, "X");
        gameController.gameModel.setIsLegal(2,1, false);
        gameController.gameModel.setIsLegal(1, 1, true);
        gameController.gameModel.setBlocksData(2,0, "O");
        gameController.gameModel.setIsLegal(2,0, false);
        gameController.gameModel.setBlocksData(1,0, "X");
        gameController.gameModel.setIsLegal(1,0, false);
        gameController.gameModel.setIsLegal(0, 0, true);
    }

    @After
    public void resetTearDown() {
        gameController = null;
    }

    @Test
    public void testReset() {
        gameController.resetGame();
        for(int i=0; i<gameController.gameModel.rows; i++) {
            for(int j=0; j<gameController.gameModel.columns; j++) {
                assertEquals(gameController.gameModel.blocksData[i][j].getContents(), "");
                if(i == gameController.gameModel.rows - 1) {
                    assertEquals(gameController.gameModel.blocksData[i][j].getIsLegalMove(), true);
                } else {
                    assertEquals(gameController.gameModel.blocksData[i][j].getIsLegalMove(), false);
                }
            }
        }
    }

    @Test
    public void testIllegalMove() {
        gameController.move(this.gameController.gameModel, 1,2);
        // should not get set as [2,2] has currently not been played
        assertEquals(gameController.gameModel.blocksData[1][2].getContents(), "");
    }

    @Test
    public void testLegalMove() {
        gameController.move(this.gameController.gameModel, 0,0);
        // should get set as [1,0] has been played
        assertEquals(gameController.gameModel.blocksData[0][0].getContents(), "X");
    }

    @Test
    public void EndsInATie() {
        gameController.resetGame();
        gameController.move(this.gameController.gameModel, 2, 0);
        gameController.move(this.gameController.gameModel, 2, 1);
        gameController.move(this.gameController.gameModel, 1, 0);
        gameController.move(this.gameController.gameModel, 1, 1);
        gameController.move(this.gameController.gameModel, 0, 1);
        gameController.move(this.gameController.gameModel, 0, 0);
        gameController.move(this.gameController.gameModel, 2, 2);
        gameController.move(this.gameController.gameModel, 1, 2);
        gameController.move(this.gameController.gameModel, 0, 2);

        assertEquals(this.gameController.gameModel.getFinalResult(), "Game ends in a draw");
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

}
