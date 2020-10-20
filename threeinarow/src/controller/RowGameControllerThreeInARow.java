package controller;

import model.RowGameModel;
import view.RowGameGUI;
import javax.swing.*;

public class RowGameControllerThreeInARow extends RowGameController implements RowGameRulesStrategy {

    public static final String GAME_END_NOWINNER = "Game ends in a draw";
    public RowGameModel gameModel;
    public RowGameGUI gameView;

    public RowGameControllerThreeInARow(int rows, int columns) {
        super();
        this.gameModel = new RowGameModel(rows, columns);
        System.out.println(this.gameModel.totalMoves);
        this.gameView = new RowGameGUI(this, rows, columns);
        resetGame();
    }

    public RowGameModel getModel() {
        return this.gameModel;
    }

    public RowGameGUI getView() {
        return this.gameView;
    }

    public void startUp() {
        gameView.gui.setVisible(true);
    }

    public void makeMove(JButton block, int row, int col) {
        this.move(this.gameModel, row, col);
    }

    public void move(RowGameModel gameModel, int row, int col) {
        super.move(gameModel, row, col);
    }

    /**
     * Ends the game disallowing further player turns.
     */
    public void endGame(RowGameModel gameModel) {
        super.endGame(gameModel);
    }

    /**
     * Resets the game to be able to start playing again.
     */
    public void reset(RowGameModel gameModel) {
        for(int row = 0; row<gameModel.rows; row++) {
            for(int column = 0; column<gameModel.columns; column++) {
                gameModel.blocksData[row][column].reset();
                gameModel.setIsLegal(row, column, row == gameModel.rows-1);
            }
        }
        gameModel.setPlayer("1");
        gameModel.movesLeft = gameModel.rows * gameModel.columns;
        gameModel.setFinalResult(null);
    }

    public void resetGame() {
        reset(this.gameModel);
    }

    public boolean isWin(RowGameModel gameModel) {
        return super.isWin(gameModel);
    }
    public boolean isTie(RowGameModel gameModel) {
        return super.isTie(gameModel);
    }

}
