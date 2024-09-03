package chat.server.controller;

import chat.client.controller.ClientController;
import chat.server.repository.ILog;
import chat.server.repository.LogFile;
import chat.server.view.ServerGUI;
import chat.server.view.ServerView;

import java.util.ArrayList;
import java.util.List;

public class ServerController {

    private final ServerView serverView;

    private String nameChat;
    public boolean isWork = false;
    public ILog log;
    private final List<ClientController> listClientControllers;

    public ServerController(String nameChat) {
        this.log = new LogFile();
        this.listClientControllers = new ArrayList<>();
        this.nameChat = nameChat;
        this.serverView = new ServerGUI(this);
        this.serverView.setServerController(this);
    }

    public void disconnectUser(ClientController clientController) {
        clientController.disconnectFromServer();
    }

    public void connectUser(ClientController clientController) {
//        clientController.connectToServer();
        this.listClientControllers.add(clientController);
        showMessageInWindow(clientController.getUserName() + " connected");
    }

    public List<ClientController> getUsersOnline() {
        return listClientControllers;
    }

    public void showMessageInWindow(String text) {
        this.serverView.showMessage(text);
        this.log.write(text);
    }

    public void start() {
        if (isWork){
            showMessageInWindow("Server still running");
        } else {
            showMessageInWindow("Server run");
            isWork = true;
        }
    }

    public void stop() {
        if (isWork){
            isWork = false;
            while (!listClientControllers.isEmpty()){
                disconnectUser(listClientControllers.getLast());
            }
            showMessageInWindow("Server stop");
        } else {
            showMessageInWindow("Server isn't run");
        }
    }

    public void addUser(ClientController clientController) {
        listClientControllers.add(clientController);
    }

    public String getNameChat() {
        return nameChat;
    }
}
