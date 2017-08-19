package govitallLinkRunner.core;
import java.util.HashMap;


public class W3cValidationResult {

    public W3cValidationResult(String url, HashMap<String, String> errorMessages, Boolean checkStatus){
        this.url = url;
        this.errorMessages = errorMessages;
        this.checkStatus = checkStatus;
    }

    private String url = "";
    private Boolean checkStatus;
    private HashMap<String, String> errorMessages = new HashMap<String, String>();

    public String getURL(){
        return url;
    }
    public Boolean getCheckStatus(){
        return checkStatus;
    }
    public HashMap<String, String> getErrorMessages(){
        return errorMessages;
    }
}
