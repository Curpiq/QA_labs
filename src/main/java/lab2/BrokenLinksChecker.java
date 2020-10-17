package lab2;

import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BrokenLinksChecker {
    public static Set<String> uniqueLinks = new HashSet<>();
    public static String domain;

    private static int getLinkResponseCode(String url) throws Exception {
        HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());
        huc.setRequestMethod("HEAD");
        huc.connect();
        return huc.getResponseCode();
    }

    private static void getLinks(String url) throws IOException {
        Document doc = Jsoup.connect(url).userAgent("Chrome").get();
        Elements links = doc.select("a");

        if (links.isEmpty()) {
            return;
        }

        for (Element element : links) {
            String link = element.attr("abs:href");
            if (link.endsWith("#")) {
                link = link.substring(0, link.length() - 1);
            }
            boolean add = false;
            if (link.startsWith("http://") || link.startsWith("https://")) {
                add = uniqueLinks.add(link);
            }
            if (add && link.contains(domain)) {
                try {
                    getLinks(link);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        domain = "91.210.252.240";
        try {
            getLinks("http://91.210.252.240/broken-links/");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        try (FileWriter validLinks = new FileWriter("src/main/java/lab2/validLinks.txt");
             FileWriter brokenLinks = new FileWriter("src/main/java/lab2/brokenLinks.txt")) {

            int validLinksCounter = 0;
            int brokenLinksCounter = 0;

            for (String link : uniqueLinks) {
                int responseCode = getLinkResponseCode(link);
                if (responseCode >= 200 && responseCode < 300) {
                    validLinksCounter++;
                    validLinks.write(link + " " + responseCode + "\n");
                } else {
                    brokenLinksCounter++;
                    brokenLinks.write(link + " " + responseCode+ "\n");
                }
            }

            DateFormat date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            validLinks.write("\n" + "Links: " + validLinksCounter + "\n" + date.format(cal.getTime()) + "\n");
            brokenLinks.write("\n" + "Links: " + brokenLinksCounter + "\n"+ date.format(cal.getTime()) + "\n");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
