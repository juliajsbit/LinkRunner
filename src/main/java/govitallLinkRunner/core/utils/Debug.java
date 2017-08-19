package govitallLinkRunner.core.utils;


public class Debug {

    public  void debugPrint(String message){
        if (!GloballSettings.getMODE().equals(GloballSettings.PRODUCTION)) {
            System.out.println(message);
        }
    }

    public  boolean productionMode(){
        if (GloballSettings.getMODE().equals(GloballSettings.PRODUCTION)) {
            return true;
            }else {
            return false;
        }
    }

    public  void verifyArgsLength(Integer argsLength){
        if (argsLength == 0) {
            System.out.println("Please, enter URL, depth and other check parameters!");
            System.exit(0);
        }
    }
}
