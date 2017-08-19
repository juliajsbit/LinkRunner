package govitallLinkRunner.core.utils.print;


import govitallLinkRunner.core.WebResource;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//TODO Singleton
public class PrintResult {

    private PrintToFile printToFile = new PrintToFile();

    //header
    public void header(String baseURL, int validationDepth) {
        printToFile.printToFile("LinkRunner Report (" + java.util.Calendar.getInstance().getTime() + ")\n" + "Project: " + baseURL + "\n" + "Depth = " + validationDepth); //TODO Start check, End check, spend time
    }

    public void spendTime(String srart, String end, String spendTime) {
        printToFile.printToFile("---------------------------------------------------\n\nStart : " + srart + "\nEnd : " + end +"\nSpent time: "+spendTime+"\n---------------------------------------------------");
    }

    //statistics
    public void statisticsInternalLinks(int checkedLinks, int uncheckedLinks, int errorLinks) {
        printToFile.printToFile("STATISTICS\n\n"+"---------------------------------------------------\n" + "Checked internal links: " + checkedLinks + "\nUnchecked internal links: " + uncheckedLinks + "\nError internal links: " + errorLinks+"\n---------------------------------------------------");
    }

    public void statisticsInternalNonPageLinks(int checkedLinks, int errorLinks, boolean check){
        if(check) {
            printToFile.printToFile("Checked internal non page links: " + checkedLinks + "\nError internal non page links: " + errorLinks + "\n---------------------------------------------------");
        }
    }

    public void statisticsEnternalLinks(int checkedLinks, int errorLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nChecked external links: " + checkedLinks + "\nError external links: " + errorLinks+"\n---------------------------------------------------");
        } else {
        }
    }

    public void statisticsExternalNonPageLinks(int checkedLinks, int errorLinks, boolean check){
        if(check) {
            printToFile.printToFile("Checked external non page links: " + checkedLinks + "\nError external non page links: " + errorLinks + "\n---------------------------------------------------");
        }
    }

    public void statisticsIMGlinks(int checkedLinks, int errorLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nChecked IMG links: " + checkedLinks + "\nError IMG links: " + errorLinks+"\n---------------------------------------------------");
        } else {
        }
    }

    public void statisticsJSlinks(int checkedLinks, int errorLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nChecked JS links: " + checkedLinks + "\nError JS links: " + errorLinks+"\n---------------------------------------------------");
        } else {
        }
    }

    public void statisticsCSSlinks(int checkedLinks, int errorLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nChecked CSS links: " + checkedLinks + "\nError CSS links: " + errorLinks+"\n---------------------------------------------------");
        } else {
        }
    }

    public void statisticW3cValidation(int checkedLinks, boolean check){
        if(check){
            printToFile.printToFile("\nInternal links with W3cValidation error: " +checkedLinks +"\n");
        }
    }

    //error
    public void errorInternalLinks(Map<String, WebResource> errorInternalLinks) {
            printToFile.printToFile("\nInternal links with error === " + errorInternalLinks.size() + " ===\n", errorInternalLinks, true); //TODO разделители
            printToFile.printToFile("\n---------------------------------------------------\n");
    }

    public void errorExternalLinks(Map<String, WebResource> errorExternalLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nExternal links with error === " + errorExternalLinks.size() + " ===\n", errorExternalLinks, true);
            printToFile.printToFile("\n---------------------------------------------------\n");
        }
    }

    public void errorInternalNonPageinks(Map<String, WebResource> errorInternalNonPageLinks){
        printToFile.printToFile("\nInternal non page links error === "+errorInternalNonPageLinks.size()+" ===\n", errorInternalNonPageLinks, true);
        printToFile.printToFile("\n---------------------------------------------------\n");
    }
    public void errorExternalNonPageinks(Map<String, WebResource> errorExternalNonPageLinks, boolean check){
        if(check) {
            printToFile.printToFile("\nExternal non page links error === " + errorExternalNonPageLinks.size() + " ===\n", errorExternalNonPageLinks, true);
            printToFile.printToFile("\n---------------------------------------------------\n");
        }
    }

    public void errorIMGLinks(Map<String, WebResource> errorImageLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nImage links with error === " + errorImageLinks.size() + " ===\n", errorImageLinks, true);
            printToFile.printToFile("\n---------------------------------------------------\n");
        }
    }

    public void errorJSLinks(Map<String, WebResource> errorJSLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nJavaScript links with error === " + errorJSLinks.size() + " ===\n", errorJSLinks, true);
            printToFile.printToFile("\n---------------------------------------------------\n");
        }
    }

    public void errorCSSLinks(Map<String, WebResource> errorCSSLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nCSS links with error === " + errorCSSLinks.size() + " ===\n", errorCSSLinks, true);
            printToFile.printToFile("\n---------------------------------------------------\n");
        }
    }

    public void errorW3cValidation(Map<String, WebResource> errorW3cValidationLinks, boolean check){ //TODO
        if(check){
            printToFile.printToFile("\nLinks with W3cValidation error === "+errorW3cValidationLinks.size() + "===\n", errorW3cValidationLinks, true);
        }
    }

    //internal
    public void checkedInternalLink(Map<String, WebResource> internalLinks) {
            printToFile.printToFile("\nTotal of numbers links with OK response === " + internalLinks.size() + " ===\n", internalLinks, false);
            printToFile.printToFile("\n---------------------------------------------------\n");
    }

    public void uncheckedInternalLink(Map<String, WebResource> uncheckedLinks) {
            printToFile.printToFile("\nUnchecked links === " + uncheckedLinks.size() + " ===\n", uncheckedLinks, false);
            printToFile.printToFile("\n---------------------------------------------------\n");
    }

    //notPage
    public void internalNonPageLinks(Map<String, WebResource> internalNonPageLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nInternal not page links with OK response=== " + internalNonPageLinks.size() + " ===\n", internalNonPageLinks, false);
            printToFile.printToFile("\n---------------------------------------------------\n");
        }
    }

    public void externalNonPageLinks(Map<String, WebResource> externalNonPageLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nExternal not page links with OK response === " + externalNonPageLinks.size() + " ===\n", externalNonPageLinks, false); //TODO параметры проверки
            printToFile.printToFile("\n---------------------------------------------------\n");
        }
    }

    //external
    public void checkedEnternalLink(Map<String, WebResource> externalLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nExternal Links with OK response === " + externalLinks.size() + " ===\n", externalLinks, false);
            printToFile.printToFile("\n---------------------------------------------------\n");
        }
    }

    //ga
    public void printGA(Map<String, WebResource> internalLinks, boolean check) {
        if (check) {
            printToFile.printGoogleAnalyticsToFile("\n\nGOOGLE Analytics \n", internalLinks);
        }
    }

    //image
    public void checkedIMGLink(Map<String, WebResource> imageLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nImage links with OK response === " + imageLinks.size() + " ===\n", imageLinks, false);
            printToFile.printToFile("\n---------------------------------------------------\n");
        }
    }

    //css
    public void checkedCSSLink(Map<String, WebResource> cssLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nCSS links with OK response=== " + cssLinks.size() + " ===\n", cssLinks, false);
            printToFile.printToFile("\n---------------------------------------------------\n");
        }
    }
    //js
    public void checkedJSLink(Map<String, WebResource> jsLinks, boolean check) {
        if (check) {
            printToFile.printToFile("\nJS links with OK response === " + jsLinks.size() + " ===\n", jsLinks, false);
            printToFile.printToFile("\n---------------------------------------------------\n");
        }
    }
}
