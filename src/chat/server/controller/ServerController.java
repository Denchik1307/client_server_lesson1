package chat.server.controller;

import chat.client.controller.ClientController;
import chat.server.repository.ILog;
import chat.server.repository.LogFile;
import chat.server.view.ServerView;

import java.util.ArrayList;
import java.util.List;

public class ServerController {

    private boolean isWork = false;
    private ServerView serverView;
    private ILog log;


    private List<ClientController> listClientControllers;

    public ServerController(ServerView serverView) {
        this.serverView = serverView;
        this.log = new LogFile();
        this.listClientControllers = new ArrayList<>();
    }


    public void disconnectUser(ClientController clientController) {
        clientController.disconnectFromServer();
    }

    public void connectUser(ClientController clientController) {
        clientController.connectToServer();
    }

    public List<ClientController> getUsersOnline() {
        return listClientControllers;
    }

    private void showMessageInWindow(String text) {
        serverView.showMessage(text);
        log.write(text);
    }

    public void start() {
        if (!isWork){
            showMessageInWindow("Server isn't run");
        } else {
            isWork = true;
            showMessageInWindow("Server running");
        }
    }

    public void stop() {
        if (!isWork){
            showMessageInWindow("Server isn't run");
        } else {
            isWork = false;
            while (!listClientControllers.isEmpty()){
                disconnectUser(listClientControllers.get(listClientControllers.size() - 1));
            }
            showMessageInWindow("Server stopped");
        }
    }
}
