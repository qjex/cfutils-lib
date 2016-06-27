package xyz.qjex.cfutils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Formatter;
import java.util.logging.Logger;

/**
 * Created by qjex on 5/26/16.
 */
public class SourceCode{

    private static Logger logger = Logger.getLogger(SourceCode.class.getName());

    private final String urlTemplate = "http://codeforces.com/%s/%s/submission/%s";
    private String code, contestType;
    private long contestId, id;

    public SourceCode(String contestType, long contestId, long id) {
        this.contestType = contestType;
        this.contestId = contestId;
        this.id = id;
        String url = new Formatter().format(urlTemplate, contestType, contestId, id).toString();
        String code = null;
        try {
            code = parsePage(url);
            if (code.length() == 0) logger.warning("Error parsing submission src: " + url);
        } catch (IOException e) {
            logger.warning("Error parsing submission src: " + url);
            e.printStackTrace();
        }
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private String parsePage(String url) throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
        Element srcPre = doc.getElementsByClass("program-source").first();
        return srcPre.text();
    }


}
