package govitallLinkRunner;

import govitallLinkRunner.core.WebResource;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ykolesnik on 01.10.2015.
 */
public class Parser {



    public ArrayList<String> getDatas()  {
        ArrayList<String> titles = new ArrayList<String>();

        titles.add("http://paramountessays.com/work_for_us");
        titles.add("http://paramountessays.com/blog/essay/custom-essays");
        titles.add("http://paramountessays.com/blog/essay/custom-written-essays");
        titles.add("http://paramountessays.com/blog/essay/free-essays");
        titles.add("http://paramountessays.com/index_new");
        titles.add("http://paramountessays.com/blog/essay/essay-editing-service");
        titles.add("http://paramountessays.com/blog/essay/cheap-term-paper");
        titles.add("http://paramountessays.com/essay");
        titles.add("http://paramountessays.com/blog/essay/term-paper-proposal-format");
        titles.add("http://paramountessays.com/blog/essay/online-essay");
        titles.add("http://paramountessays.com/blog/essay/essay-help");
        titles.add("http://paramountessays.com/blog/essay/write-my-essay");
        titles.add("http://paramountessays.com/blog/essay/essay-writers");
        titles.add("http://paramountessays.com/blog/essay/custom-essay");
        titles.add("http://paramountessays.com/blog/essay/order-essay");
        titles.add("http://paramountessays.com/blog/essay/essay-writing");
        titles.add("http://paramountessays.com/blog/essay/research-essay");
        titles.add("http://paramountessays.com/blog/essay/essay-service");
        titles.add("http://paramountessays.com/blog/essay/essay-writing-help");
        titles.add("http://paramountessays.com/blog/essay/written-essay");
        titles.add("http://paramountessays.com/blog/essay/essay-online");
        titles.add("http://paramountessays.com/blog/essay/order-essay-2");
        titles.add("http://paramountessays.com/blog/essay/essay-website");
        titles.add("http://paramountessays.com/blog/essay/coursework-essay");
        titles.add("http://paramountessays.com/blog/essay/professional-essay");
        titles.add("http://paramountessays.com/blog/essay/essay-expert");
        titles.add("http://paramountessays.com/blog/essay/writing-essays");
        titles.add("http://paramountessays.com/blog/essay/writing-term-paper");
        titles.add("http://paramountessays.com/blog/essay/writing-a-term-paper");
        titles.add("http://paramountessays.com/blog/essay/written-term-paper");
        titles.add("http://paramountessays.com/blog/essay/term-paper-research");

        return titles;
    }
}
