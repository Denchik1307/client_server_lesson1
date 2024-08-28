package chat.server.repository;

public interface ILog<String> {
    String read();
    void write(String text);
}
