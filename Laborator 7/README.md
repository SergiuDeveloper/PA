# FII JAVA LAB7
## Nistor Marian-Sergiu

* Compulsory

Create the class Token. An instance of this class will hold a number from 1 to m. Consider the case when a token may be blank, meaning that it can take the place of any number.
Create the class Board. An instance of this class will contain n tokens (you may consider the numbers from 1 to n).
Create the class Player. Each player will have a name. This class will implement the Runnable interface. In the run method the player will repeatedly extract one token from the board.
Create the Game class. Simulate the game using a thread for each player.
Pay attention to the synchronization of the threads when extracting tokens from the board.

* Optional

Make sure that players wait their turns, using a wait-notify approach.