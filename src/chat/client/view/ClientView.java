package chat.client.view;


import chat.client.controller.ClientController;

public interface ClientView {

    void showMessage(String message);

    void disconnectedFromServer();

    void setClientController(ClientController clientController);
}
