package govitallLinkRunner;

import govitallLinkRunner.core.RobotProperties;
import govitallLinkRunner.core.utils.Debug;
import govitallLinkRunner.core.utils.HttpProcessing;

import java.util.ArrayList;

public class Main extends MainData {

    public static String baseURL;
    public static int validationDepth;
    public static boolean checkexterlinks;
    public static boolean checkimg ;
    public static boolean checkcss;
    public static boolean checkjs;
    public static boolean checkGA ;
    public static boolean validation;

    public static void main(String[] args) throws Exception{
        Debug debug = new Debug();
        if (debug.productionMode()) {
            debug.verifyArgsLength(args.length);

            for (int i = 0; i < args.length; i += 2) {
                if (args[i].equals("-url")) {
                    baseURL=(args[i + 1]);
                } else if (args[i].equals("-depth")) {
                    validationDepth = Integer.parseInt(args[i + 1]);
                } else if (args[i].equals("-checkexterlinks")) {
                    if (args[i + 1].equals("true")) {
                        checkexterlinks = true;
                    } else {
                        checkexterlinks = false;
                    }
                } else if (args[i].equals("-checkimg")) {
                    if (args[i + 1].equals("true")) {
                        checkimg = true;
                    } else {
                        checkimg = false;
                    }
                } else if (args[i].equals("-checkcss")) {
                    if (args[i + 1].equals("true")) {
                        checkcss = true;
                    } else {
                        checkcss = false;
                    }
                } else if (args[i].equals("-checkjs")) {
                    if (args[i + 1].equals("true")) {
                        checkjs = true;
                    } else {
                        checkjs = false;
                    }
                } else if (args[i].equals("-checkga")) {
                    if (args[i + 1].equals("true")) {
                        checkGA = true;
                    } else {
                        checkGA = false;
                    }
                } else if (args[i].equals("-validation")) {
                    if (args[i + 1].equals("true")) {
                        checkGA = true;
                    } else {
                        checkGA = false;
                    }
                } else {
                    System.out.println("Illegal instruction " + args[i]);
                    System.exit(0); //TODO print print -help
                }
            }
        }

        for(int i = 0; i<listURLs.size(); i++){
            baseURL = (String)listURLs.get(i);
        }
        HttpProcessing httpProcessing = new HttpProcessing();
        String coolie = httpProcessing.getCooklie();
        GovitallRobot parseTest = new GovitallRobot();
        parseTest.init(baseURL, new RobotProperties(baseURL, validationDepth, checkexterlinks, checkimg, checkcss, checkjs, checkGA, validation));
        parseTest.run(0, coolie);
    }
    public static Integer getNumberOfUrls(){
        return listURLs.size();
    }
}





