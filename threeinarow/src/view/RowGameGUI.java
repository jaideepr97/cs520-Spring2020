package view;

import controller.RowGameController;
import controller.RowGameControllerThreeInARow;
import controller.RowGameControllerTicTacToe;
import controller.SelectStrategy;
import model.RowGameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RowGameGUI implements RowGameView
{
    public JFrame gui = new JFrame("Three in a Row");
    public RowGameBoardView gameBoardView;
    public JButton reset = new JButton("Reset");
    public JButton threeInARow = new JButton("3-in-a-row");
    public JButton ticTacToe = new JButton("tic-tac-toe");
    public RowGameStatusView gameStatusView;
    
    private RowGameController gameController;
    private SelectStrategy selectStrategy;
    private int rows;
    private int columns;


    /**
     * Creates a new game initializing the GUI.
     */

    // Separating GUI initialization from business logic through MVC addressed identified issue #1
    public RowGameGUI(RowGameController gameController, int rows, int columns) {
	    this.gameController = gameController;
	    this.rows = rows;
	    this.columns = columns;
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

	gameBoardView = new RowGameBoardView(this.gameController, rows, columns);
        JPanel gamePanel = gameBoardView.gamePanel;

        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);
        options.add(threeInARow);
        options.add(ticTacToe);

	gameStatusView = new RowGameStatusView(this.gameController);
        JPanel messages = gameStatusView.messages;

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectStrategy.executeStrategy(gameController.gameModel);
            }
        });

        threeInARow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectStrategy = new SelectStrategy(new RowGameControllerThreeInARow(rows, columns));
                selectStrategy.executeStrategy(gameController.gameModel);
            }
        });

        ticTacToe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectStrategy = new SelectStrategy(new RowGameControllerTicTacToe(rows, columns));
                selectStrategy.executeStrategy(gameController.gameModel);
            }
        });
    }

    /**
     * Updates the game view after the game model
     * changes state.
     *
     * @param gameModel The current game model
     */
    public void update(RowGameModel gameModel) {
	gameBoardView.update(gameModel);

	gameStatusView.update(gameModel);
    }
}
