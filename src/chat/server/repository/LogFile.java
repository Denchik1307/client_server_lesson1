package chat.server.repository;

import java.io.FileReader;
import java.io.FileWriter;

public class LogFile implements ILog {
    private static final String LOG_PATH = "src/chat/server/repository/log.log";

    @Override
    public void save(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public String load() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);) {
            int ch;
            while ((ch = reader.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            return stringBuilder.toString();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
