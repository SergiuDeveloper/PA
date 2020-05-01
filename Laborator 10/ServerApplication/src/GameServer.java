import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private int serverPort;
    public int getServerPort() {
        return this.serverPort;
    }
    private void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    private boolean serverRunning = false;
    public boolean getServerRunning() {
        return this.serverRunning;
    }
    private void setServerRunning(boolean serverRunning) {
        this.serverRunning = serverRunning;
    }

    private ServerSocket serverSocket;

    private List<ClientThread> clientThreads;

    public GameServer(int serverPort) {
        this.serverPort = serverPort;
        this.serverRunning = false;
        this.clientThreads = new ArrayList<>();
    }

    public boolean start() throws IOException {
        if (this.serverRunning)
            return false;

        this.serverSocket = new ServerSocket(this.serverPort);
        this.serverRunning = true;

        Socket clientSocket;
        ClientThread clientThread;
        while (this.serverRunning) {
            try {
                clientSocket = this.serverSocket.accept();
            }
            catch (Exception acceptException) {
                return true;
            }
            clientThread = new ClientThread(this, clientSocket);
            clientThread.start();
            this.clientThreads.add(clientThread);
        }

        return true;
    }

    public boolean stop() throws IOException {
        if (!this.serverRunning)
            return false;

        for (var clientThread: this.clientThreads)
            clientThread.interrupt();
        this.serverSocket.close();
        this.serverRunning = false;

        return true;
    }

    public void removeClientThread(ClientThread clientThread) {
        clientThread.interrupt();
        this.clientThreads.remove(clientThread);
    }
}