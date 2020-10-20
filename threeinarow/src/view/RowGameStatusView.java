package view;

import controller.RowGameController;
import model.RowGameModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class RowGameStatusView implements RowGameView
{
    public JTextArea playerturn = new JTextArea();
    protected JPanel messages = new JPanel(new FlowLayout());

    
    protected RowGameStatusView(RowGameController gameController) {
		super();
		gameController.gameModel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				update(gameController.gameModel);
			}
		});
		messages.setBackground(Color.white);
		messages.add(playerturn);
    }

    public void update(RowGameModel gameModel) {
		if (gameModel.getFinalResult() == null) {
			if (gameModel.player.equals("1")) {
				playerturn.setText("Player 1 to play 'X'");
			}
			else {
				playerturn.setText("Player 2 to play 'O'");
			}
		}
		else {
			playerturn.setText(gameModel.getFinalResult());
		}
    }
}
