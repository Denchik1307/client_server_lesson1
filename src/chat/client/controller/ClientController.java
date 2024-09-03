package chat.client.controller;

import chat.client.view.ClientView;
import chat.server.controller.ServerController;

import java.util.ArrayList;
import java.util.List;

public class ClientController implements ClientView {

    private static List<String> listUsers = new ArrayList<>();
    private boolean isConnected;
    private ServerController serverController;

    public ClientController(ServerController serverController) {
        this.serverController = serverController;
    }

    public String getUsersOnline() {
        StringBuilder users = new StringBuilder();
        serverController.getUsersOnline().forEach(user -> users.append(user).append("\n"));
        return users.toString();
    }

    @Override
    public void connectToServer() {

        this.serverController.connectUser(this);
    }

    @Override
    public void disconnectFromServer() {
        this.serverController.disconnectUser(this);
    }

    @Override
    public void setClientController(ClientView clientView) {
        clientView.setClientController(this);
    }

    @Override
    public void showMessage(String text) {

    }

    public String getUser() {
        return "";
    }
}
