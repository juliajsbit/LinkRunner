package govitallLinkRunner.core;


import govitallLinkRunner.core.modules.CheckResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebResource {

    private String url = "";
    private String response = "";
    private ArrayList<String> urlParentPage = new ArrayList<String>();
    private Integer httpStatusCode = 0;
    private Boolean isChecked = false;
    private Map<String, CheckResult> moduleResults = new HashMap<String, CheckResult>();
    private W3cValidationResult w3cValidationResult;

    public WebResource(String url, String parentURL) {
        this.url = url;
        this.urlParentPage.add(parentURL);
    }

    public Map<String, CheckResult> getModuleResults() {
        return moduleResults;
    }

    public CheckResult getModuleResultsByName(String name) {
        return moduleResults.get(name);
    }

    public void setModuleResults(String name, CheckResult result) {
        this.moduleResults.put(name, result);
    }

    public Integer getHttpStatusCode() {
        return this.httpStatusCode;
    }

    public ArrayList<String> getParentLink() {
        return urlParentPage;
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

    public String getUrl() {
        return this.url;
    }

    public String getResponse() {
        return this.response;
    }

    public WebResource setHttpResponseCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
        return this;
    }

    public WebResource setIsCheckStatus(Boolean isChecked) {
        this.isChecked = isChecked;
        return this;
    }
    public WebResource setUrlParentPage(String parentURL) {
        urlParentPage.add(parentURL);
        return this;
    }

    public W3cValidationResult setW3cValidationResult(String type, HashMap<String, String> errorMessage, Boolean checkStatus){
        return new W3cValidationResult(type,  errorMessage, checkStatus);
    }

    public W3cValidationResult getW3cValidationResult(){
        return w3cValidationResult;
    }

    public int getQuantityW3cValidationError(){
        return w3cValidationResult.getErrorMessages().size();
    }
    public Boolean getW3cValidationStatus(){
        return w3cValidationResult.getCheckStatus();
    }
}
