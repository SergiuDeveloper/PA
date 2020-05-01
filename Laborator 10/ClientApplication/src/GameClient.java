import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    private String serverIP;
    public String getServerIP() {
        return this.serverIP;
    }
    private void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    private int serverPort;
    public int getServerPort() {
        return this.serverPort;
    }
    private void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    private Socket clientSocket;

    public GameClient(String serverIP, int serverPort) {
        this.serverPort = serverPort;
    }

    public void run() throws IOException {
        this.clientSocket = new Socket(this.serverIP, this.serverPort);

        PrintWriter clientSocketPrintWriter = new PrintWriter(this.clientSocket.getOutputStream(), true);
        BufferedReader clientSocketBufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

        Scanner scanner = new Scanner(System.in);

        String clientRequest;
        String serverResponse;
        do {
            clientRequest = scanner.nextLine();

            clientSocketPrintWriter.println(clientRequest);

            if (clientRequest.equals("exit"))
                break;
            else {
                serverResponse = clientSocketBufferedReader.readLine(); /* Request received confirmation */
                System.out.println(serverResponse);

                serverResponse = clientSocketBufferedReader.readLine();
                this.handleServerResponse(serverResponse);
            }
        } while (!clientSocket.isClosed());

        clientSocket.close();
    }

    public void handleServerResponse(String serverResponse) throws IOException {
        System.out.println(serverResponse);

        switch (serverResponse) {
            case "Server stopped":
                this.clientSocket.close();
                break;
            case "Server error":
                this.clientSocket.close();
                break;
            default:
                System.out.println("Unhandled response");
                break;
        }
    }
}
