package govitallLinkRunner;


import govitallLinkRunner.core.RobotProperties;
import govitallLinkRunner.core.WebResource;
import govitallLinkRunner.core.results.RunResults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class GovitallRobotCore implements IGovitallRobot {

    protected String baseURL = "";
    protected RobotProperties robotProperties;

    protected Map<String, WebResource> internalLinks = new HashMap<String, WebResource>();
    protected Map<String, WebResource> errorInternalLinks = new HashMap<String, WebResource>();
    protected Map<String, WebResource> errorW3cValidationLinks = new HashMap<String, WebResource>();
    protected Map<String, WebResource> externalLinks = new HashMap<String, WebResource>();
    protected Map<String, WebResource> errorExternalLinks = new HashMap<String, WebResource>();
    protected Map<String, WebResource> emails = new HashMap<String, WebResource>();
    protected Map<String, WebResource> trashLinks = new HashMap<String, WebResource>();

    protected Map<String, WebResource> internalNonPageLinks = new HashMap<String, WebResource>();
    protected Map<String, WebResource> errorInternalNonPageLinks = new HashMap<String, WebResource>();

    protected Map<String, WebResource> externalNonPageLinks = new HashMap<String, WebResource>();
    protected Map<String, WebResource> errorExternalNonPageLinks = new HashMap<String, WebResource>();

    protected Map<String, WebResource> uncheckedLinks = new HashMap<String, WebResource>();

    protected Map<String, WebResource> imageLinks = new HashMap<String, WebResource>();
    protected Map<String, WebResource> errorImageLinks = new HashMap<String, WebResource>();

    protected Map<String, WebResource> cssLinks = new HashMap<String, WebResource>();
    protected Map<String, WebResource> errorCSSLinks = new HashMap<String, WebResource>();

    protected Map<String, WebResource> jsLinks = new HashMap<String, WebResource>();
    protected Map<String, WebResource> errorJSLinks = new HashMap<String, WebResource>();

    protected ArrayList<String> temp = new ArrayList<String>();

    public abstract void init(String baseURL, RobotProperties robotproperties);

    abstract public void run(int currentDepth, String coolie);

    abstract public RunResults getRunResults();

    abstract protected void setProperties(RobotProperties properties);

    abstract protected void inputLinkInCollection(String url);

}
