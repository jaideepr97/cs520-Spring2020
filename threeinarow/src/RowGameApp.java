import controller.RowGameController;
import model.RowGameModel;


public class RowGameApp 
{
    /**                                                                             
     * Starts a new game in the GUI.
     */
    public static void main(String[] args) {
	RowGameController game = new RowGameController(RowGameModel.Strategy.ThreeInARow);
	game.startUp();
    }
}
