package KK_Java;

import java.util.Scanner;

public class tictactoe {

    static char[][] board = new char[5][5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe! ");

        boolean playAgain;

        do {
            resetBoard();
            System.out.println("\nPlayer X vs Player O");
            System.out.println("----------------------------------");
            System.out.println("Choose positions (1-9) as per the grid:");
            printGuideBoard();
            System.out.println("----------------------------------");

            printBoard();

            for (int i = 0; i < 9; i++) {
                int pos;
                if (i % 2 == 0) {
                    System.out.print("Player X, enter position (1-9): ");
                    pos = getValidPosition(sc);
                    placeMove(pos, 'X');
                } else {
                    System.out.print("Player O, enter position (1-9): ");
                    pos = getValidPosition(sc);
                    placeMove(pos, 'O');
                }

                System.out.println();
                printBoard();

                String result = checkWinner();
                if (!result.equals("")) {
                    System.out.println();
                    System.out.println(result + " 🏆");
                    break;
                }

                if (i == 8) {
                    System.out.println();
                    System.out.println("It's a draw!");
                }
            }

            System.out.print("\nDo you want to play again? (Y/N): ");
            String choice = sc.next();
            playAgain = choice.equalsIgnoreCase("Y");

        } while (playAgain);

        System.out.println("\nThank you for playing Tic Tac Toe!");
        sc.close();
    }

    public static void resetBoard() {
        char[][] newBoard = {
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
        };

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
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
        System.out.println("    Current Board:");
        for (char[] row : board) {
            System.out.print("    ");
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
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

    public static void printGuideBoard() {
        System.out.println("    1 | 2 | 3");
        System.out.println("   ---+---+---");
        System.out.println("    4 | 5 | 6");
        System.out.println("   ---+---+---");
        System.out.println("    7 | 8 | 9");
    }

    public static int getValidPosition(Scanner sc) {
        int pos;
        while (true) {
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input! Enter a number between 1-9: ");
                sc.next(); 
            }

            pos = sc.nextInt();
            if (pos < 1 || pos > 9) {
                System.out.print("Invalid input! Enter position (1-9): ");
            } else if (isPositionTaken(pos)) {
                System.out.print("Position already taken! Choose another: ");
            } else {
                break;
            }
        }
        return pos;
    }

    public static boolean isPositionTaken(int pos) {
        switch (pos) {
            case 1: return board[0][0] != ' ';
            case 2: return board[0][2] != ' ';
            case 3: return board[0][4] != ' ';
            case 4: return board[2][0] != ' ';
            case 5: return board[2][2] != ' ';
            case 6: return board[2][4] != ' ';
            case 7: return board[4][0] != ' ';
            case 8: return board[4][2] != ' ';
            case 9: return board[4][4] != ' ';
            default: return true; 
        }
    }
}
