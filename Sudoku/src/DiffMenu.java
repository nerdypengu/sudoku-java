/* KELOMPOK 10
 * Eugenia Indrawan - 5026221020
 * Ashila Mahdiyyah - 5026221148
 * Razi Alvaro Arman - 5026221168
 * 
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiffMenu extends JFrame {

    public DiffMenu() {
        setTitle("Game Start Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Sudoku");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton mode1Button = new JButton("Easy");
        mode1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        dispose();
                        new SudokuMain(1).setVisible(true);

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
                        dispose();
                        new SudokuMain(2).setVisible(true);
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
                        dispose();
                        new SudokuMain(3).setVisible(true);

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

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(titleLabel, gbc);

        gbc.gridy++;
        panel.add(mode1Button, gbc);

        gbc.gridy++;
        panel.add(mode2Button, gbc);

        gbc.gridy++;
        panel.add(mode3Button, gbc);

        gbc.gridy++;
        panel.add(exitButton, gbc);

        add(panel);

        setPreferredSize(new Dimension(400, 300));
        pack(); // Pack the components
        setLocationRelativeTo(null);
    }
}
