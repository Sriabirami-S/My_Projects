# Tic Tac Toe – Console Based Game

## Introduction

Tic Tac Toe is a classic two-player strategy game played on a **3x3 grid**. In this **Java console-based implementation**, two players take turns placing their symbols (`X` or `O`) on the board. The first player to align **three of their symbols in a row, column, or diagonal** wins the game.

If all cells are filled without a winner, the game ends in a **draw**.

This project is designed for learners to practice core programming concepts while building a real, playable game.

---

## Objective

* To build a **2-player turn-based game** using Java.
* To gain hands-on experience with:

  * Arrays and multidimensional data structures.
  * Loops and condition-based control flows.
  * User input handling with validation.
  * Modular programming through method creation.

---

## Key Features

* **Console-Based 3x3 Board**
  Displays the game board with clear cell divisions using a structured 5x5 char array for better readability.

* **Interactive Gameplay**
  Two players alternate turns by entering positions numbered **1 to 9**, mapped to the board grid.

* **Winner and Draw Detection**
  After each move, the game checks for a **winner** or a **draw** and announces the result.

* **Input Validation**
  Ensures that players cannot:

  * Enter numbers outside the **1–9** range.
  * Choose a position that is already occupied.

* **Replayability**
  After the end of a round, players can choose to **restart the game** without rerunning the program.

---

## Game Logic Breakdown

### 1. Board Representation

* The game board is represented using a **5x5 `char` array** to simulate a 3x3 grid with dividers (`|`, `+`, `-`).
* Logical positions **1–9** are mapped to specific indices in the array to place `X` and `O` while keeping the grid structure intact.

### 2. Game Loop

* The main game loop allows a maximum of **9 moves**.
* On each iteration:

  * It checks whose turn it is (`X` or `O`).
  * Prompts the respective player to enter a valid position.
  * Places the move using the `placeMove()` method.
  * Checks for a winner or a draw.
* The loop terminates early if a player wins.

### 3. Move Placement

* The method `placeMove(int pos, char symbol)` is responsible for placing `X` or `O` in the correct cell of the board.
* The positions **1–9** correspond to the following layout:

```
 1 | 2 | 3
---+---+---
 4 | 5 | 6
---+---+---
 7 | 8 | 9
```

### 4. Winner Check

* The `checkWinner()` method verifies all **possible win conditions**:

  * Horizontal (rows)
  * Vertical (columns)
  * Diagonal (left to right and right to left)
* If three matching symbols are found in any of these patterns, the method returns the winner.

### 5. Draw Condition

* If **all 9 cells are filled** and no winner is detected, the game declares a **draw**.

### 6. Board Display

* The `printBoard()` method outputs the current state of the board after each valid move.
* A **guide board** is also printed at the start to help players map positions to numbers.

---

## Sample Output

Example Game Board:

```
    1 | 2 | 3
   ---+---+---
    4 | 5 | 6
   ---+---+---
    7 | 8 | 9
```

Sample In-Game Display:

```
    Current Board:
     X | O | X
    ---+---+---
     O | X | O
    ---+---+---
     X |   | O

Player X wins!
```
---

## Java Concepts Practiced

* **2D Arrays**
  For the game board structure and management.

* **Loops and Conditionals**
  For managing game flow, input validation, and winner checks.

* **Modular Programming**
  Game logic is divided into small, reusable methods like `placeMove()`, `printBoard()`, and `checkWinner()`.

* **Input Handling**
  Uses `Scanner` to take input and validate user moves.
  
---

## Conclusion

This Tic Tac Toe project is a simple yet effective demonstration of how foundational Java concepts can be used to build an interactive application. By completing this game, key programming skills are reinforced, including working with multidimensional arrays, managing user input and validation, structuring game loops, and organizing code with reusable methods. The project also introduces the concept of user experience in console-based applications, providing clear instructions, interactive prompts, and error handling for invalid inputs.

The game is modular and easily extendable, leaving room for future improvements such as AI integration or graphical interfaces. It serves as a solid stepping stone for learners who want to transition from basic programming exercises to creating full-fledged applications.



