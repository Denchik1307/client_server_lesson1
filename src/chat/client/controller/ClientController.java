package chat.client.controller;


import chat.client.view.ClientView;
import chat.server.controller.ServerController;

public class ClientController {
    private boolean connected;
    private String name;
    private final ClientView clientView;
    private final ServerController serverController;

    public ClientController(ClientView clientView, ServerController serverController) {
        this.clientView = clientView;
        this.serverController = serverController;
        clientView.setClientController(this);
    }

    public boolean connectToServer(String name) {
        this.name = name;
        if (serverController.connectUser(this)) {
            showOnWindow("You have successfully connected\n");
            connected = true;
            String log = serverController.getHistory();
            if (log != null) {
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("Connection failed");
            return false;
        }
    }

    public void answerFromServer(String text) {
        showOnWindow(text);
    }

    public void disconnectedFromServer() {
        if (connected) {
            connected = false;
            clientView.disconnectedFromServer();
            showOnWindow("You have been disconnected from the server");
        }
    }

    public void disconnectServer() {
        serverController.disconnectUser(this);
    }

    public void message(String text) {
        if (connected) {
            if (!text.isEmpty()) {
                serverController.message(name + ": " + text);
            }
        } else {
            showOnWindow("No connection to server");
        }
    }

    public String getName() {
        return name;
    }

    private void showOnWindow(String text) {
        clientView.showMessage(text + "\n");
    }
}
