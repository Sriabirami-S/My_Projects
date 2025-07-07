# Tic Tac Toe - Java Based Game

## Introduction
Tic Tac Toe is a classic two-player game played on a 3x3 grid. In this console-based implementation using Java, two players take alternate turns to place their symbols (X and O) on the board. The first player to align three of their symbols vertically, horizontally, or diagonally wins the game.

## Objective
- To implement a 2-player turn-based logic game in Java.
- To learn and demonstrate basic Java concepts such as arrays, loops, conditionals, and user input handling.

## Key Features
- Console interface using a structured 3x3 board.
- Input handling via Java’s Scanner class.
- Turn-based logic with alternating players.
- Win condition checks for rows, columns, and diagonals.
- Declares a winner or a draw.

## Working Logic Breakdown

### 1. Board Representation
- The board is created using a `char[][]` array.
- Logical positions (1–9) are mapped to indexes in a 5x5 character grid for visual spacing.

### 2. Game Loop
- The game loop runs for a maximum of 9 moves (3x3 grid).
- Players take turns based on the iteration count.

### 3. Move Placement
- `placeMove()` updates the board based on input (1–9).

### 4. Winner Check
- After each move, `checkWinner()` is called to verify if a player has won.
- If a winning condition is met, the game ends and the winner is displayed.

### 5. Draw Condition
- If all 9 cells are filled without a winner, the game declares a draw.

### 6. Board Display
- The `printBoard()` method visually displays the board after each move.

## Code
[View Code](https://github.com/Sriabirami-S/Skills-and-Projects/blob/main/Java/Project/tictactoe.java)

## Output

![image](https://github.com/user-attachments/assets/86508763-1813-42fb-9afb-551ef99a7ae7)

## Video Output
[Tic tac toe](https://drive.google.com/file/d/1V0sT84B-TaNmNzAwq1ZNOljNPz-zlsgL/view)

## Conclusion

This Tic Tac Toe project is a simple yet effective demonstration of how core Java concepts can be applied to build an interactive game. By developing this project, the following skills are strengthened:

- Working with 2D arrays  
- Managing user input  
- Using conditionals and loops  
- Building reusable methods  
- Structuring logic with modular code  

The project can be enhanced further by:

- Adding a single-player mode with AI  
- Improving input validation  
- Creating a GUI using JavaFX or Swing  
- Keeping score across multiple rounds



