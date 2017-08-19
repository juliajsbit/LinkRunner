package govitallLinkRunner;


import java.util.ArrayList;
import java.util.HashMap;

public abstract class MainData {

    protected String baseURL;
    protected int validationDepth ;
    protected boolean checkexterlinks;
    protected boolean checkimg ;
    protected boolean checkcss ;
    protected boolean checkjs ;
    protected boolean checkGA ;

    protected static ArrayList listURLs= new ArrayList();

}
