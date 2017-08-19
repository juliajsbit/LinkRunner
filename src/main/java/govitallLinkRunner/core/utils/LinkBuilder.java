package govitallLinkRunner.core.utils;


import govitallLinkRunner.core.WebResource;

import java.util.Map;

public class LinkBuilder {


    public void separateLinks(String baseURL, String currentURL, Map<String, WebResource> _alllinks, Map<String, WebResource> _inretnalLinks, Map<String, WebResource> _externalLinks, Map<String, WebResource> _internalNotPageLinks, Map<String, WebResource> _externalNotPageLinks, String parentURL, Map<String, WebResource> trashLinks, Map<String, WebResource> emails) {
        LinkBuilder linkBuilder = new LinkBuilder();
        String hostName = linkBuilder.hostName(baseURL);
        for (Map.Entry<String, WebResource> webResource : _alllinks.entrySet()) {
            String url = composeLinkByType(webResource.getValue().getUrl(), currentURL, baseURL); //internal
            if (url != "") {
                if (url.regionMatches(true, 0, baseURL, 0, baseURL.length() - 1)) {
                    if (url.contains("#")) {
                        trashLinks.put(url, new WebResource(url, parentURL));
                    } else if (url.endsWith(".pdf") || url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith(".gif")) {
                        _internalNotPageLinks.put(url, new WebResource(url, parentURL));
                    } else {
                        _inretnalLinks.put(url, new WebResource(url, parentURL));
                    }
                } else if (url.contains(hostName)) {
                    if (!url.startsWith("https://twitter.com")) {
                        _externalLinks.put(url, new WebResource(url, parentURL));
                    } else if (url.endsWith(".pdf") || url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith(".gif") /*&& !url.startsWith("mailto")*/) {
                        _externalNotPageLinks.put(url, new WebResource(url, parentURL));
                    } else {
                        _inretnalLinks.put(url, new WebResource(url, parentURL));
                    }
                } else if (url.startsWith("http") && !url.startsWith(baseURL)) {
                    _externalLinks.put(url, new WebResource(url, parentURL));
                } else if (url.endsWith(".pdf") || url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith(".gif") && url.startsWith(baseURL)) {
                    _internalNotPageLinks.put(url, new WebResource(url, parentURL));
                } else if (url.endsWith(".pdf") || url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith(".gif") && !url.startsWith(baseURL)) {
                    _externalNotPageLinks.put(url, new WebResource(url, parentURL));
                } else if (url.startsWith("javascript")) {
                    trashLinks.put(url, new WebResource(url, parentURL));
                } else if (url.startsWith("mailto")) {
                    emails.put(url, new WebResource(url, parentURL));
                } else {
                    trashLinks.put(url, new WebResource(url, parentURL));
                }
            }
        }
    }

    public void margeLinks(Map<String, WebResource> internalLinks, Map<String, WebResource> _innerLinks,Map<String, WebResource> externalLinks, Map<String, WebResource> _externalLinks,Map<String, WebResource> internalNonPageLinks, Map<String, WebResource> _innerNonPageLinks,Map<String, WebResource> externalNonPageLinks, Map<String, WebResource> _externalNonPageLinks) {
        for (Map.Entry<String, WebResource> webResource1 : _innerLinks.entrySet()) {
            if (internalLinks.containsKey(webResource1.getKey())) {
                internalLinks.get(webResource1.getKey()).setUrlParentPage(webResource1.getValue().getParentLink().toString()); //TODO !!!!!!!!!!!

            } else {
                if (webResource1.getValue().getHttpStatusCode() != 200) {
                    internalLinks.put(webResource1.getKey(), webResource1.getValue());
                }
            }
        }
        for (Map.Entry<String, WebResource> webResource : _externalLinks.entrySet()) {
            if (externalLinks.containsKey(webResource.getKey())) {

            } else {
                externalLinks.put(webResource.getKey(), webResource.getValue());
            }
        }
        for (Map.Entry<String, WebResource> webResource : _innerNonPageLinks.entrySet()) {
            if (internalNonPageLinks.containsKey(webResource.getKey())) {

            } else {
                internalNonPageLinks.put(webResource.getKey(), webResource.getValue());
                //System.out.println("Internal not html page: "+ webResource.getKey());
            }

        }
        for (Map.Entry<String, WebResource> webResource : _externalNonPageLinks.entrySet()) {
            if (externalNonPageLinks.containsKey(webResource.getKey())) {

            } else {
                externalNonPageLinks.put(webResource.getKey(), webResource.getValue());
                //System.out.println("External not html page: "+webResource.getKey());
            }
        }
    }
    private String composeLinkByType(String linkForCompose, String currentURL, String baseURL) { //TODO если нет слеша вначале то к текукущему
        StringBuffer temp = new StringBuffer(linkForCompose);//TODO првиести к одному виду что б не повторялись /
        String newLinkForCompose;
        if(linkForCompose.startsWith("#") && linkForCompose.contains("#")){
            return "";
        }else if(linkForCompose.startsWith("http")){
            return linkForCompose;
        }else if(linkForCompose.startsWith("./")){
            newLinkForCompose = currentURL + temp.deleteCharAt(0).deleteCharAt(0).toString();
            return urlWithoutSharp(newLinkForCompose);
        }else if(linkForCompose.startsWith("/")){
            if (linkForCompose.length() > 1 && linkForCompose.charAt(1) == '/') {
                newLinkForCompose = getProtocol(currentURL) + linkForCompose;
                urlWithoutSharp(newLinkForCompose);
                return newLinkForCompose;
            } else {
                newLinkForCompose = baseURL + temp.deleteCharAt(0).toString();
                urlWithoutSharp(newLinkForCompose);
                return newLinkForCompose;
            }
        }else if(linkForCompose.startsWith("javascript")){
            return "";
        }else if(linkForCompose.contains("#")){
            return "";
        }
        return "";
    }

    public String composeOtherLink(String linkForCompose, String currentURL, String baseURL) { //TODO если нет слеша вначале то к текукущему
        StringBuffer temp = new StringBuffer(linkForCompose);//TODO првиести к одному виду что б не повторялись /
        String newLinkForCompose;
        if(linkForCompose.startsWith("#") && linkForCompose.contains("#")){
            return "";
        }else if(linkForCompose.startsWith("http")){
            return linkForCompose;
        }else if(linkForCompose.startsWith("./")){
            newLinkForCompose = currentURL + temp.deleteCharAt(0).deleteCharAt(0).toString();
            return newLinkForCompose;
        }else if(linkForCompose.startsWith("/")){
            if (linkForCompose.length() > 1 && linkForCompose.charAt(1) == '/') {
                newLinkForCompose = getProtocol(currentURL) + linkForCompose;
                //urlWithoutSharp(newLinkForCompose);
                return newLinkForCompose;
            } else {
                newLinkForCompose = baseURL + temp.deleteCharAt(0).toString();
                //urlWithoutSharp(newLinkForCompose);
                return newLinkForCompose;
            }
        }/*else if(linkForCompose.startsWith("javascript")){
            return "";
        }else if(linkForCompose.contains("#")){
            return "";
        }*/
        return "";
    }


    private String getProtocol(String url){
        String[]d = url.split("/");
        return d[0];
    }
    private String urlWithoutSharp(String url){
        String[] a = url.toString().split("#");
        String d = a[0];
        return d;
    }

    public String hostName(String url){
        String []a = url.toString().split("/");
        //String[] b = url.toString().split("\\.");
        if (a[2].contains("www")){
            String[] s =a[2].split("www\\.");
            return s[1];
        }else{
            return a[2];
        }
    }

    /*public String hostName(String url){
        String []a = url.toString().split("/");
        return a[2];
    }*/
}
