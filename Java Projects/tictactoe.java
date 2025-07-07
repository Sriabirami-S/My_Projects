package KK_Java;

import java.util.Scanner;
public class tictactoe {
    static char[][] board = {
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}
    }; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
        printBoard();

        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) {
                System.out.print("Player X, enter position (1-9): ");
                int pos = sc.nextInt();
                placeMove(pos, 'X');
            } else {
                System.out.print("Player O, enter position (1-9): ");
                int pos = sc.nextInt();
                placeMove(pos, 'O');
            }

            printBoard();

            String result = checkWinner();
            if (!result.equals("")) {
                System.out.println(result);
                break; 
            }

            if (i == 8) {
                System.out.println("It's a "
                		+ "draw!");
            }
        }

        sc.close();
    }

    public static void placeMove(int pos, char symbol) {
        switch (pos) {
            case 1: board[0][0] = symbol; break;
            case 2: board[0][2] = symbol; break;
            case 3: board[0][4] = symbol; break;
            case 4: board[2][0] = symbol; break;
            case 5: board[2][2] = symbol; break;
            case 6: board[2][4] = symbol; break;
            case 7: board[4][0] = symbol; break;
            case 8: board[4][2] = symbol; break;
            case 9: board[4][4] = symbol; break;
            default: System.out.println("Invalid position"); break;
        }
    }

    public static void printBoard() {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static String checkWinner() {
        if (board[0][0] == board[0][2] && board[0][2] == board[0][4] && board[0][0] != ' ')
            return "Player " + board[0][0] + " wins!";
        if (board[2][0] == board[2][2] && board[2][2] == board[2][4] && board[2][0] != ' ')
            return "Player " + board[2][0] + " wins!";
        if (board[4][0] == board[4][2] && board[4][2] == board[4][4] && board[4][0] != ' ')
            return "Player " + board[4][0] + " wins!";

        if (board[0][0] == board[2][0] && board[2][0] == board[4][0] && board[0][0] != ' ')
            return "Player " + board[0][0] + " wins!";
        if (board[0][2] == board[2][2] && board[2][2] == board[4][2] && board[0][2] != ' ')
            return "Player " + board[0][2] + " wins!";
        if (board[0][4] == board[2][4] && board[2][4] == board[4][4] && board[0][4] != ' ')
            return "Player " + board[0][4] + " wins!";

        if (board[0][0] == board[2][2] && board[2][2] == board[4][4] && board[0][0] != ' ')
            return "Player " + board[0][0] + " wins!";
        if (board[0][4] == board[2][2] && board[2][2] == board[4][0] && board[0][4] != ' ')
            return "Player " + board[0][4] + " wins!";

        return ""; 
    }
}
