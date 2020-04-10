import com.lab7.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(15);
        Player player1 = new Player("Player 1", board);
        Player player2 = new Player("Player 2", board);
        Game game = new Game(board, player1, player2);

        game.runGame();
    }
}
