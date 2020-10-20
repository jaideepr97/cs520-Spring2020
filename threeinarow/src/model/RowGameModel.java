package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RowGameModel
{
    public static final String GAME_END_NOWINNER = "Game ends in a draw";

    public RowBlockModel[][] blocksData;
    public enum Strategy { ThreeInARow, TicTacToe};
    private PropertyChangeSupport changes;


    /**
     * The current player taking their turn
     */
    public String player = "1";
    public int movesLeft;
    public int totalMoves;
    public int rows;
    public int columns;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy strategy;


    private String finalResult = null;


    public RowBlockModel[][] getBlocksData() {
        return blocksData;
    }

    public RowGameModel(int rows, int columns) {
	    super();
        blocksData = new RowBlockModel[rows][columns];
        changes = new PropertyChangeSupport(this);


        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
            blocksData[row][col] = new RowBlockModel(this);
            } // end for col
        } // end for row

        this.rows = rows;
        this.columns = columns;
        this.totalMoves = rows * columns;
        this.movesLeft = this.totalMoves;
    }

    public String getFinalResult() {
	    return this.finalResult;
    }

    public String getPlayer() {
        return player;
    }

    public void setBlocksData(int row, int column, String content) {
        String oldContent = this.blocksData[row][column].getContents();
        this.blocksData[row][column].setContents(content);
        changes.firePropertyChange("Content", oldContent, content);
    }

    public void setIsLegal(int row, int column, boolean isLegal) {
        boolean oldIsLegal = this.blocksData[row][column].getIsLegalMove();
        this.blocksData[row][column].setIsLegalMove(isLegal);
        changes.firePropertyChange("isLegal", oldIsLegal, isLegal);
    }

    public void setPlayer(String player) {
        String oldPlayer = this.player;
        this.player = player;
        changes.firePropertyChange("player", oldPlayer, player);
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
        changes.firePropertyChange("finalResult", null, finalResult);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }
    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }
}
