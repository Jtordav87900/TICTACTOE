import javax.swing.*;
import java.awt.*;

public class GameBoard {
    JFrame frame;
    char player1 = 'X';
    char player2 = 'O';
    boolean turn = true;
    char[][] board = new char[3][3];
    JButton[][] board1 = new JButton[3][3];

    public GameBoard() {
        // Set up window
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a 3×3 grid panel
        JPanel panel = new JPanel(new GridLayout(3, 3));
        frame.add(panel);

        // Initialize both the logic board and the button board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '#';
                JButton btn = new JButton(" ");
                board1[i][j] = btn;
                final int x = i, y = j;

                // When clicked, place the current player's token
                btn.addActionListener(e -> {
                    char token = playerTurn();
                    if (placeToken(x + 1, y + 1)) {
                        // Show the token on the button
                        btn.setText(String.valueOf(token));

                        // Check for a win
                        if (WinCondition()) {
                            JOptionPane.showMessageDialog(frame, "Player " + token + " won!");
                        }
                        // Or check for a tie
                        else if (TieCondition()) {
                            JOptionPane.showMessageDialog(frame, "Game ended in a tie");
                        }
                        // Otherwise toggle turn for next click
                        else {
                            turn = !turn;
                        }
                    }
                });

                panel.add(btn);
            }
        }

        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    // Places token in logic board if spot is empty; does NOT toggle turn
    public boolean placeToken(int x, int y) {
        x--; y--;
        if (x < 0 || x > 2 || y < 0 || y > 2) return false;
        if (board[x][y] != '#') return false;
        board[x][y] = playerTurn();
        return true;
    }

    // Returns the char for the current player
    public char playerTurn() {
        return turn ? player1 : player2;
    }

    // True if no empty spots remain
    public boolean TieCondition() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '#')
                    return false;
        return true;
    }

    // True if any row, column, or diagonal has three matching non-‘#’ chars
    public boolean WinCondition() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '#' &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2])
                return true;
            if (board[0][i] != '#' &&
                    board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i])
                return true;
        }
        if (board[0][0] != '#' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2])
            return true;
        if (board[2][0] != '#' &&
                board[2][0] == board[1][1] &&
                board[1][1] == board[0][2])
            return true;

        return false;
    }
}
