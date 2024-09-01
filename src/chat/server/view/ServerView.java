package chat.server.view;

import chat.server.controller.ServerController;

public interface ServerView {
    void showMessage(String message);
    String getNameChat();
}
