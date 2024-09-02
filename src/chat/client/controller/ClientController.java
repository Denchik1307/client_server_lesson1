package chat.client.controller;

import chat.client.view.ClientView;
import chat.server.controller.ServerController;

import java.util.ArrayList;
import java.util.List;

public class ClientController {

    private static List<String> listUsers = new ArrayList<>();
    private boolean connected;
    private ClientView clientView;
    private ServerController serverController;

    public ClientController(ServerController serverController) {
        this.serverController = serverController;
        this.clientView = new ClientView() {
            @Override
            public void setClientController(ClientController clientController) {

            }

            @Override
            public void showMessage(String text) {

            }

            @Override
            public void disconnectFromServer() {

            }
        };
    }

    public void disconnectFromServer() {
        this.serverController.disconnectUser(this);
    }

    public List<String> getUsers() {
        return listUsers;
    }

    public String getUsersOnline(){
        StringBuilder users = new StringBuilder();
        serverController.getUsersOnline().forEach(user -> users.append(user).append("\n"));
        return users.toString();
    }

    public void connectToServer() {
        this.serverController.connectUser(this);
    }
}
