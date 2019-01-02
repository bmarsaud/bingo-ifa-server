package fr.bmarsaud.entity;

public class Sentence {
    private int id;
    private String label;
    private int upVotes;
    private int downVotes;
    private boolean activated;

    public Sentence(int id, String label, int upVotes, int downVotes, boolean activated) {
        this.id = id;
        this.label = label;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.activated = activated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
