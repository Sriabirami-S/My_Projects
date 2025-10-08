package tic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeSwing extends JFrame implements ActionListener {

    private JButton[] buttons = new JButton[9];
    private char currentPlayer = 'X';
    private JLabel statusLabel;

    public TicTacToeSwing() {
        setTitle("Tic Tac Toe");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        Color pastelGreen = new Color(152, 251, 152);
        Color blue = new Color(0, 0, 255);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
            buttons[i].setBackground(pastelGreen);
            buttons[i].setForeground(blue);
            buttons[i].addActionListener(this);
            boardPanel.add(buttons[i]);
        }

        statusLabel = new JLabel("Player X's turn");
        statusLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel controlPanel = new JPanel();
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        resetButton.addActionListener(e -> resetBoard());

        JButton endButton = new JButton("End");
        endButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        endButton.addActionListener(e -> System.exit(0));

        controlPanel.add(resetButton);
        controlPanel.add(endButton);

        add(statusLabel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (!clickedButton.getText().equals("")) return;

        clickedButton.setText(String.valueOf(currentPlayer));

        if (checkWinner()) {
            statusLabel.setText("Player " + currentPlayer + " wins! 🏆");
            disableAllButtons();
        } else if (isDraw()) {
            statusLabel.setText("It's a draw!");
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }

    private boolean checkWinner() {
        int[][] combos = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
        };

        for (int[] combo : combos) {
            if (buttons[combo[0]].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[combo[1]].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[combo[2]].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    private boolean isDraw() {
        for (JButton b : buttons) {
            if (b.getText().equals("")) return false;
        }
        return true;
    }

    private void disableAllButtons() {
        for (JButton b : buttons) b.setEnabled(false);
    }

    private void resetBoard() {
        for (JButton b : buttons) {
            b.setText("");
            b.setEnabled(true);
        }
        currentPlayer = 'X';
        statusLabel.setText("Player X's turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeSwing::new);
    }
}
