package lab8;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Imposter {
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    static HttpPost request = new HttpPost("http://localhost:2525/imposters");
    public static void main(String[] args) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get("src/main/java/lab8/imposter.json")));
        StringEntity params = new StringEntity(data);

        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        httpClient.execute(request);
    }
}
