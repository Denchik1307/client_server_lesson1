package chat.client;

import chat.server.view.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientWindow extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private final ServerWindow server;
    private boolean isUserConnected = false;

    private String userName;

    private JPanel connectionPanel;

    private JTextField textFieldIPaddress;
    private JTextField textFieldPort;
    private JTextField textFieldLogin;
    private JPasswordField textFieldPassword;
    private JButton buttonSend;

    private JPanel panelMessageLog;
    private JTextField messageField;
    private JButton buttonConnect;
    private JTextArea areaLog = new JTextArea();

    public ClientWindow(ServerWindow server) {
        this.server = server;

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");

        createView();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void answer(String text) {
        appendLog(text);
    }

    private void connectToServer() {
        if (server.isUserConnected(this)) {
            String log = server.getLog();
            isUserConnected = true;
            userName = textFieldLogin.getText();
            connectionPanel.setVisible(false);
            if (log != null) appendLog(log);
            setTitle("Chat client - " + userName);
            appendLog("Connected is successful!\n");
        } else {
            appendLog("Connection filed");
        }
    }

    public void disconnectFromServer() {
        if (isUserConnected) {
            this.connectionPanel.setVisible(true);
            this.isUserConnected = false;
            this.server.disconnectUser(this);
            appendLog("Connection closed!");
        }
    }

    public void sendMessage() {
        if (isUserConnected && !messageField.getText().isEmpty()) {
            server.message(userName + ": " + messageField.getText());
        } else {
            appendLog("Not connection");
        }
        messageField.setText("");
    }

    private void appendLog(String text) {
        areaLog.append(text + "\n");
    }

    private void createView() {
        add(createConnectionPanel(), BorderLayout.NORTH);
        add(new JScrollPane(areaLog));
        add(createPanelMessageSend(), BorderLayout.SOUTH);
    }

    private Component createConnectionPanel() {
        JPanel left = new JPanel(new GridLayout(2,1));
        JPanel center = new JPanel(new GridLayout(2,1));
        JPanel right = new JPanel(new GridLayout(1,1));
        connectionPanel = new JPanel(new GridLayout(1,2));

        textFieldIPaddress = new JTextField("IP address");
        textFieldPort = new JTextField("port");
        textFieldLogin = new JTextField("Name");
        textFieldPassword = new JPasswordField("password");
        buttonConnect = new JButton("connect");

        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        left.add(textFieldIPaddress);
        left.add(textFieldLogin);
        center.add(textFieldPort);
        center.add(textFieldPassword);
        right.add(buttonConnect);
        connectionPanel.add(left,BorderLayout.WEST);
        connectionPanel.add(center,BorderLayout.CENTER);
        connectionPanel.add(right,BorderLayout.EAST);

        return connectionPanel;
    }

    private Component createPanelMessageSend() {
        panelMessageLog = new JPanel(new BorderLayout());
        messageField = new JTextField();
        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == '\n') {
                    sendMessage();
                }
            }
        });
        buttonSend = new JButton("send");
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessage();
            }
        });
        panelMessageLog.add(messageField);
        panelMessageLog.add(buttonSend, BorderLayout.EAST);
        return panelMessageLog;
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            disconnectFromServer();
        }
        super.processWindowEvent(e);
    }
}
