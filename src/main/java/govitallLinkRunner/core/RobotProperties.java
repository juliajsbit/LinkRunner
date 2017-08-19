package govitallLinkRunner.core;


public class RobotProperties extends RobotPropertiesCore {

    public RobotProperties(String baseURL, Integer validationDepth, Boolean checkExternalLinks, Boolean checkImage, Boolean checkCSS, Boolean checkJS, Boolean checkGA, Boolean validation) {
        this.baseURL = baseURL;
        this.status = 0;
        this.depth = validationDepth;
        this.checkExternalLinks = checkExternalLinks;
        this.checkImage = checkImage;
        this.checkCSS = checkCSS;
        this.checkJS= checkJS;
        this.checkGA = checkGA;
        this.validation = validation;
    }

    public int getDepth() {
        return this.depth;
    }

    public boolean getCheckExternalLinks(){
        return this.checkExternalLinks;
    }

    public boolean getCheckImage(){
        return this.checkImage;
    }

    public boolean getCheckCSS(){
        return this.checkCSS;
    }

    public boolean getCheckJS(){
        return this.checkJS;
    }

    public boolean getCheckGA(){
        return checkGA;
    }

    public boolean getValidation(){
        return validation;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    private String baseURL;
    private Integer status;
    private int depth;
    private boolean checkExternalLinks;
    private boolean checkImage;
    private boolean checkCSS;
    private boolean checkJS;
    private boolean checkGA;
    private boolean validation;
}
