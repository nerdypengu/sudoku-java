/* KELOMPOK 10
 * Eugenia Indrawan - 5026221020
 * Ashila Mahdiyyah - 5026221148
 * Razi Alvaro Arman - 5026221168
 * 
*/

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

    public Timer timer;
    public int elapsedTime;
    public JLabel timerLabel;

    // Constructor
    public SudokuMain(int difficulty) {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        board.setDiff(difficulty);
        cp.add(board, BorderLayout.CENTER);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Add a smaller "New Game" button
        JButton newGameBtn = new JButton("New Game");

        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.newGame();
            }
        });
        buttonPanel.add(newGameBtn);

        JButton btnSolveGame = new JButton("Solve");
        btnSolveGame.addActionListener(e -> board.SolveGame());
        buttonPanel.add(btnSolveGame);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DiffMenu menu = new DiffMenu();
                menu.setVisible(true);
            }
        });
        buttonPanel.add(btnBack);

        timer = new Timer(1000, new TimerListener());
        timerLabel = new JLabel("Time: 00:00");
        buttonPanel.add(timerLabel);

        // Create a panel for Top Bar
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(statusLabel);
        topPanel.add(timerLabel);

        cp.add(buttonPanel, BorderLayout.SOUTH);
        cp.add(topPanel, BorderLayout.NORTH);

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

    public class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime++; // Increment elapsed time
            updateTimerLabel(); // Update timer label
        }
    }

    public void updateTimerLabel() {
        int minutes = elapsedTime / 60; // Calculate minutes
        int seconds = elapsedTime % 60; // Calculate seconds

        // Format the time as "MM:SS" string
        String timeText = String.format("Time: %02d:%02d", minutes, seconds);

        timerLabel.setText(timeText); // Update the timer label text
    }

}
