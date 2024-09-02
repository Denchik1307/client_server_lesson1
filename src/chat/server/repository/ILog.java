package chat.server.repository;

public interface ILog {
    String read();

    void write(String text);
}
