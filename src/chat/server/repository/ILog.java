package chat.server.repository;

public interface ILog {
    void save(String text);

    java.lang.String load();
}
