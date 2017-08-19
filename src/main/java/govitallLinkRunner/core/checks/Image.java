package govitallLinkRunner.core.checks;

import govitallLinkRunner.core.WebResource;
import govitallLinkRunner.core.results.PageContent;
import govitallLinkRunner.core.utils.HttpProcessing;
import govitallLinkRunner.core.utils.LinkBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Image {

    LinkBuilder linkBuilder = new LinkBuilder();

    public Map<String, WebResource> checkImageLinks(String currentURL,String baseURL, Map<String, WebResource> imageLinks) {
        collectImageLinks(currentURL, baseURL);
        Iterator<Map.Entry<String, WebResource>> iter = imageLinks1.entrySet().iterator();
        while (iter.hasNext()) {
            final Map.Entry<String, WebResource> next = iter.next();
            if (imageLinks.containsKey(next.getKey())) {

            }else {
                Integer respose = httpProcessing.sendGet(next.getKey(), "").getHttpCode();
                next.getValue().setHttpResponseCode(respose);
                imageLinks.put(next.getKey(), next.getValue());
            }
        }
        return imageLinks;
    }
    public void collectImageLinks(String currentURL,String baseURL){
            PageContent response = httpProcessing.sendGet(currentURL, "");
            Document doc = Jsoup.parse(response.getResponse());
            temp = getIMGLinks(doc);
            for(String e: temp) {
                String newLink = linkBuilder.composeOtherLink(e, currentURL, baseURL);
                if (newLink != "") {
                    imageLinks1.put(newLink, new WebResource(newLink, currentURL));
                }
            }
        }

    public ArrayList<String> getIMGLinks(Document domContent) {
        ArrayList linkCollection = new ArrayList();
        int links = domContent.getElementsByTag("img").size();
        Elements allLinks = domContent.select("img");
        for (int i = 0; i < links; i++) {
            String relHref = allLinks.get(i).attr("src");
            linkCollection.add(relHref);
        }
        return linkCollection;
    }

    public  Map<String, WebResource> imageLinks1 = new HashMap<String, WebResource>();
    public ArrayList<String> temp = new ArrayList<String>();
    HttpProcessing httpProcessing = new HttpProcessing();


}
