package chat.server.controller;


import chat.client.controller.ClientController;
import chat.server.repository.ILog;
import chat.server.view.ServerView;

import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private boolean isWork;
    private final ServerView serverView;
    private final List<ClientController> clientControllerList;
    private final ILog log;

    public ServerController(ServerView serverView, ILog log) {
        this.serverView = serverView;
        this.log = log;
        clientControllerList = new ArrayList<>();
        serverView.setServerController(this);
    }

    public void start() {
        if (isWork) {
            showOnWindow("Server is already running");
        } else {
            isWork = true;
            showOnWindow("Server run");
        }
    }

    public void stop() {
        if (!isWork) {
            showOnWindow(" Server isn`t run");
        } else {
            isWork = false;
            while (!clientControllerList.isEmpty()) {
                disconnectUser(clientControllerList.getLast());
            }
            showOnWindow(" Server stop");
        }
    }

    public void disconnectUser(ClientController clientController) {
        clientControllerList.remove(clientController);
        if (clientController != null) {
            clientController.disconnectedFromServer();
            showOnWindow(clientController.getName() + " disconnected");
        }
    }

    public boolean connectUser(ClientController clientController) {
        if (!isWork) {
            return false;
        }
        clientControllerList.add(clientController);
        showOnWindow(clientController.getName() + " connected");
        return true;
    }

    public void message(String text) {
        if (!isWork) {
            return;
        }
        showOnWindow(text);
        answerAll(text);
        saveInHistory(text);
    }

    public String getHistory() {
        return log.load();
    }

    private void answerAll(String text) {
        for (ClientController clientController : clientControllerList) {
            clientController.answerFromServer(text);
        }
    }

    private void showOnWindow(String text) {
        serverView.showMessage(text + "\n");
    }

    private void saveInHistory(String text) {
        log.save(text);
    }
}
