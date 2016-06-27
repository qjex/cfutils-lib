package xyz.qjex.cfutils;

import org.bson.Document;
import org.json.JSONObject;

import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

/**
 * Created by qjex on 2/24/16.
 */

public class Submission {

    private static Logger logger = Logger.getLogger(Submission.class.getName());

    private long id, contestId, creationTimeSeconds, relativeTimeSeconds, timeConsumedMillis, memoryConsumedBytes, passedTestCount;
    private String verdict, programmingLanguage;
    private Problem problem;
    private SourceCode sourceCode;

    public Submission(long contestId, long id, String verdict, String programmingLanguage) {
        this.contestId = contestId;
        this.id = id;
        this.verdict = verdict;
        this.programmingLanguage = programmingLanguage;
    }

    public Submission(JSONObject json) {
        verdict = json.getString("verdict");
        programmingLanguage = json.getString("programmingLanguage");
        id = json.getLong("id");
        contestId = json.getLong("contestId");
        problem = new Problem(json.getJSONObject("problem"));
        creationTimeSeconds = json.getLong("creationTimeSeconds");
        relativeTimeSeconds = json.getLong("relativeTimeSeconds");
        timeConsumedMillis = json.getLong("timeConsumedMillis");
        memoryConsumedBytes = json.getLong("memoryConsumedBytes");
        passedTestCount = json.getLong("passedTestCount");
    }

    public void saveSourceCode(DBConnection dbConnection) {
        saveSubmission(dbConnection);
        dbConnection.updateOne("submissions", eq("id", id), set("src", getSourceCode().getCode()));
    }

    public SourceCode getSourceCode() {
        if (sourceCode == null) sourceCode = new SourceCode("contest", contestId, id);
        return sourceCode;
    }

    public void saveSubmission(DBConnection dbConnection) {
        if (dbConnection.find("submissions", eq("id", id)).size() == 0) {
            dbConnection.insert("submissions", toDocument());
            logger.info("Saving submission: " + id);
        }

    }

    public Document toDocument() {
        Document result = new Document("id", id)
                            .append("contestId", contestId)
                            .append("verdict", verdict)
                            .append("programmingLanguage", programmingLanguage);
        if (sourceCode != null) {
            result.append("src", sourceCode.getCode());
        }
        return result;
    }

    public String getVerdict() {
        return verdict;
    }

    public Problem getProblem() {
        return problem;
    }

    public long getContestId() {
        return contestId;
    }

    public long getId() {
        return id;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public long getRelativeTimeSeconds() {
        return relativeTimeSeconds;
    }

    public long getMemoryConsumedBytes() {
        return memoryConsumedBytes;
    }

    public long getCreationTimeSeconds() {
        return creationTimeSeconds;
    }

    public long getPassedTestCount() {
        return passedTestCount;
    }

    public long getTimeConsumedMillis() {
        return timeConsumedMillis;
    }



}

