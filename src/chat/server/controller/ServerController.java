package chat.server.controller;

import chat.client.controller.ClientController;
import chat.server.view.ServerView;

import java.util.List;

public class ServerController {

    private boolean isWork;
    private ServerView serverView;
    private List<ClientController> listClientController;

    public ServerController(ServerView serverView) {
        this.serverView = serverView;
    }

    public void stop() {
        if (!isWork){
            showOnWindow("Server isn't run");
        } else {
            isWork = false;
            while (!listClientController.isEmpty()){
                disconnectUser(listClientController.get(listClientController.size() - 1));
            }
            showOnWindow("Server stopped");
        }
    }

    private void disconnectUser(ClientController clientController) {
        listClientController.remove(clientController);
        if (clientController != null){
            clientController.disconnectFromServer();
            showOnWindow(clientController.getNameUser() + " disconnect");
        }
    }

    private void showOnWindow(String text) {
        serverView.showMessage(text);
    }

    private void showConnectedUsers(){
        listClientController.forEach(System.out::println);
    }

    public void start() {
        if (!isWork){
            showOnWindow("Server isn't run");
        } else {
            isWork = false;
            while (!listClientController.isEmpty()){
                disconnectUser(listClientController.getLast());
            }
            showOnWindow("Server stopped");
        }
    }
}
