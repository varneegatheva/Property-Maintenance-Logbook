package model;

import java.util.ArrayList;
import java.util.List;

// Represents a property with different attributes
public class Property {

    String address;
    int size;
    List<Inspection> inspections;

    //EFFECTS: constructs a new property, with an empty list of inspections
    public Property() {
        this.inspections = new ArrayList<>();
    }



    public List<Inspection> getInspections() {
        return inspections;
    }

    // MODIFIES: this
    // EFFECTS: searches inspection list by date and adds an inspection to list if it already doesn't exist
    public void addInspection(Inspection inspection) {
        for (Inspection i : inspections) {
            if (i.getDate().equals(inspection.getDate())) {
                System.out.println(
                        "Inspection with that date already exists for this property, returning to main menu");
                return;
            }
        }
        inspections.add(inspection);
        System.out.println("Inspection added to property");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // MODIFIES: this
    // EFFECTS: searches inspection list by date; returns inspection if found, otherwise returns null
    public Inspection getInspectionByDate(String date) {
        for (Inspection inspection : inspections) {
            if (inspection.getDate().equals(date)) {
                return inspection;
            }
        }
        return null;
    }

    // EFFECTS: returns a string representation of property
    public String toString() {
        return " Property: " + this.address + "\n Size: " + this.size;
    }

}
