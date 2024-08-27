package chat.server;

import chat.client.ClientWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    public static final String PATH_FOR_LOG = "/src/chat/server/log.txt";

    private final List<ClientWindow> listClients = new ArrayList<>();

    JButton btnStart, btnStop;
    JTextArea log;
    boolean work;

    public ServerWindow() {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Server for chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createPanel();
        setVisible(true);
    }

    public boolean isUserConnected(ClientWindow clientWindow) {
        if (!work) {
            return false;
        }
        listClients.add(clientWindow);
        return true;
    }

    public String getLog() {
        return readLog();
    }

    public void disconnectUser(ClientWindow clientWindow) {
        listClients.remove(clientWindow);
        if (clientWindow != null) {
            clientWindow.disconnectFromServer();
        }
    }

    public void message(String text) {
        if (work) {
            appendLog(text);
            answerAll(text);
            saveInLog(text);
        }
    }

    private void answerAll(String text) {
        for (ClientWindow clientWindow : listClients) {
            clientWindow.answer(text);
        }
    }

    private void saveInLog(String text) {
        try (FileWriter writer = new FileWriter(PATH_FOR_LOG, true)) {
            writer.append(text).append("\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String readLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(PATH_FOR_LOG);) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
//            stringBuilder.deleteCharAt(stringBuilder.length());
            System.out.println(stringBuilder);
            return stringBuilder.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void appendLog(String text) {
        log.append(text + "\n");
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (work) {
                    appendLog("Server is work");
                } else {
                    work = true;
                    appendLog("Server started");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!work) {
                    appendLog("Server is stopped");
                } else {
                    work = false;
                    while (!listClients.isEmpty()) {
                        disconnectUser(listClients.get(listClients.size() - 1));
                    }
                    appendLog("Server stop");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
}
