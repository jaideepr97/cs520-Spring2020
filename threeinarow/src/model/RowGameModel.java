package model;


public class RowGameModel 
{
    public static final String GAME_END_NOWINNER = "Game ends in a draw";

    public RowBlockModel[][] blocksData = new RowBlockModel[3][3];
    public enum Strategy { ThreeInARow, TicTacToe};

    /**
     * The current player taking their turn
     */
    public String player = "1";
    public int movesLeft = 9;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy strategy;


    private String finalResult = null;


    public RowGameModel(Strategy strategy) {
	    super();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
            blocksData[row][col] = new RowBlockModel(this);
            } // end for col
        } // end for row

        this.strategy = strategy;
    }

    public String getFinalResult() {
	return this.finalResult;
    }

    public void setFinalResult(String finalResult) {
	this.finalResult = finalResult;
    }
}
