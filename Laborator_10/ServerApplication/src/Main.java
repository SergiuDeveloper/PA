import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameServer gameServer = new GameServer(27015);
        gameServer.start();
    }
}
