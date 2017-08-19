package govitallLinkRunner.core.checks;


import govitallLinkRunner.core.W3cValidationResult;
import govitallLinkRunner.core.WebResource;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Validator {

    //public static String urlForCheck = urlFormation("http://goldessay.org/");

    public W3cValidationResult jsonParse(String urlForCheck){
        HashMap<String,String> messages = new HashMap<String, String>();
        JSONObject dataISONObj;
        String strJson = validatorW3c(urlForCheck);
        if(strJson == null){
            return w3cValidationResult = new W3cValidationResult(type, messages, false);
        }else
        try {
            dataISONObj = new JSONObject(strJson);
            JSONArray validationResult = dataISONObj.getJSONArray("messages");

            for (int i = 0; i < validationResult.length(); i++) {
                JSONObject messageOfError = validationResult.getJSONObject(i);
                type = messageOfError.getString("type");
                if (type.equals("error")) {
                    message = messageOfError.getString("message");
                    extract = messageOfError.getString("extract");
                    messages.put(message, extract);
                } else {

                }
                w3cValidationResult = new W3cValidationResult(type, messages, true);
            }
        }catch (Exception e){
            System.out.println("The problem of converting the string into an JSON object "+strJson);
            e.printStackTrace();
            w3cValidationResult = new W3cValidationResult(type, messages, false);
        }
        return w3cValidationResult;
    }

    public Map<String, WebResource>  sortLinksWithW3cError(Map<String, WebResource>  internallinks){
        HashMap<String, WebResource> linksWithW3cError = new HashMap<String, WebResource>();
        final Iterator<Map.Entry<String, WebResource>> iter = internallinks.entrySet().iterator();
        while (iter.hasNext()) {
            final Map.Entry<String, WebResource> next = iter.next();
            if(next.getValue().getQuantityW3cValidationError() >1 || !next.getValue().getW3cValidationStatus()){
                linksWithW3cError.put(next.getKey(), next.getValue());
            }
        }
        return linksWithW3cError;
    }


    private String validatorW3c (String urlForCheck){
        //System.out.println(validatorHttpHead+urlFormation(urlForCheck)+validatorHttpTail);
        try {
            URL url = new URL(validatorHttpHead+urlFormation(urlForCheck)+validatorHttpTail);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine()) != null){
                buffer.append(line);
            }
            resultJson = buffer.toString();

        }catch (Exception e){
            System.out.println("Sorry, some problems with w3c validation  when checking " + urlForCheck);
            e.printStackTrace();
        }
        return null;
    }
    private HttpURLConnection urlConnection = null;
    private BufferedReader reader = null;
    private String resultJson = "";
    private W3cValidationResult w3cValidationResult = null;
    private String validatorHttpHead = "http://validator.w3.org/nu/?doc=";
    private String validatorHttpTail = "&out=json";
    public String type ;
    public String lastLine ;
    public String lastColumn ;
    public String firstColumn;
    public String message ;
    public String extract ;
    public String hiliteStart ;
    public String hiliteLength ;

    private static String urlFormation(String urlForCheck){
        if(urlForCheck.endsWith("/")){
            return urlForCheck;
        }else {
            return urlForCheck+"/";
        }
    }
}
