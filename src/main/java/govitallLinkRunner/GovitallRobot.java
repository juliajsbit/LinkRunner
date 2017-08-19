package govitallLinkRunner;

import govitallLinkRunner.core.RobotProperties;
import govitallLinkRunner.core.W3cValidationResult;
import govitallLinkRunner.core.WebResource;
import govitallLinkRunner.core.checks.*;
import govitallLinkRunner.core.modules.CheckResult;
import govitallLinkRunner.core.modules.GaAnalytics;
import govitallLinkRunner.core.results.RunResults;
import govitallLinkRunner.core.utils.*;
import govitallLinkRunner.core.utils.print.PrintResult;
import govitallLinkRunner.core.utils.print.PrintToFile;
import org.jsoup.nodes.Document;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GovitallRobot extends GovitallRobotCore implements IGovitallRobot, Checks {

    private PrintResult printResult = new PrintResult();
    private PrintToFile printToFile = new PrintToFile();
    private LinkBuilder linkBuilder = new LinkBuilder();
    private HttpProcessing httpProcessing = new HttpProcessing();
    private Validator validator = new Validator();
    private Links links = new Links();
    private CSS css = new CSS();
    private Image image = new Image();
    private JS js = new JS();
    private Debug debug = new Debug();
    private Time time = new Time();
    private String start;
    private String end;
    Parser parser = new Parser();
    ArrayList<String> del = new ArrayList<String>();


    @Override
    public void init(String baseURL, RobotProperties robotproperties) {
        printToFile.printConsole("LinkRunner checks link " + baseURL + " Please, wait");
        if (baseURL.endsWith("/")) {

        } else if (!baseURL.endsWith("/")) {
            baseURL = baseURL + "/";
        }
        this.setProperties(robotproperties);
        this.inputLinkInCollection(baseURL);
        this.baseURL = baseURL;
    }

    @Override
    public void run(int currentDepth, String coolie) {
        if (currentDepth == 0) {
            start = time.getStartTime();
        }
        Map<String, WebResource> _alllinks;
        Map<String, WebResource> _inretnalLinks = new HashMap<String, WebResource>();
        Map<String, WebResource> _externalLinks = new HashMap<String, WebResource>();
        Map<String, WebResource> _internalNotPageLinks = new HashMap<String, WebResource>();
        Map<String, WebResource> _externalNotPageLinks = new HashMap<String, WebResource>();

        Boolean _isExistNotCheckResources = false;
        for (Map.Entry<String, WebResource> webResource : this.internalLinks.entrySet()) {
            if (!webResource.getValue().getIsChecked()) {
                String parentURL = webResource.getValue().getUrl();
                Document domContent = httpProcessing.checkWebPageResponse(webResource.getValue(), coolie);
                checkGA(webResource);

                _alllinks = links.getHtmlLinks(domContent);

                linkBuilder.separateLinks(baseURL, webResource.getValue().getUrl(), _alllinks, _inretnalLinks, _externalLinks, _internalNotPageLinks, _externalNotPageLinks, parentURL, trashLinks, emails);
                separateOtherLinks(webResource.getValue().getUrl(), baseURL, webResource.getValue());
                debug.debugPrint("Checked: " + webResource.getKey() + "  " + "  " + currentDepth);

                _isExistNotCheckResources = true;
            }
        }

        linkBuilder.margeLinks(internalLinks, _inretnalLinks, externalLinks, _externalLinks, internalNonPageLinks, _internalNotPageLinks, externalNonPageLinks, _externalNotPageLinks);
        if (_isExistNotCheckResources && currentDepth++ < this.robotProperties.getDepth()) {
            this.run(currentDepth, coolie);
        } else {
            try {
                sortingLinksWithErrors(internalLinks);
            } catch (Exception e) {

            }
            //checkExternalLinks();
            checkInternalNonPageLinks();
            checkExternalNonPageLinks();
            //sortImage();
            //sortCSS();
            //sortJS();
            //checkW3cValidation();
            end = time.getEndTime();
            this.printResult();
            this.robotProperties.setStatus(1);
        }
    }

    public void checkExternalLinks() {
        if (this.robotProperties.getCheckExternalLinks()) {
            links.checkResponseCode(externalLinks, errorExternalLinks);
        }
    }


    public void checkImage(String url, String baseURL) {
        if (this.robotProperties.getCheckImage()) {
            image.checkImageLinks(url, baseURL, imageLinks);
        }
    }

    public void sortImage() {
        if (this.robotProperties.getCheckImage()) {
            final Iterator<Map.Entry<String, WebResource>> iter = imageLinks.entrySet().iterator();
            while (iter.hasNext()) {
                final Map.Entry<String, WebResource> next = iter.next();
                if (next.getValue().getHttpStatusCode() == 200) {

                } else {
                    errorImageLinks.put(next.getValue().getUrl(), next.getValue());
                    iter.remove();
                }
            }
        }
    }

    /**
     * css
     */
    public void checkCSS(String url, String baseURL) {
        if (this.robotProperties.getCheckCSS()) {
            css.checkCSSLinks(url, baseURL, cssLinks);
        }
    }

    public void sortCSS() {
        if (this.robotProperties.getValidation()) {
            final Iterator<Map.Entry<String, WebResource>> iter = cssLinks.entrySet().iterator();
            while (iter.hasNext()) {
                final Map.Entry<String, WebResource> next = iter.next();
                if (next.getValue().getHttpStatusCode() == 200) {

                } else {
                    errorCSSLinks.put(next.getValue().getUrl(), next.getValue());
                    iter.remove();
                }
            }
        }
    }

    /**
     * js
     */
    public void checkJS(String url, String baseURL) {
        if (this.robotProperties.getCheckJS()) {
            js.checkJSLinks(url, baseURL, jsLinks);
        }
    }

    public void sortJS() {
        if (this.robotProperties.getCheckJS()) {
            final Iterator<Map.Entry<String, WebResource>> iter = jsLinks.entrySet().iterator();
            while (iter.hasNext()) {
                final Map.Entry<String, WebResource> next = iter.next();
                if (next.getValue().getHttpStatusCode() == 200) {

                } else {
                    errorJSLinks.put(next.getValue().getUrl(), next.getValue());
                    iter.remove();
                }
            }
        }
    }

    /**
     * GA
     */
    public void checkGA(Map.Entry<String, WebResource> webResource) {
        if (this.robotProperties.getCheckGA()) {
            GaAnalytics gaAnalytics = new GaAnalytics();
            CheckResult res = gaAnalytics.check(webResource.getKey());
            webResource.getValue().setModuleResults("GA", res);
        }
    }

    /**
     * W3cValidation
     */
    public void checkW3cValidation() {
        if (this.robotProperties.getValidation()) {
            final Iterator<Map.Entry<String, WebResource>> iter = internalLinks.entrySet().iterator();
            while (iter.hasNext()) {
                final Map.Entry<String, WebResource> next = iter.next();
                W3cValidationResult w3cValidationResult = validator.jsonParse(next.getKey());
                next.getValue().setW3cValidationResult(w3cValidationResult.getURL(), w3cValidationResult.getErrorMessages(), w3cValidationResult.getCheckStatus());
                if (w3cValidationResult.getErrorMessages().size() > 1 || !w3cValidationResult.getCheckStatus()) {
                    errorW3cValidationLinks.put(next.getKey(), next.getValue());
                    //System.out.println("W3C ");
                }
            }
        }
    }

    @Override
    public RunResults getRunResults() {
        return new RunResults();
    }

    @Override
    protected void setProperties(RobotProperties properties) {
        this.robotProperties = properties;
    }

    @Override
    protected void inputLinkInCollection(String url) {
        this.internalLinks.put(url, new WebResource(url, url));
    }

    private void sortingLinksWithErrors(Map internalLinksTemp) throws Exception { //TOD
        int count = 0;
        int counter = 0;
        del = parser.getDatas();
        final Iterator<Map.Entry<String, WebResource>> iter = internalLinksTemp.entrySet().iterator();

        while (iter.hasNext()) {
            final Map.Entry<String, WebResource> next = iter.next();
            /*for (String e : del) {*/
                if (next.getValue().getUrl().equals("http://paramountessays.com/essay")) {
                    System.out.println(count + " " + next.getValue().getUrl()+ " "+next.getValue().getParentLink());
                    count++;
                    for (String l : next.getValue().getParentLink()) {
                        System.out.println(next.getValue().getUrl() + "  " + l);
                    }
                }
            int a = next.getValue().getHttpStatusCode();
            if (a == 200) {
            } else if (a == 0) {
                if (!internalNonPageLinks.containsValue(next.getValue().getUrl())) {
                    uncheckedLinks.put(next.getValue().getUrl(), next.getValue());
                }
                iter.remove();
            } else if (!next.getValue().getUrl().startsWith("mailto")) {
                errorInternalLinks.put(next.getValue().getUrl(), next.getValue());
                iter.remove();
            }

            if (next.getValue().getModuleResults().containsKey("GA")) {
                //System.out.println(next.getValue().getModuleResultsByName("GA").getFailCount());
                if (next.getValue().getModuleResultsByName("GA").getFailCount() > 0) { //TODO
                    errorInternalLinks.put(next.getValue().getUrl(), next.getValue());
                }
            }
        }

    }

        /*for(String o: temp){
            counter++;
            System.out.println(counter + "  "+o);
        }
    }*/

    public RobotProperties getRobotProperties() {
        return this.robotProperties;
    }

    public void separateOtherLinks(String currentURL, String baseURL, WebResource webResource) {
        checkCSS(currentURL, baseURL);
        checkJS(currentURL, baseURL);
        checkImage(currentURL, baseURL);
    }

    public void checkInternalNonPageLinks() {
        links.checkResponseCode(internalNonPageLinks, errorInternalNonPageLinks);
    }

    public void checkExternalNonPageLinks() {
        links.checkResponseCode(externalNonPageLinks, errorExternalNonPageLinks);
    }

    private void printResult() {
        printResult.header(baseURL, this.robotProperties.getDepth());
        printResult.spendTime(start, end, time.getSpendTime());
        printResult.statisticsInternalLinks(internalLinks.size(), uncheckedLinks.size(), errorInternalLinks.size());
        printResult.statisticsInternalNonPageLinks(internalNonPageLinks.size(), errorInternalNonPageLinks.size(), true);
        printResult.statisticsEnternalLinks(externalLinks.size(), errorExternalLinks.size(), this.robotProperties.getCheckExternalLinks());
        printResult.statisticsExternalNonPageLinks(externalNonPageLinks.size(), errorExternalNonPageLinks.size(), this.robotProperties.getCheckExternalLinks());
        printResult.statisticsIMGlinks(imageLinks.size(), errorImageLinks.size(), this.robotProperties.getCheckImage());
        printResult.statisticsJSlinks(jsLinks.size(), errorJSLinks.size(), this.robotProperties.getCheckJS());
        printResult.statisticsCSSlinks(cssLinks.size(), errorCSSLinks.size(), this.robotProperties.getCheckCSS());
        printResult.statisticW3cValidation(errorW3cValidationLinks.size(), this.robotProperties.getValidation());
        //error
        printResult.errorInternalLinks(errorInternalLinks);
        printResult.errorInternalNonPageinks(errorInternalNonPageLinks);
        printResult.errorExternalLinks(errorExternalLinks, this.robotProperties.getCheckExternalLinks());
        printResult.errorExternalNonPageinks(errorExternalNonPageLinks, this.robotProperties.getCheckExternalLinks());
        printResult.errorIMGLinks(errorImageLinks, this.robotProperties.getCheckImage());
        printResult.errorJSLinks(errorJSLinks, this.robotProperties.getCheckJS());
        printResult.errorCSSLinks(errorCSSLinks, this.robotProperties.getCheckCSS());
        printResult.errorW3cValidation(errorW3cValidationLinks, this.robotProperties.getValidation());
        /*----------*/
        printResult.checkedInternalLink(internalLinks);
        printResult.internalNonPageLinks(internalNonPageLinks, true);
        printResult.uncheckedInternalLink(uncheckedLinks);
        printResult.checkedEnternalLink(externalLinks, this.robotProperties.getCheckExternalLinks());
        printResult.externalNonPageLinks(externalNonPageLinks, this.robotProperties.getCheckExternalLinks());
        printResult.checkedIMGLink(imageLinks, this.robotProperties.getCheckImage());
        printResult.checkedJSLink(jsLinks, this.robotProperties.getCheckJS());
        printResult.checkedCSSLink(cssLinks, this.robotProperties.getCheckCSS());
        System.out.println(printToFile.getAbsolutePathToResult());
    }
}
