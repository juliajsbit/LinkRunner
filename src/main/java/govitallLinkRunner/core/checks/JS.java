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

public class JS {

    LinkBuilder linkBuilder = new LinkBuilder();

    public Map<String, WebResource> checkJSLinks(String currentURL, String baseURL, Map<String, WebResource> jsLinks) {
        collectJSLinks(currentURL, baseURL);
        Iterator<Map.Entry<String, WebResource>> iter = jsLinks1.entrySet().iterator();
        while (iter.hasNext()) {
            final Map.Entry<String, WebResource> next = iter.next();
            if (jsLinks.containsKey(next.getKey())) {
                //cssLinks1.remove(next.getKey());
            } else {
                Integer respose = httpProcessing.sendGet(next.getKey(), "").getHttpCode();
                next.getValue().setHttpResponseCode(respose);
                jsLinks.put(next.getKey(), next.getValue());
            }
        }
        return jsLinks;
    }

    public void collectJSLinks(String url, String baseURL) {
            PageContent response = httpProcessing.sendGet(url, "");
            Document doc = Jsoup.parse(response.getResponse());
            temp = getJSLinks(doc);
            for (String e : temp) {
                String newLink = linkBuilder.composeOtherLink(e, url, baseURL);
                if(newLink != "") {
                    jsLinks1.put(newLink, new WebResource(newLink, url));
                }
            }
        }

    public ArrayList<String> getJSLinks(Document domContent) {
        ArrayList<String> linkCollection = new ArrayList<String>();
        int links = domContent.getElementsByTag("script").size();
        Elements allLinks = domContent.select("script");
        for (int i = 0; i < links; i++) {
            String relHref = allLinks.get(i).attr("src");
            linkCollection.add(relHref);
        }
        return linkCollection;

    }

    public Map<String, WebResource> jsLinks1 = new HashMap<String, WebResource>();
    public ArrayList<String> temp = new ArrayList<String>();
    HttpProcessing httpProcessing = new HttpProcessing();
}
