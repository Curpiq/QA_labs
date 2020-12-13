package lab8;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.io.IOException;


public class RateMountebankTest {
    static CloseableHttpClient httpClient = HttpClients.createDefault();

    String getResponseBody(String url) {
        HttpResponse response = null;
        try {
            response = httpClient.execute(new HttpGet(url));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        assert response != null;
        HttpEntity entity = response.getEntity();

        String responseBody = null;
        try {
            responseBody = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return responseBody;
    }

    @Test
    public void shouldReturnEURRate() {
        String expected = "{\n" +
                "    \"eur\": \"90,02\"\n" +
                "}";
        assertEquals(expected, getResponseBody("http://localhost:4545/rate/eur"));
    }

    @Test
    public void shouldReturnUSDRate() {
        String expected = "{\n" +
                "    \"usd\": \"74,38\"\n" +
                "}";
        assertEquals(expected, getResponseBody("http://localhost:4545/rate/usd"));
    }

    @Test
    public void shouldReturnGBRRate() {
        String expected = "{\n" +
                "    \"gbr\": \"98,64\"\n" +
                "}";
        assertEquals(expected, getResponseBody("http://localhost:4545/rate/gbr"));
    }
}
