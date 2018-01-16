package com.github.tobiasmiosczka.discordstats.persistence.model;

public class DiscordGuildStats {
    private long count;
    private long sum;
    private long max;
    private double average;

    public DiscordGuildStats(Long connections, Long totalTime, Long max, Double average) {
        this.count = (connections != null) ? connections : 0;
        this.sum = (totalTime != null) ? totalTime : 0;
        this.max = (max != null) ? max : 0;
        this.average = (average != null) ? average : 0;

    }

    public long getCount() {
        return count;
    }

    public void setCount(long sum) {
        this.count = sum;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }
}
