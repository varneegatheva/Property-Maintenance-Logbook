package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Inspection;
import model.Property;

// Represents a console version of the application
public class Logbook {

    private List<Property> listProperty;
    private Scanner scanner;

    // MODIFIES: this
    // EFFECTS: initializes properties and runs app
    public Logbook() {
        listProperty = new ArrayList<Property>();
        scanner = new Scanner(System.in);
        runLogBook();
        scanner.close();
    }

    public static void logbookMenu() {
        System.out.println();
        System.out.println("Please choose from the following options: ");
        System.out.println("1. Create a new property");
        System.out.println("2. View all properties");
        System.out.println("3. Add inspection to a property");
        System.out.println("4. View all inspections for a property");
        System.out.println("5. View inspection by date for a property");
        System.out.println("6. Exit");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLogBook() {
        boolean runApp = true;
        System.out.println("Welcome to Your Property Logbook");
        while (runApp) {
            logbookMenu();
            String menuItem = scanner.nextLine();
            if (menuItem.equals("1")) {
                createProperty();
            } else if (menuItem.equals("2")) {
                viewProperties();
            } else if (menuItem.equals("3")) {
                addInspectionToProperty();
            } else if (menuItem.equals("4")) {
                viewInspectionsForProperty();
            } else if (menuItem.equals("5")) {
                viewInspectionByDate();
            } else if (menuItem.equals("6")) {
                runApp = false;
                System.out.println("Property Logbook Closed");

            } else {
                System.out.println("Please enter a valid menu option");
            }
        }
    }

    private void createProperty() {
        Property p = new Property();
        System.out.println("Enter property address: ");
        String propertyAddress = scanner.nextLine();
        p.setAddress(propertyAddress);
        System.out.println("Enter property size: ");
        p.setSize(scanner.nextInt());
        System.out.println("Property with address " + propertyAddress + " was created");
        addPropertyToList(p);
    }

    private void viewProperties() {
        for (Property p : listProperty) {
            System.out.println("\n");
            System.out.println(p.toString());
        }

    }

    private void addInspectionToProperty() {
        System.out.println("Enter property address: ");
        String propertyAddress = scanner.nextLine();
        Property property = findProperty(propertyAddress);
        if (property == null) {
            System.out.println("Property not found, return to main menu");
            return;
        }

        System.out.println("Enter date in format MMDDYYYY: ");
        String date = scanner.nextLine();
        if (date.length() != 8) {
            System.out.println("Invalid date format, returning to main menu");
            return;
        }

        System.out.println("What condition is the plumbing in?");
        String plumbing = scanner.nextLine();
        System.out.println("What condition is the drywall in?");
        String drywall = scanner.nextLine();
        System.out.println("Additional Notes: ");
        String notes = scanner.nextLine();

        Inspection inspection = new Inspection(date, plumbing, drywall, notes);
        property.addInspection(inspection);

    }

    private void viewInspectionsForProperty() {
        System.out.println("Enter property address: ");
        String propertyAddress = scanner.nextLine();
        Property property = findProperty(propertyAddress);
        if (property == null) {
            System.out.println("Property not found, return to main menu");
            return;
        }

        for (Inspection inspection : property.getInspections()) {
            System.out.println();
            System.out.println(inspection.toString());

        }
    }


    private void viewInspectionByDate() {
        System.out.println("Enter property address: ");
        String propertyAddress = scanner.nextLine();
        Property property = findProperty(propertyAddress);
        if (property == null) {
            System.out.println("Property not found, returning to main menu");
            return;
        }

        System.out.println("Enter inspection date in format MMDDYYYY: ");
        String date = scanner.nextLine();
        if (date.length() != 8) {
            System.out.println("Invalid date format, returning to main menu");
            return;
        }

        Inspection inspection = property.getInspectionByDate(date);

        if (inspection == null) {
            System.out.println("Inspection for that date not found, returning to main menu");
            return;
        }

        System.out.println(inspection.toString());
    }

    private Property findProperty(String address) {
        for (Property p : listProperty) {
            if (p.getAddress().equals(address)) {
                return p;
            }
        }
        return null;
    }

    public void addPropertyToList(Property p) {
        listProperty.add(p);
    }


}
