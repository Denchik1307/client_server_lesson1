package chat.server.view;

import chat.client.view.ClientGUI;
import chat.server.controller.ServerController;

import javax.swing.*;
import java.awt.*;

public class ServerGUI extends JFrame implements ServerView {
    private final int WIDTH = 400, HEIGHT = 400;

    private ServerController serverController;

    private final String nameChat;
    private final String TITLE = "Server for chat";
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea logArea = new JTextArea();

    public ServerGUI(String nameChat) {
        this.nameChat = nameChat;
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setTitle(TITLE + " - " + nameChat);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createPanel();
        setVisible(true);
    }


    private void createPanel() {
        add(logArea);

        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart.addActionListener(e -> serverController.start());
        btnStop.addActionListener(e -> serverController.stop());
        panel.add(btnStart);
        panel.add(btnStop);

        add(panel, BorderLayout.SOUTH);
    }



    public boolean isUserConnected(ClientGUI clientGUI) {
        return false;
    }

    public void disconnectUser(ClientGUI clientGUI) {
    }

    public String getLogArea() {
        return "";
    }

    public void message(String s) {

    }

    @Override
    public void showMessage(String message) {
        logArea.append(message);
    }

    @Override
    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    @Override
    public String getNameChat() {
        return this.nameChat;
    }

}
