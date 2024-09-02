package chat;

import chat.client.ClientGUI;
import chat.client.controller.ClientController;
import chat.server.controller.ServerController;
import chat.server.view.ServerGUI;

public class Main {
    public static void main(String[] args) {

        ServerController serverController  = new ServerController(new ServerGUI("Work"));

        new ClientController(serverController);
//        new ClientController(serverController);
//        new ClientGUI(new ServerGUI("W"));
    }
}
