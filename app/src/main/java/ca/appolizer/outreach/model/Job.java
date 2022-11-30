package ca.appolizer.outreach.model;

public class Job {
    private String title;
    private String subtitle;

    public Job() {
    }

    public Job(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle.length() > 24 ? subtitle.substring(0, 25) + "..." : subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
