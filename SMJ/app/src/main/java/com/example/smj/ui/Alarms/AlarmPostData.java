package com.example.smj.ui.Alarms;

public class AlarmPostData {
    private String content;
    private String startDate;
    private String endTime;
    private String repeat;
    private String startTime;
    private String title;

    public AlarmPostData(String title, String content, String startDate,String startTime, String endTime, String repeat) {
        this.content = content;
        this.startDate = startDate;
        this.endTime = endTime;
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

    public String getstartDate() {
        return startDate;
    }

    public void setstartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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