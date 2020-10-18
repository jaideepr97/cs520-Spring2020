package model;


public class RowGameModel 
{
    public static final String GAME_END_NOWINNER = "Game ends in a draw";

    public RowBlockModel[][] blocksData;
    public enum Strategy { ThreeInARow, TicTacToe};

    /**
     * The current player taking their turn
     */
    public String player = "1";
    public int movesLeft;
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


    public RowGameModel(Strategy strategy, int rows, int columns) {
	    super();
        blocksData = new RowBlockModel[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
            blocksData[row][col] = new RowBlockModel(this);
            } // end for col
        } // end for row

        this.strategy = strategy;
        this.rows = rows;
        this.columns = columns;
        this.movesLeft = rows * columns;
    }

    public String getFinalResult() {
	return this.finalResult;
    }

    public void setFinalResult(String finalResult) {
	this.finalResult = finalResult;
    }
}
