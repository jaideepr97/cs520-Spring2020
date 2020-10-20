package view;

import controller.RowGameController;
import controller.RowGameControllerTicTacToe;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class ViewTests {
    private RowGameController gameController;

    @Before
    public void SetUp() {
        gameController = new RowGameControllerTicTacToe(3, 3);
    }

    @After
    public void TearDown() {
        gameController = null;
    }

    @Test
    public void testUpdateBlock() {
        gameController.move(this.gameController.gameModel, 0,2);
        assertEquals(gameController.getView().gameBoardView.blocks[0][2].getText(), "X");
        assertEquals(gameController.getView().gameBoardView.blocks[0][2].isEnabled(), false);
    }

    @Test
    public void testWinnerStatus() {
        gameController.resetGame();
        gameController.move(this.gameController.gameModel, 2, 0);
        gameController.move(this.gameController.gameModel, 2, 1);
        gameController.move(this.gameController.gameModel, 1, 0);
        gameController.move(this.gameController.gameModel, 1, 1);
        gameController.move(this.gameController.gameModel, 0, 0);

        assertEquals(gameController.getView().gameStatusView.playerturn.getText(), "Player 1 wins!");
    }

}
