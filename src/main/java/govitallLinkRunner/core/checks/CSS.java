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

public class CSS{

    LinkBuilder linkBuilder = new LinkBuilder();

    public Map<String, WebResource> checkCSSLinks(String currentURL, String baseURL, Map<String, WebResource> cssLinks) {
        collectCSSLinks(currentURL, baseURL);
        Iterator<Map.Entry<String, WebResource>> iter = cssLinks1.entrySet().iterator();
        while (iter.hasNext()) {
            final Map.Entry<String, WebResource> next = iter.next();
            if (cssLinks.containsKey(next.getKey())) {

            } else {
                Integer respose = httpProcessing.sendGet(next.getKey(), "").getHttpCode();
                next.getValue().setHttpResponseCode(respose);
                cssLinks.put(next.getKey(), next.getValue());
            }
        }
        return cssLinks;
    }
    public void collectCSSLinks(String url, String baseURL){
            PageContent response = httpProcessing.sendGet(url, "");
            Document doc = Jsoup.parse(response.getResponse());
            temp = getCSSLinks(doc);
            for(String e: temp){
                if(e.endsWith(".css")) { //TODO need optimization sort of type="test/css"
                    String newLink = linkBuilder.composeOtherLink(e, url, baseURL);
                    if(newLink != "") {
                        cssLinks1.put(newLink, new WebResource(newLink, url));
                    }
                }
            }
        }


    public ArrayList<String> getCSSLinks(Document domContent) {
        ArrayList<String> linkCollection = new ArrayList<String>();
        int links = domContent.getElementsByTag("link").size();
        Elements allLinks = domContent.select("link");
        for (int i = 0; i < links; i++) {
            String relHref = allLinks.get(i).attr("href");
            linkCollection.add(relHref);
        }
        return linkCollection;
    }


    public  Map<String, WebResource> cssLinks1 = new HashMap<String, WebResource>();
    public ArrayList<String> temp = new ArrayList<String>();
    HttpProcessing httpProcessing = new HttpProcessing();
}
