package chat.client.view;


import chat.client.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ClientView {
    private static final int WIDTH = 400, HEIGHT = 300;
    private JTextArea log;
    private JTextField textFieldLogin;
    private JTextField textFieldMessage;
    private JPanel headPanel;
    private ClientController clientController;

    public ClientGUI() {
        setting();
        createPanel();
        setVisible(true);
    }

    private void setting() {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }


    public void disconnectServer() {
        clientController.disconnectServer();
    }

    public void hideHeaderPanel(boolean isVisible) {
        headPanel.setVisible(isVisible);
    }

    public void login() {
        if (clientController.connectToServer(textFieldLogin.getText())) {
            headPanel.setVisible(false);
        }
    }

    private void message() {
        clientController.message(textFieldMessage.getText());
        textFieldMessage.setText("");
    }

    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    private Component createHeaderPanel() {
        headPanel = new JPanel(new GridLayout(2, 3));
        JTextField tfIPAddress = new JTextField("127.0.0.1");
        JTextField tfPort = new JTextField("6655");
        textFieldLogin = new JTextField("User");
        JPasswordField password = new JPasswordField("666555333");
        JButton btnLogin = new JButton("login");
        btnLogin.addActionListener(e -> login());

        headPanel.add(tfIPAddress);
        headPanel.add(tfPort);
        headPanel.add(new JPanel());
        headPanel.add(textFieldLogin);
        headPanel.add(password);
        headPanel.add(btnLogin);

        return headPanel;
    }

    private Component createLog() {
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        textFieldMessage = new JTextField();
        textFieldMessage.addKeyListener(new SendKeyAdapter());
        JButton btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
        panel.add(textFieldMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    @Override
    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void showMessage(String message) {
        log.append(message);
    }

    @Override
    public void disconnectedFromServer() {
        hideHeaderPanel(true);
    }

    @Override
    protected void processWindowEvent(WindowEvent event) {
        super.processWindowEvent(event);
        if (event.getID() == WindowEvent.WINDOW_CLOSING) {
            disconnectServer();
        }
    }

    private class SendKeyAdapter extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent event) {
            if (event.getKeyChar() == '\n') {
                message();
            }
        }
    }
}
