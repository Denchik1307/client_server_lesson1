package chat.client.controller;

import chat.client.view.ClientView;
import chat.server.controller.ServerController;

public class ClientController {

    private boolean connected;
    private String nameUser;
    private ClientView clientView;
    private ServerController serverController;

    public ClientController(ClientView clientView, ServerController serverController) {
        this.clientView = clientView;
        this.serverController = serverController;
        clientView.setClientController(this);
    }

    public void disconnectFromServer() {

    }

    public String getNameUser() {
        return nameUser;
    }
}
