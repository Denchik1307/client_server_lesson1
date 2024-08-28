package chat.server.view;

import chat.server.controller.ServerController;

public interface ServerView {
    void showMessage(String message);
    void setServerController(ServerController serverController);
    String getNameChat();
}
