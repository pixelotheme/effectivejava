import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        Path path = Path.of("/path");
        FileStore fileStore = Files.getFileStore(path);
    }
}