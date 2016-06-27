package xyz.qjex.cfutils.stats;

import xyz.qjex.cfutils.Submission;
import xyz.qjex.cfutils.User;
import xyz.qjex.cfutils.methods.ApiMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qjex on 2/24/16.
 */
public class TaskRate {

    private ArrayList<User> users;
    private HashMap<String, Double> cost;
    private double defaultRate;
    public TaskRate(ArrayList<User> users, double defaultRate) {
        this.defaultRate = defaultRate;
        this.users = users;
        cost = new HashMap<>();
    }

    public TaskRate(ArrayList<User> users, HashMap<String, Double> cost, double defaultRate) {
        this.defaultRate = defaultRate;
        this.users = users;
        this.cost = cost;
    }

    public ArrayList<RateResult> getRate() {
        ArrayList<RateResult> result = new ArrayList<>();
        for (User user : users) {
            System.out.println("Calculating " + user.getHandle());
            result.add(getUserRate(user));
        }
        return result;
    }

    private RateResult getUserRate(User user) {
        RateResult result = new RateResult(user);
        try {
            ArrayList<Submission> allSubmissions = ApiMethods.getUserStatus(user);
            HashMap<String, Integer> count = new HashMap<>();
            for (Submission submission : allSubmissions) {
                if (submission.getVerdict().equalsIgnoreCase("OK")) {
                    String index = submission.getProblem().getIndex();
                    if (count.containsKey(index)) {
                        count.put(index, count.get(index) + 1);
                    } else count.put(index, 1);
                }
            }
            result.setCount(count);
            HashMap<String, Double> rate = new HashMap<>();
            double sum = 0;
            for (Map.Entry<String, Integer> entry : count.entrySet()) {
                String index = entry.getKey();
                double value = (cost.containsKey(index) ? cost.get(index) : defaultRate) * entry.getValue();
                rate.put(index, value);
                sum += value;

            }
            result.setRate(rate);
            result.setSum(sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
