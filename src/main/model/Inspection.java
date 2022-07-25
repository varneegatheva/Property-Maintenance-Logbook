package model;

// Represents an inspection entry of a property
public class Inspection {
    String date;
    String plumbing;
    String drywall;
    String addedNotes;

    // REQUIRES: date must have format MMDDYYYY
    // MODIFIES: this
    // EFFECTS: constructs an inspection with a date, plumbing info, drywall info and added notes
    public Inspection(String date, String plumbing, String drywall, String addedNotes) {
        this.date = date;
        this.drywall = drywall;
        this.addedNotes = addedNotes;
        this.plumbing = plumbing;
    }

    public String getDrywall() {
        return drywall;
    }

    public String getAddedNotes() {
        return addedNotes;
    }

    public String getPlumbing() {
        return plumbing;
    }

    public String getDate() {
        return date;
    }

    // EFFECTS: returns a string representation of inspection
    public String toString() {
        return " Date: " + this.date + "\n Plumbing: " + this.plumbing + "\n Drywall: " + this.drywall
                + "\n Additional Notes: " + this.addedNotes;
    }
}
