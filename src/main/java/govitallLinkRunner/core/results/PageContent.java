package govitallLinkRunner.core.results;


public class PageContent {

    protected Integer httpCode = 0;
    protected String response = "";

    public PageContent(Integer httpCode, String response) {
        this.httpCode = httpCode;
        this.response = response;
    }

    public Integer getHttpCode() {
        return this.httpCode;
    }

    public String getResponse() {
        return this.response;
    }

}
