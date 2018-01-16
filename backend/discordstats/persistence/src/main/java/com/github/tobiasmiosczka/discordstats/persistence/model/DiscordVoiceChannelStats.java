package com.github.tobiasmiosczka.discordstats.persistence.model;

public class DiscordVoiceChannelStats {
    private long count;
    private long sum;
    private long max;
    private double average;

    public DiscordVoiceChannelStats(Long count, Long totalTime, Long max, Double average) {
        this.count = (count != null) ? count : 0;
        this.sum = (totalTime != null) ? totalTime : 0;
        this.max = (max != null) ? max : 0;
        this.average = (average != null) ? average : 0;

    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
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
