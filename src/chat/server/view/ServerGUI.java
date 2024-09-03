package chat.server.view;

import chat.client.controller.ClientController;
import chat.client.view.ClientGUI;
import chat.server.controller.ServerController;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ServerGUI implements ServerView {
    private final int WIDTH = 400, HEIGHT = 400;

    private ServerController serverController;

    private final String TITLE = "Server for chat";
    private final JFrame frame = new JFrame(TITLE);
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea logArea = new JTextArea(20, 1);

    public ServerGUI(ServerController serverController) {
        this.serverController = serverController;
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setTitle(TITLE + " - " + serverController.getNameChat());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        createPanel();
        frame.setVisible(true);
    }


    private void createPanel() {
        logArea.setEditable(false);
        frame.add(new JScrollPane(logArea), "Center");

        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart.addActionListener(e -> serverController.start());
        btnStop.addActionListener(e -> serverController.stop());
        panel.add(btnStart);
        panel.add(btnStop);

        frame.add(panel, BorderLayout.SOUTH);
    }

    public String getLogArea() {
        return "";
    }

    @Override
    public void showMessage(String message) {
        logArea.append(message);
        logArea.append("\n");
    }

    @Override
    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }
}
