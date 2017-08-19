package govitallLinkRunner;


import govitallLinkRunner.core.WebResource;

import java.util.HashMap;
import java.util.Map;

public interface Checks {

    void separateOtherLinks(String currentURL, String baseURL, WebResource webResource);

    void checkExternalLinks();

    void checkImage(String url, String baseURL);

    void checkCSS(String url, String baseURL);

    void checkJS(String url, String baseURL);

    void checkGA(Map.Entry<String, WebResource> webResource);

    public void checkW3cValidation();

}
