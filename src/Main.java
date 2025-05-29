import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        // Create the game logic instance (GameBoard handles the logic and the GUI)
        GameBoard game = new GameBoard();

        // No need for the text-based game loop since GUI now handles actions via buttons
        // The game loop and interactions are handled by the ActionListeners in the GameBoard class. dAS
    }
}
