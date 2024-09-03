package chat.client.controller;

import chat.client.view.ClientGUI;
import chat.client.view.ClientView;
import chat.server.controller.ServerController;
import chat.server.view.ServerGUI;
import chat.server.view.ServerView;

import java.util.ArrayList;
import java.util.List;

public class ClientController implements ClientView {

    private static List<String> listUsers = new ArrayList<>();
    private final ServerController serverController;
    private final ClientView clientView;
    private ClientController clientController;
    private boolean isConnected;

    public ClientController(ServerController serverController) {
        this.serverController = serverController;
        this.clientView = new ClientGUI(serverController);
    }

    //for show active users (maybe)
    public String getUsersOnline() {
        StringBuilder users = new StringBuilder();
        serverController.getUsersOnline().forEach(user -> users.append(user).append("\n"));
        return users.toString();
    }

    @Override
    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void showMessage(String text) {
        clientController.showMessage(text);
    }

    public void disconnectFromServer() {
        serverController.disconnectUser(this);
    }

    @Override
    public void connectToServer() {
        serverController.connectUser(this);
    }

    @Override
    public String getUserName() {
        return clientController.getUserName();
    }
}
