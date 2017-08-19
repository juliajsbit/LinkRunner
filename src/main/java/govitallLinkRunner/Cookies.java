package govitallLinkRunner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * Created by Kolesnik on 21.02.2016.
 */
public class Cookies {

    public static void main(String[] args) {

        connectWithCookies("https://www.facebook.com/", "https://www.facebook.com/karynakalyna?pnref=lhc.friends", "lalakolesnik@gmail.com", "");

    }

    /**
     *
     * @param loginURL
     * @param URL
     * @param login
     * @param password
     * @return Document
     */
    public static Document connectWithCookies(String loginURL, String URL, String login, String password)
    {
        try {
            Connection.Response res = Jsoup.connect(loginURL).data("email", login, "pass", password).method(Connection.Method.GET).execute();
            Map<String, String> cookies = res.cookies();
            Document doc = Jsoup.connect(URL).cookies(cookies).get();
            System.out.println((Jsoup.connect(URL).cookies(cookies).data().execute().statusCode()));
            System.out.println((Jsoup.connect(URL).cookies(cookies).data().execute().statusMessage()));
            System.out.println(doc.title());
            return doc;
        } catch (Exception e) {
            System.out.println("Problem with cookies");
            e.printStackTrace();
        }
        return null;
    }
}

