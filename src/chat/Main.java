package chat;


import chat.client.controller.ClientController;
import chat.client.view.ClientGUI;
import chat.server.controller.ServerController;
import chat.server.repository.LogFile;
import chat.server.view.ServerGUI;

public class Main {
    public static void main(String[] args) {
        ServerController serverController = new ServerController(new ServerGUI(), new LogFile());

        new ClientController(new ClientGUI(), serverController);
        new ClientController(new ClientGUI(), serverController);
    }
}
