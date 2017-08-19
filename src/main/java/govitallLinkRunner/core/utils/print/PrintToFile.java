package govitallLinkRunner.core.utils.print;

import govitallLinkRunner.core.WebResource;

import java.io.*;
import java.util.Date;
import java.util.Map;

public class PrintToFile {

    String nameFile = getFileName();
    File file = new File(nameFile);

    // TODO. Add doc
    public void printToFile(String message, Map<String, WebResource> map, Boolean status) {
        try {
            FileOutputStream ty = new FileOutputStream(nameFile, true);
            PrintWriter out = new PrintWriter(ty);
            try {
                out.append(message);
                if (status) {
                    for (Map.Entry<String, WebResource> entry : map.entrySet()) {
                        out.append(entry.getKey() + "\n");
                        out.append("Refer: " + entry.getValue().getParentLink() + "\n");
                        out.append("Response code: " + entry.getValue().getHttpStatusCode() + "\n");

                        if (entry.getValue().getModuleResults().containsKey("GA") ) { //TODO
                            out.append(entry.getValue().getModuleResultsByName("GA").getMessage());
                        }
                    }
                }else  {
                    for (Map.Entry<String, WebResource> entry : map.entrySet()) {
                        out.append(entry.getKey() + "\n");

                        if (entry.getValue().getModuleResults().containsKey("GA") ) {
                            out.append(entry.getValue().getModuleResultsByName("GA").getMessage());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO. Add doc
    public void printToFile(String message) {
        try {
            FileOutputStream ty = new FileOutputStream(nameFile, true);
            PrintWriter out = new PrintWriter(ty);
            try {
                message += "\n";
                out.append(message);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printGoogleAnalyticsToFile(String message, Map<String, WebResource> map){
        try {
            FileOutputStream ty = new FileOutputStream(nameFile, true);
            PrintWriter out = new PrintWriter(ty);
            try {
                out.append(message);
                    for (Map.Entry<String, WebResource> entry : map.entrySet()) {
                        if (entry.getValue().getModuleResults().containsKey("GA") ) {
                            out.append("Google analytics results:\n");
                            out.append(entry.getValue().getModuleResultsByName("GA").getMessage());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO. Add doc
    public void printConsole(Object string) {
        //System.out.println(string);
    }

    // TODO. Add doc
    public String getFileName() {
        String fileName = new Date(System.currentTimeMillis()).toString();
        String dataName = (fileName.replace(" ", "_")).replace(":", "_");
        String fileNam = "C:/LinkRunnerLog/" + dataName + ".txt"; //TODO file type
        //String fileNam = "C:/prime/linkrunner/log/"+dataName+".txt"; //TODO file type
        return fileNam;
    }

    // TODO. Add doc
    public String getAbsolutePathToResult() {
        try {
            return file.getCanonicalPath();
        } catch (Exception e) {
            return "Error getting file path";
        }
    }

}
