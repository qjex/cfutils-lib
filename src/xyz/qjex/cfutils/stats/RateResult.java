package xyz.qjex.cfutils.stats;

import xyz.qjex.cfutils.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qjex on 2/24/16.
 */
public class RateResult implements Comparable<RateResult> {
    private User user;
    private double sum;
    private boolean showT = true;
    private HashMap<String, Integer> count;
    private HashMap<String, Double> rate;

    RateResult(User user) {
        this.user = user;
    }

    public void setCount(HashMap<String, Integer> count) {
        this.count = count;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setRate(HashMap<String, Double> rate) {
        this.rate = rate;
    }

    public double getSum() {
        return sum;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(user.getHandle() + " " + sum + "\n");
        if (showT) {
            for (Map.Entry<String, Integer> entry : count.entrySet()) {
                String index = entry.getKey();
                long cnt = entry.getValue();
                if (rate.get(index) == 0) continue;
                result.append(" " + index + " " + cnt + " " + rate.get(index) + "\n");
            }
        }
        return result.toString();
    }

    @Override
    public int compareTo(RateResult o) {
        return Double.valueOf(o.sum).compareTo(this.sum);
    }

    public void showTasks(boolean b) {
        showT = b;
    }
}
