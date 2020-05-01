import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket clientSocket = null;

    private boolean threadActive;

    private GameServer gameServer;

    public ClientThread (GameServer gameServer, Socket clientSocket) {
        this.gameServer = gameServer;
        this.clientSocket = clientSocket;
        this.threadActive = true;
    }

    public void run() {
        BufferedReader clientSocketBufferedReader;
        try {
            clientSocketBufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            try {
                this.clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                return;
            }
            return;
        }

        String clientRequest;

        PrintWriter clientSocketPrintWriter = null;
        try {
            clientSocketPrintWriter = new PrintWriter(this.clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (this.threadActive) {
            try {
                clientRequest = clientSocketBufferedReader.readLine();
                assert clientSocketPrintWriter != null;
                this.handleClientRequest(clientSocketPrintWriter, clientRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        assert clientSocketPrintWriter != null;
        clientSocketPrintWriter.close();
        try {
            clientSocketBufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interrupt() {
        this.threadActive = false;
    }

    private void handleClientRequest(PrintWriter clientSocketPrintWriter, String clientRequest) throws IOException {
        clientSocketPrintWriter.println("Server received the request '" + clientRequest + "'");
        clientSocketPrintWriter.flush();

        switch (clientRequest) {
            case "stop":
                if (this.gameServer.stop())
                    clientSocketPrintWriter.println("Server stopped");
                else
                    clientSocketPrintWriter.println("Server error");
                clientSocketPrintWriter.flush();
                break;
            case "exit":
                this.gameServer.removeClientThread(this);
                break;
            default:
                clientSocketPrintWriter.println("Unknown request '" + clientRequest + "'");
                clientSocketPrintWriter.flush();
                break;
        }
    }
}
