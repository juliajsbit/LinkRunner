package govitallLinkRunner.core.utils;


public class GloballSettings { //TODO Singleton

    public static String DEBUG = "DEBUG";
    public static String PRODUCTION = "PRODUCTION";

    private static String MODE = DEBUG;

    public static String getMODE() {
        return MODE;
    }

    public static void setMODE(String MODE) {
        GloballSettings.MODE = MODE;
    }
}
