package govitallLinkRunner.core.utils;

import govitallLinkRunner.core.WebResource;
import govitallLinkRunner.core.results.PageContent;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class HttpProcessing {

    public PageContent sendGet(String url, String sessionId) {
        try {
            URL obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("Cookie", sessionId);
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Chrome/44.0");
            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            //TODO very costil
            /*if(response.toString().contains("_gaq")){
                //System.out.println("601");
                return new PageContent(601, response.toString());
            }*/

            return new PageContent(responseCode, response.toString());
        } catch (SocketTimeoutException e) {
            return new PageContent(401, ""); //TODO !!!!!!!!!! 401
        } catch (Exception e) {
            return new PageContent(404, "");
        }
    }

    /**
     * @param webResource
     * @return Document
     */
    public Document checkWebPageResponse(WebResource webResource, String sessionId) {
        HttpProcessing httpProcessing = new HttpProcessing();
        PageContent response = httpProcessing.sendGet(webResource.getUrl(), sessionId);

        webResource
                .setHttpResponseCode(response.getHttpCode())
                .setIsCheckStatus(true);

        Document doc = Jsoup.parse(response.getResponse());

        return doc;
    }

    public String getCooklie() throws Exception {
        Connection.Response res = Jsoup.connect("https://en.essayprofit.com/admin/login")
                .data("login", "testadmin", "password", "Gjx8934xhfd")
                .method(Connection.Method.POST)
                .execute();
        Document doc = res.parse();
        String sessionId = res.cookie("SESSIONID");
        System.out.println();
        return sessionId;
    }
}