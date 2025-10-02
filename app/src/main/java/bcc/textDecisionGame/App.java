package bcc.textDecisionGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Text Decision Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);

        frame.setLayout(new BorderLayout());

        JLabel text = new JLabel("Scenario 1: You are given a new project in CP3.\nDo you start today or wait until it's due?", SwingConstants.CENTER);
        text.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(text, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());

        JButton leftButton = new JButton("Start today");
        JButton rightButton = new JButton("Wait until due");

        // --- SCENARIO 1: Start Today ---
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text.setText("Scenario 2: While working, you discover a new version of Snake!\nDo you keep working or try Snake?");
                leftButton.setText("Keep working");
                rightButton.setText("Try Snake");

                // Clear old listeners
                clearListeners(leftButton);
                clearListeners(rightButton);

                // --- SCENARIO 2 ---
                leftButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        text.setText("You ignored the game and finished early. Solid work! You got an A!");
                        endGame(leftButton, rightButton);
                    }
                });

                rightButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        text.setText("Scenario 3: You played Snake for 2 class periods.\nDo you finish after school or give up?");
                        leftButton.setText("Finish after school");
                        rightButton.setText("Give up");

                        // Clear again
                        clearListeners(leftButton);
                        clearListeners(rightButton);

                        // --- SCENARIO 3 ---
                        leftButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                text.setText("Final Result: You finished after school, got 100% on your project, and life is grand!");
                                endGame(leftButton, rightButton);
                            }
                        });

                        rightButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                text.setText("You gave up and you failed the project.");
                                endGame(leftButton, rightButton);
                            }
                        });
                    }
                });
            }
        });

        // --- SCENARIO 1: Wait until due ---
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text.setText("You procrastinated and ran out of time.");
                endGame(leftButton, rightButton);
            }
        });

        buttonPanel.add(leftButton, BorderLayout.WEST);
        buttonPanel.add(rightButton, BorderLayout.EAST);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Utility: Clear listeners so new ones can be added cleanly
    private static void clearListeners(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }

    // Utility: Disable buttons when game ends
    private static void endGame(JButton leftButton, JButton rightButton) {
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
    }
}