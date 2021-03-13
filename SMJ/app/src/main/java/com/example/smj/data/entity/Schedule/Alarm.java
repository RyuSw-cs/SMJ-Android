package com.example.smj.data.entity.Schedule;

public class Alarm {
    private String content;
    private String day;
    private String endTime;
    private int id;
    private String repeat;
    private String startTime;
    private String title;

    public Alarm(String content, String day, String endTime, int id, String repeat, String startTime, String title) {
        this.content = content;
        this.day = day;
        this.endTime = endTime;
        this.id = id;
        this.repeat = repeat;
        this.startTime = startTime;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
