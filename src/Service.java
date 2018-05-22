import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Service {

    public static List<String> checkURLs(String urlTemplate, int start, int end) {
        List<String> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            try {
                URL url = new URL(urlTemplate + i);
                System.out.print("Checking " + url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    boolean broken = false;
                    String output;
                    for (; (output = br.readLine()) != null; ) {
                        if (output.contains("Сторінка не найдена")) {
                            System.out.println();
                            broken = true;
                            break;
                        }
                    }
                    if (!broken) {
                        list.add(url.toString());
                        System.out.println(" added");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static Lawyer parseHtml(String url) {
        if (url == null) {
            throw new IllegalArgumentException();
        }
        Document doc;
        String fullName = "";
        String region = "";
        String certificateNumber = "";
        String certificateDate = "";
        String lawyerForm = "";
        String address = "";
        String phoneNuberOne = "";
        String phoneNuberTwo = "";
        String email = "";
        try {
            doc = Jsoup.connect(url).get();
            fullName = doc.select("p.info-about__name").text();
            region = doc.select("p[class=\"\"]").text();
            certificateNumber = doc.select("p.info-about__certificate-date").text();
            certificateDate = doc.select("div.col-xs-6.col-md-3.info-about__main-secondary")
                    .get(1).select("p")
                    .get(1).text();
            try {
                lawyerForm = doc.select("div.column-right__header.col-md-12").first().text();
            } catch (NullPointerException e) {

            }
            Elements elements = doc.select("div.text-info.col-md-9");
            address = elements.get(0).text();
            if (elements.get(1).text().contains("+38")) {
                phoneNuberOne = elements.get(1).text();
            }
            if (elements.get(2).text().contains("+38")) {
                phoneNuberTwo = elements.get(2).text();
            }
            try {
                if (doc.select("div.text-info.text-info__mail.col-md-9").first().text().contains("@")) {
                    email = doc.select("div.text-info.text-info__mail.col-md-9").first().text();
                }
            } catch (NullPointerException e) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Lawyer(fullName, region, certificateNumber, certificateDate, lawyerForm, address, phoneNuberOne, phoneNuberTwo, email);
    }

    public static void listToJSON(List<?> list, File file) {
        if (list == null || file == null) {
            throw new IllegalArgumentException();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file))) {
            pw.println(gson.toJson(list));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void parseValidURLsToCSV(List<String> list, File file) {
        if (list == null || file == null) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file))) {
            pw.println("ПІБ;Номер свідоцтва;Дата видачі;Форма адвокатської діяльності;Адреса;Телефон;Телефон;E-mail");
            for (String s : list) {
                System.out.print("Current URL: " + s + "  |  ");
                Lawyer lawyer = Service.parseHtml(s);
                System.out.println(count);
                pw.print(lawyer.getFullName() + ";");
                pw.print(lawyer.getCertificateNumber() + ";");
                pw.print(lawyer.getCertificateDate() + ";");
                pw.print(lawyer.getLawyerForm() + ";");
                pw.print(lawyer.getAddress() + ";");
                pw.print(lawyer.getPhoneNuberOne() + ";");
                pw.print(lawyer.getPhoneNuberTwo() + ";");
                pw.println(lawyer.getEmail() + ";");
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<String> listFromJSON(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }
        Gson gson = new Gson();
        List<String> list = new ArrayList<>();
        try {
            list = gson.fromJson(new FileReader(file), ArrayList.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
