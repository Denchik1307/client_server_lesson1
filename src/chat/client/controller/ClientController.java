package chat.client.controller;


import chat.client.view.ClientView;
import chat.server.controller.ServerController;

public class ClientController {
    private boolean connected;
    private String name;
    private final ClientView clientView;
    private final ServerController serverController;

    public ClientController(ClientView clientView, ServerController serverController) {
        this.clientView = clientView;
        this.serverController = serverController;
        clientView.setClientController(this);
    }

    public boolean connectToServer(String name) {
        this.name = name;
        if (serverController.connectUser(this)){
            showOnWindow("You have successfully connected\n");
            connected = true;
            String log = serverController.getHistory();
            if (log != null){
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("Connection failed");
            return false;
        }
    }

    public void answerFromServer(String text) {
        showOnWindow(text);
    }

    public void disconnectedFromServer() {
        if (connected) {
            connected = false;
            clientView.disconnectedFromServer();
            showOnWindow("You have been disconnected from the server");
        }
    }

    /**
     * Метод отключения от сервера инициализированное клиентом (например закрыто GUI)
     */
    public void disconnectServer() {
        serverController.disconnectUser(this);
    }

    /**
     * Метод для передачи сообщения на сервер
     * @param text текст передаваемого сообщения
     */
    public void message(String text) {
        if (connected) {
            if (!text.isEmpty()) {
                serverController.message(name + ": " + text);
            }
        } else {
            showOnWindow("No connection to server");
        }
    }

    /**
     * Геттер
     * @return возвращает имя клиента
     */
    public String getName() {
        return name;
    }

    /**
     * Метод вывода сообщения на GUI
     * @param text текст, который требуется вывести на экран
     */
    private void showOnWindow(String text) {
        clientView.showMessage(text + "\n");
    }
}
