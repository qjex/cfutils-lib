package xyz.qjex.cfutils;

/**
 * Created by qjex on 2/24/16.
 */

public class User {
    private String handle, email, firstName, lastName, country, city, organization, rank, maxRank;
    private  long registrationTimeSeconds, lastOnlineTimeSeconds, rating, contribution;

    public String getEmail() {
        return email;
    }

    public String getHandle() {
        return handle;
    }

    public String getCountry() {
        return country;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public User(String handle) {
        this.handle = handle;
    }
}
