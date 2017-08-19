package govitallLinkRunner.core.utils;


import java.util.Date;

public class Time {

    long startTime;
    long endTime;


    public String getStartTime() {
        Date start = new Date();
        startTime = start.getTime();
        return  convertDate(start.getTime());
    }

    public String getEndTime() {
        Date end = new Date();
        endTime = end.getTime();
        return convertDate(end.getTime());
    }

    public String getSpendTime() {
        long diffSeconds = (endTime / 1000 % 60) - (startTime / 1000 % 60);
        long diffMinutes = (endTime / (60 * 1000) % 60) - (startTime / (60 * 1000) % 60);
        long diffHours = (endTime / (60 * 60 * 1000) % 24) - (startTime / (60 * 60 * 1000) % 24);
        if (diffSeconds < 0) {
            diffMinutes = diffMinutes - 1;
            diffSeconds = 60 + diffSeconds;
        }
        if (diffMinutes < 0) {
            diffMinutes = (endTime / (60 * 1000) % 60) + (60 - (startTime / (60 * 1000) % 60));
        }
        String f = diffHours + ":" + diffMinutes + ":" + diffSeconds;
        return f;
    }
    public String convertDate(long diff) {
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        String f = diffHours + ":" + diffMinutes + ":" + diffSeconds;
        return f;
    }
}


/*public String getStartTime() {
        Calendar dating = Calendar.getInstance();
        SimpleDateFormat formating = new SimpleDateFormat("HH:mm:ss");
        String start = formating.format(dating.getTime());
        return start;
    }

    public String getEndTime() {
        Calendar dating2 = Calendar.getInstance();
        SimpleDateFormat formating2 = new SimpleDateFormat("HH:mm:ss");
        String and = formating2.format(dating2.getTime());
        return and;
    }

    public String getSpendTime(String start, String end){
        String [] e = end.split(":");
        String [] s = start.split(":");
        int h = Integer.valueOf(e[0]) - Integer.valueOf(s[0]);
        int m = Integer.valueOf(e[1]) - Integer.valueOf(s[1]);
        int ss = Integer.valueOf(e[2]) - Integer.valueOf(s[2]);
        String f = h+":"+m+":"+ss;
        return f;
        }*/
