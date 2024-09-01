package chat.server.view;

import chat.server.controller.ServerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame implements ServerView {
    private final int WIDTH = 400, HEIGHT = 400;

    private ServerController serverController;

    private final String nameChat;
    private final String TITLE = "Server for chat";
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();

    public ServerWindow(String nameChat) {
        this.nameChat = nameChat;
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setTitle(TITLE + " - " + nameChat);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createPanel();
        setVisible(true);
    }

    private void appendLog(String text) {
        log.append(text + "\n");
    }

    private void createPanel() {
        add(log);

        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart.addActionListener(e -> serverController.start());
        btnStop.addActionListener(e -> serverController.stop());
        panel.add(btnStart);
        panel.add(btnStop);

        add(panel, BorderLayout.SOUTH);
    }


    @Override
    public void showMessage(String message) {
        log.append(message);
    }

    @Override
    public String getNameChat() {
        return this.nameChat;
    }
}
