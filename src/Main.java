import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> validUrls = Service.checkURLs("http://erau.unba.org.ua/profile/",1,100000);
        List<Lawyer> lawyers = new ArrayList<>();

        for (String validUrl : validUrls) {
            lawyers.add(Service.parseHtml(validUrl));
        }

        Service.listToCSV(lawyers, new File("Lawyers.csv"));

    }
}
