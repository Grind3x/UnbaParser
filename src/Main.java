import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Parsing valid URLs...");
        List<String> validUrls = Service.checkURLs("http://erau.unba.org.ua/profile/",10000,10100);
        System.out.println("---- > OK");
        System.out.println("Saving list of URLs to .json file...");
        Service.listToJSON(validUrls, new File("ValidURLs.json"));
        System.out.println("---- > OK");
        System.out.println("Parsing valid URLs to csv...");
        Service.parseValidURLsToCSV(validUrls, new File("Result.csv"));
        System.out.println("---- > OK");
    }
}
