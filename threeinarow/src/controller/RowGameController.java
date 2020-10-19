package controller;

import model.RowGameModel;
import view.RowGameGUI;

import javax.swing.*;


/**
 * Java implementation of the 3 in a row game, using the Swing framework.
 *
 * This quick-and-dirty implementation violates a number of software engineering
 * principles and needs a thorough overhaul to improve readability,
 * extensibility, and testability.
 */
public class RowGameController implements RowGameRulesStrategy{
    public static final String GAME_END_NOWINNER = "Game ends in a draw";

    public RowGameModel gameModel;
    public RowGameGUI gameView;


    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameController(RowGameModel.Strategy strategy, int rows, int columns) {
		gameModel = new RowGameModel(strategy, rows, columns);
		gameView = new RowGameGUI(this, rows, columns);
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
		gameModel.movesLeft = gameModel.movesLeft - 1;
		JButton block = gameView.gameBoardView.blocks[row][col];
		String player = gameModel.player;
		int movesLeft = gameModel.movesLeft;
		if(player.equals("1")) {
			// Check whether player 1 won
			gameModel.setBlocksData(row, col, "X");
			gameModel.setIsLegal(row, col, false);
			if(row > 0 && gameModel.blocksData[row-1][col].getContents().equals("")) {
				gameModel.setIsLegal(row-1, col, true);
			}
			gameModel.setPlayer("2");

			if(movesLeft < gameModel.totalMoves - gameModel.rows + 1) {
				if(isWin(this.gameModel)) {
					gameModel.setFinalResult("Player 1 wins!");
					endGame(this.gameModel);
				} else if (isTie(this.gameModel)) {
					gameModel.setFinalResult(GAME_END_NOWINNER);
				}
			}
		} else {
			// Check whether player 2 won
			gameModel.setBlocksData(row, col, "O");
			gameModel.setIsLegal(row, col, false);
			if(row > 0 && gameModel.blocksData[row-1][col].getContents().equals("")) {
				gameModel.setIsLegal(row-1, col, true);
			}
			gameModel.setPlayer("1");

			if(movesLeft < gameModel.totalMoves - gameModel.rows + 1) {
				if(isWin(this.gameModel)) {
					gameModel.setFinalResult("Player 2 wins!");
					endGame(this.gameModel);
				} else if (isTie(this.gameModel)) {
					gameModel.setFinalResult(GAME_END_NOWINNER);
				}
			}
		}
    }

    /**
     * Ends the game disallowing further player turns.
     */
    public void endGame(RowGameModel gameModel) {
		for(int row = 0;row<gameModel.rows ;row++) {
			for(int column = 0;column<gameModel.columns ;column++) {
				this.gameModel.setIsLegal(row, column, false);
			}
		}
    }

    /**
     * Resets the game to be able to start playing again.
     */
    public void reset(RowGameModel gameModel) {
        for(int row = 0;row<gameModel.rows;row++) {
            for(int column = 0;column<gameModel.columns;column++) {
                gameModel.blocksData[row][column].reset();
				// Enable the bottom row
				if(gameModel.strategy == RowGameModel.Strategy.ThreeInARow) {
					this.gameModel.setIsLegal(row, column, row == gameModel.rows-1);
				} else if (gameModel.strategy == RowGameModel.Strategy.TicTacToe) {
					gameModel.setIsLegal(row, column, true);
				}
            }
        }
        gameModel.setPlayer("1");
		gameModel.movesLeft = gameModel.rows * gameModel.columns;
		gameModel.setFinalResult(null);
    }

    public void resetGame() {
    	reset(this.gameModel);
	}

	public void resetGame(RowGameModel.Strategy strategy) {
    	this.gameModel.setStrategy(strategy);
    	reset(this.gameModel);
	}

	public boolean isWin(RowGameModel gameModel) {
    	int rows = gameModel.blocksData.length;
    	int columns = gameModel.blocksData[0].length;
    	boolean isWin = true;
    	for(int i=rows-1; i>=0; i--) {
    		isWin = true;
    		for(int j=0; j<columns; j++) {
    			if(gameModel.blocksData[i][j].getContents().equals("") || !gameModel.blocksData[i][j].getContents().equalsIgnoreCase(gameModel.blocksData[i][0].getContents())) {
    				isWin = false;
    				break;
				}
			}
			if(isWin)
				break;
		}
    	if(isWin) {
    		return true;
		}

		for(int j=0; j<columns; j++) {
			isWin = true;
			for(int i=rows-1; i>=0; i--) {
				if(gameModel.blocksData[i][j].getContents().equals("") || !gameModel.blocksData[i][j].getContents().equalsIgnoreCase(gameModel.blocksData[0][j].getContents())) {
					isWin = false;
					break;
				}
			}

			if(isWin)
				break;
		}
		if(isWin) {
			return true;
		}

		isWin = true;
		for(int i=1; i<rows; i++) {
			if(gameModel.blocksData[i][i].getContents().equals("") || !gameModel.blocksData[i][i].getContents().equalsIgnoreCase(gameModel.blocksData[i-1][i-1].getContents())) {
				isWin = false;
				break;
			}
		}
		if(isWin) {
			return true;
		}

		isWin = true;
		for(int i=rows-1; i>0; i--) {
			if(gameModel.blocksData[i][i].getContents().equals("") || !gameModel.blocksData[i][rows-1-i].getContents().equalsIgnoreCase(gameModel.blocksData[i-1][rows-i].getContents())) {
				isWin = false;
				break;
			}
		}
		if(isWin) {
			return true;
		}
    	return false;
	}
	public boolean isTie(RowGameModel gameModel) {
		if(gameModel.movesLeft == 0 && !isWin(gameModel))
    		return true;
		return false;
	}

}
