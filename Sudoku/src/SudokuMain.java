import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L; // to prevent serial warning

    // private variables
    public GameBoardPanel board = new GameBoardPanel(this);
    JButton btnNewGame = new JButton("New Game");
    JLabel statusLabel = new JLabel("Remaining cells to guess: 81");

    // Constructor
    public SudokuMain() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(board, BorderLayout.CENTER);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Add a smaller "New Game" button
        JButton newGameBtn = new JButton("New Game");

        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // startTimer();
                board.newGame();
            }
        });
        buttonPanel.add(newGameBtn);

        cp.add(buttonPanel, BorderLayout.SOUTH);
        cp.add(statusLabel, BorderLayout.NORTH);

        // Initialize the game board to start the game
        board.newGame();

        pack(); // Pack the UI components, instead of using setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to handle window-closing
        setTitle("Sudoku");
        setVisible(true);
    }

    public void updateRemainingStatusLabel() {
        int remainingCells = board.getRemainingCellsToGuess();
        statusLabel.setText("Remaining Cells: " + remainingCells);
    }

}
