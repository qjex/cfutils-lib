package xyz.qjex.cfutils;

import org.json.JSONObject;
import xyz.qjex.cfutils.methods.ApiMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by qjex on 5/31/16.
 */
public class Contest {

    private static Logger logger = Logger.getLogger(Contest.class.getName());

    private long id, durationSeconds, startTimeSeconds, relativeTimeSeconds;
    private String name;
    private boolean isGym;
    private ArrayList<Submission> submissions;

    public Contest(long id, boolean isGym) {
        this.id = id;
        this.isGym = isGym;
//        if (getAllInfo) {
//            updateInfo();
//        }
    }

    public Contest(JSONObject json) {
        id = json.getLong("id");
        name = json.getString("name");
        durationSeconds = json.getLong("durationSeconds");
        startTimeSeconds = json.getLong("startTimeSeconds");
        relativeTimeSeconds = json.getLong("relativeTimeSeconds");
    }

    public ArrayList<Submission> getSubmissions() {
        if (submissions == null) {
            try {
                submissions = ApiMethods.getContestStatus(this);
            } catch (IOException e) {
                logger.warning("Can't get contest status");
                e.printStackTrace();
            }
        }

        return submissions;
    }

    public String getName() {
        return name;
    }

    public boolean isGym() {
        return isGym;
    }

    public long getDurationSeconds() {
        return durationSeconds;
    }

    public long getStartTimeSeconds() {
        return startTimeSeconds;
    }

    public long getRelativeTimeSeconds() {
        return relativeTimeSeconds;
    }

    public long getId() {
        return id;
    }
}
