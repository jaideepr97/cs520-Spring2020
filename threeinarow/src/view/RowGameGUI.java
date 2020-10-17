package view;

import controller.RowGameController;
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


    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameGUI(RowGameController gameController) {
	this.gameController = gameController;
	
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

	gameBoardView = new RowGameBoardView(this.gameController);
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
                gameController.resetGame();
            }
        });

        threeInARow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.resetGame(RowGameModel.Strategy.ThreeInARow);
            }
        });

        ticTacToe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.resetGame(RowGameModel.Strategy.TicTacToe);
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
