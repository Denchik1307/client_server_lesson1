package chat.client.view;

import chat.client.controller.ClientController;

public interface ClientView {
    void setClientController(ClientController clientController);

    void showMessage(String text);

    void disconnectFromServer();

    void connectToServer();

    String getUserName();
}
