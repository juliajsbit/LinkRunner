package govitallLinkRunner.core.checks;


import govitallLinkRunner.core.WebResource;
import govitallLinkRunner.core.utils.HttpProcessing;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Links {

    public Map<String, WebResource> getHtmlLinks(Document domContent) {
        Map<String, WebResource> linkCollection = new HashMap<String, WebResource>();
        int links = domContent.getElementsByTag("a").size();
        Elements allLinks = domContent.select("a");
        for (int i = 0; i < links; i++) {
            String relHref = allLinks.get(i).attr("href");
            linkCollection.put(relHref, new WebResource(relHref, ""));
        }
        return linkCollection;
    }

    public void checkResponseCode(Map<String, WebResource> mapForChecked, Map<String, WebResource> mapWithErrors) {
        HttpProcessing httpProcessing = new HttpProcessing();
        Iterator<Map.Entry<String, WebResource>> iterator = mapForChecked.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, WebResource> next = iterator.next();
            Integer response = httpProcessing.sendGet(next.getValue().getUrl(), "").getHttpCode();
            next.getValue().setHttpResponseCode(response);
            if (response != 200) {
                mapWithErrors.put(next.getValue().getUrl(), next.getValue());
                iterator.remove();
            } else {
                next.getValue().setHttpResponseCode(response);
            }
        }
    }
}
