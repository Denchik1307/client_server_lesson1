package chat.server.repository;

import java.io.FileReader;
import java.io.FileWriter;

public class LogFile implements ILog<String> {
    private static final String PATH_FOR_LOG = "src/chat/server/log.txt";

    @Override
    public void write(String text){
        try (FileWriter writer = new FileWriter(PATH_FOR_LOG, true)) {
            writer.append(text).append("\n");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public String read(){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(PATH_FOR_LOG);) {
            int ch;
            while ((ch = reader.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            System.out.println(stringBuilder);
            return stringBuilder.toString();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
