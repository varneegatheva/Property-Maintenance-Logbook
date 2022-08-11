package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents a logbook object
public class Logbook {

    private List<Property> listProperty;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/logbook.json";


    public Logbook(List<Property> properties) {
        this.listProperty = properties;
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: loads property from file
    public void loadPropertyFromFile() {
        {
            try {
                listProperty = jsonReader.read();
                System.out.println("Loaded from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    // EFFECTS: saves property and its respective inspections to file
    public void savePropertyToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(this);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: given property address, return property if found in property list; otherwise return null
    public Property findProperty(String address) {
        for (Property p : listProperty) {
            if (p.getAddress().equals(address)) {
                return p;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: add property to property list
    public void addPropertyToList(Property p) {
        listProperty.add(p);
        EventLog.getInstance().logEvent(new Event("Property added to logbook with address: " + p.getAddress()));
    }

    // EFFECTS:
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("properties", propertiesToJson());
        return json;
    }

    // EFFECTS: returns things in projects as a JSON array
    public JSONArray propertiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Property p : listProperty) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

    public List<Property> getListProperty() {
        return listProperty;
    }

}