package chat.server.view;


import chat.server.controller.ServerController;

import javax.swing.*;
import java.awt.*;

public class ServerGUI extends JFrame implements ServerView {
    public static final int WIDTH = 400, HEIGHT = 300;

    private JTextArea log;

    private ServerController serverController;

    public ServerGUI() {
        setupWindow();
        createPanel();
        setVisible(true);
    }

    @Override
    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    private void setupWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
    }

    public ServerController getConnection() {
        return serverController;
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panelButtons = new JPanel(new GridLayout(1, 2));
        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");

        buttonStart.addActionListener(e -> serverController.start());
        buttonStop.addActionListener(e -> serverController.stop());

        panelButtons.add(buttonStart);
        panelButtons.add(buttonStop);
        return panelButtons;
    }

    @Override
    public void showMessage(String msg) {
        log.append(msg);
    }
}
