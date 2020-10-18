import controller.RowGameController;
import model.RowGameModel;


public class RowGameApp 
{
    /**                                                                             
     * Starts a new game in the GUI.
     */
    public static void main(String[] args) {
        int rows = Integer.parseInt(args[0]);
        int columns = Integer.parseInt(args[1]);
        if( rows < 0 || columns <0 || rows != columns ) {
            System.out.println("Invalid dimentions");
        }
        else {
            RowGameController game = new RowGameController(RowGameModel.Strategy.ThreeInARow, rows, columns);
            game.startUp();
        }

    }
}
