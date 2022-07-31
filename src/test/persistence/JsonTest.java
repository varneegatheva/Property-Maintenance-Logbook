package persistence;

import model.Inspection;
import model.Property;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    // code based on JsonTest from JsonSerializationDemo
    protected void checkProperty(String address, int size, Property property) {
        assertEquals(address, property.getAddress());
        assertEquals(size, property.getSize());
    }
    // code based on JsonReader from JsonSerializationDemo
    protected void checkInspection(String date, String plumbing, String drywall, String addedNotes, Inspection inspection) {
        assertEquals(date, inspection.getDate());
        assertEquals(plumbing, inspection.getPlumbing());
        assertEquals(drywall, inspection.getDrywall());
        assertEquals(addedNotes, inspection.getDrywall());
    }
}
