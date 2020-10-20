import controller.RowGameController;
import controller.RowGameControllerThreeInARow;
import controller.SelectStrategy;


public class RowGameApp 
{
    /**                                                                             
     * Starts a new game in the GUI.
     */
    public static void main(String[] args) {
        int rows = Integer.parseInt(args[0]);
        int columns = Integer.parseInt(args[1]);
        if( rows < 0 || columns <0 || rows != columns ) {
            System.out.println("Invalid dimensions");
        }
        else {
//            RowGameController game = new RowGameController(rows, columns);
//            game.startUp();
            RowGameController defaultStrategy = new RowGameControllerThreeInARow(rows, columns);
            SelectStrategy selectStrategy = new SelectStrategy(defaultStrategy);
            selectStrategy.startStrategy(defaultStrategy);
        }

    }
}
