import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameClient gameClient = new GameClient("127.0.0.1", 27015);
        gameClient.run();
    }
}
