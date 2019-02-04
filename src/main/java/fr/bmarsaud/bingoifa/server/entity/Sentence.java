package fr.bmarsaud.bingoifa.server.entity;

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

    public Sentence(String label, int upVotes, int downVotes, boolean activated) {
        this.id = -1;
        this.label = label;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.activated = activated;
    }

    public Sentence() {
        this.id = -1;
        this.label = null;
        this.upVotes = -1;
        this.downVotes = -1;
        this.activated = false;
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

    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Sentence)) return false;
        if(obj == this) return true;

        Sentence sentence = (Sentence) obj;
        return sentence.getId() == id && sentence.getLabel().equals(label) && sentence.getDownVotes() == downVotes && sentence.getUpVotes() == upVotes && sentence.isActivated() == activated;
    }

    public String toString() {
        return "Sentence{id=" + id + ", label=\"" + label + "\", downVotes=" + downVotes + ", upVotes=" + upVotes + ", activated=" + activated + "}";
    }
}
