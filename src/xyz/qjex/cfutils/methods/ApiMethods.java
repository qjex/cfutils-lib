package xyz.qjex.cfutils.methods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xyz.qjex.cfutils.Contest;
import xyz.qjex.cfutils.Submission;
import xyz.qjex.cfutils.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by qjex on 2/24/16.
 */
public class ApiMethods {
    private final static String userStatusURL = "http://codeforces.com/api/user.status";
    private final static String contestStatusURL = "http://codeforces.com/api/contest.status";
    private final static String contestListURL = "http://codeforces.com/api/contest.list";

    public static ArrayList<Submission> getUserStatus(User user) throws IOException, JSONException {
        StringBuilder query = new StringBuilder(userStatusURL);
        query.append("?handle=" + user.getHandle());
        String result = HTTPMethods.get(query.toString());
        JSONObject json = new JSONObject(result);
        JSONArray submissions = json.getJSONArray("result");
        ArrayList<Submission> userStatus = new ArrayList<>();

        for (Object submission : submissions) {
            userStatus.add(new Submission((JSONObject) submission));
        }
        return userStatus;
    }

    public static ArrayList<Submission> getContestStatus(Contest contest) throws IOException, JSONException {
        StringBuilder query = new StringBuilder(contestStatusURL);
        query.append("?contestId=" + contest.getId());
        String result = HTTPMethods.get(query.toString());
        JSONObject json = new JSONObject(result);
        JSONArray submissions = json.getJSONArray("result");
        ArrayList<Submission> contestStatus = new ArrayList<>();

        for (Object submission : submissions) {
            contestStatus.add(new Submission((JSONObject) submission));
        }
        return contestStatus;
    }

    public static ArrayList<Contest> getContestList(boolean isGym) throws IOException, JSONException {
        StringBuilder query = new StringBuilder(contestListURL);
        if (isGym) query.append("?gym=true");
        String result = HTTPMethods.get(query.toString());
        JSONObject json = new JSONObject(result);
        JSONArray contests = json.getJSONArray("result");
        ArrayList<Contest> contestList = new ArrayList<>();

        for (Object contest : contests) {
            contestList.add(new Contest((JSONObject) contest));
        }
        return contestList;
    }

    public static Contest getContest(long id) throws IOException, JSONException {
        ArrayList<Contest> contests = getContestList(false);
        for (Contest contest : contests) {
            if (contest.getId() == id) return contest;
        }
        return null;
    }







}
