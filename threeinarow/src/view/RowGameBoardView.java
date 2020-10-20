package view;

import controller.RowGameController;
import model.RowGameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class RowGameBoardView implements RowGameView
{
    public JButton[][] blocks;
    public JPanel gamePanel = new JPanel(new FlowLayout());

    
    public RowGameBoardView(RowGameController gameController, int rows, int columns) {
	super();
        blocks = new JButton[rows][columns];
        JPanel game = new JPanel(new GridLayout(rows, columns));
        gamePanel.add(game, BorderLayout.CENTER);

        gameController.gameModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                update(gameController.gameModel);
            }
        });
	
       // Initialize a JButton for each cell of the 3x3 game board.
        for(int row = 0; row<rows; row++) {
            for(int column = 0; column<columns ;column++) {
                blocks[row][column] = new JButton();
                blocks[row][column].setPreferredSize(new Dimension(75,75));
                game.add(blocks[row][column]);
                int finalRow = row;
                int finalColumn = column;
                blocks[row][column].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
			            gameController.makeMove((JButton)e.getSource(), finalRow, finalColumn);
                    }
                });
            }
        }	
    }

    /**
     * Updates the game view after the game model
     * changes state.
     *
     * @param gameModel The current game model
     */

    // Parameterizing loop variables to address identified issue #2
    public void update(RowGameModel gameModel) {
        for (int row = 0; row < gameModel.rows; row++) {
            for (int column = 0; column < gameModel.columns; column++) {
                this.updateBlock(gameModel, row, column);
            } // end for col
        } // end for row
    }

    /**
     * Updates the block view at the given row and column 
     * after the game model changes state.
     *
     * @param gameModel The game model
     * @param row The row that contains the block
     * @param col The column that contains the block
     */
    protected void updateBlock(RowGameModel gameModel, int row, int col) {
        blocks[row][col].setText(gameModel.blocksData[row][col].getContents());
        blocks[row][col].setEnabled(gameModel.blocksData[row][col].getIsLegalMove());
    }
}
