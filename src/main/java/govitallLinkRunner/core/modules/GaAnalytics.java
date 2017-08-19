package govitallLinkRunner.core.modules;

import govitallLinkRunner.core.results.PageContent;
import govitallLinkRunner.core.utils.GloballSettings;
import govitallLinkRunner.core.utils.HttpProcessing;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;

import java.util.*;

public class GaAnalytics {

    private String gaApiUrl = "http://192.168.0.98:8081/api/seo-tests/check-url?url=";

    public GaAnalytics () {
        if (GloballSettings.getMODE().equals(GloballSettings.DEBUG)) {
            //this.gaApiUrl = "http://localhost/~user/Govitall/auto-qa/web/api/seo-tests/check-url?url=";
        }
    }

    public CheckResult check(String url) {

        CheckResult result = new CheckResult();

        HttpProcessing httpProcessing = new HttpProcessing();
        PageContent pageContent = httpProcessing.sendGet(gaApiUrl + url, "");

        if (!pageContent.getResponse().equals("[]")) {
            JSONParser parser = new JSONParser();

            result.addCheckCount(1);
            try {
                Object jsonObj = parser.parse(pageContent.getResponse());
                JSONObject jsonObjInner = (JSONObject) jsonObj;

                jsonObjInner = (JSONObject) jsonObjInner.get(url);

                for (Iterator iterator = jsonObjInner.keySet().iterator(); iterator.hasNext(); ) {
                    String key = (String) iterator.next();

                    JSONParser parserRes = new JSONParser();
                    Object objRes = parserRes.parse(jsonObjInner.get(key).toString());
                    JSONObject jsonObjRes = (JSONObject) objRes;

                    if (jsonObjRes.get("result").equals("pass")) {
                        result.addPassCount(1);
                    } else {
                        result.addFailCount(1);
                    }

                    String message = "Result: " + jsonObjRes.get("result") + "\n";
                    message += "Checked code:\n" + jsonObjRes.get("code") + "\n";
                    message += "-----\n";
                    result.addMessage(message);
                }
            } catch (ParseException e) {
                // e.printStackTrace();
            } catch (Exception e) {
                // e.printStackTrace();
            }
        }
        return result;
    }
}
