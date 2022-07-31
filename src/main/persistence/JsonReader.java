package persistence;

import model.Inspection;
import model.Property;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.Logbook;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads logbook from JSON data stored in file
// code based on JsonReader from JsonSerializationDemO
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads logbook from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Property> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLogbook(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses logbook from JSON object and returns it
    private ArrayList<Property> parseLogbook(JSONObject jsonObject) {
        ArrayList<Property> lb = new ArrayList<Property>();
        addProperties(lb, jsonObject);
        return lb;
    }

    // MODIFIES: lb
    // EFFECTS: parses properties from JSON object and adds them to logbook
    private void addProperties(ArrayList<Property> lb, JSONObject jsonObject) {
        System.out.println(jsonObject);
        JSONArray jsonArray = jsonObject.getJSONArray("properties");
        for (Object json : jsonArray) {
            JSONObject nextProperty = (JSONObject) json;
            addProperty(lb, nextProperty);
        }

    }

    // MODIFIES: lb
    // EFFECTS: parses property from JSON object and adds it to logbook
    private void addProperty(ArrayList<Property> lb, JSONObject jsonObject) {
        String address = jsonObject.getString("address");
        int size = jsonObject.getInt("size");
        Property property = new Property();
        property.setSize(size);
        property.setAddress(address);
        addInspections(property, jsonObject.getJSONArray("inspections"));
        lb.add(property);
    }

    // MODIFIES: p
    // EFFECTS: parses inspections from JSON object and adds them to property
    private void addInspections(Property p, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject nextInspection = (JSONObject) json;
            addInspection(p, nextInspection);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses inspection from JSON object and adds them to property
    private void addInspection(Property p, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        String plumbing = jsonObject.getString("plumbing");
        String drywall = jsonObject.getString("drywall");
        String addedNotes = jsonObject.getString("addedNotes");
        Inspection inspection = new Inspection(date, plumbing, drywall, addedNotes);
        p.addInspection(inspection);
    }
}
