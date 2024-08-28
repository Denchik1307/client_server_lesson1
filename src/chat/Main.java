package chat;

import chat.client.controller.ClientController;
import chat.client.view.ClientView;
import chat.server.controller.ServerController;
import chat.server.view.ServerWindow;

public class Main {
    public static void main(String[] args) {

        ServerController serverController = new ServerController(new ServerWindow("Work"));

        new ClientController(new ClientView(), serverController);
        new ClientController(new ClientView(), serverController);
    }
}
