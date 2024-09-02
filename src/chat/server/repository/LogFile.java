package chat.server.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LogFile implements ILog {
    private final String PATH_FOR_LOG = "src/chat/server/log.txt";

    @Override
    public void write(String text) {
        try (FileWriter writer = new FileWriter(PATH_FOR_LOG, true)) {
            writer.write(text + "\n");
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public String read() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(PATH_FOR_LOG);) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) stringBuilder.append(line).append("\n");
            return stringBuilder.toString();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
