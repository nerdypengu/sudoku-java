import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame {

    public StartMenu() {
        setTitle("Game Start Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel titleLabel = new JLabel("Sudoku");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the start menu frame
                new DiffMenu().setVisible(true);

            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Terminate the application
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
        panel.add(startGameButton, gbc);

        gbc.gridy++;
        panel.add(exitButton, gbc);

        add(panel);

        setPreferredSize(new Dimension(400, 300));
        pack(); // Pack the components
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartMenu startMenu = new StartMenu();
            startMenu.setVisible(true);
        });
    }
}
