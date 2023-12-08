import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiffMenu extends JFrame {

    public DiffMenu() {
        setTitle("Game Start Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel titleLabel = new JLabel("Tic Tac Toe");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton mode1Button = new JButton("Easy");
        mode1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        dispose(); // Close the start menu frame
                        new SudokuMain().setVisible(true);

                    }
                });
            }
        });

        JButton mode2Button = new JButton("Medium");
        mode2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        dispose(); // Close the start menu frame
                        new SudokuMain().setVisible(true);
                    }
                });
            }
        });

        JButton mode3Button = new JButton("Hard");
        mode3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        dispose(); // Close the start menu frame
                        new SudokuMain().setVisible(true);

                    }
                });
            }
        });

        JButton exitButton = new JButton("Back");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    StartMenu startMenu = new StartMenu();
                    startMenu.setVisible(true);
                    setVisible(false);
                });
            }
        });

        // Create a panel to hold the components with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Set GridBagConstraints for center alignment
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0); // Add space below the title
        panel.add(titleLabel, gbc);

        gbc.gridy++;
        panel.add(mode1Button, gbc);

        gbc.gridy++;
        panel.add(mode2Button, gbc);

        gbc.gridy++;
        panel.add(mode3Button, gbc);

        gbc.gridy++;
        panel.add(exitButton, gbc);

        // Add the panel to the frame
        add(panel);

        // Set the preferred size and pack
        setPreferredSize(new Dimension(400, 300)); // Set the preferred width and height
        pack(); // Pack the components
        setLocationRelativeTo(null); // Center the frame on the screen
    }
}
