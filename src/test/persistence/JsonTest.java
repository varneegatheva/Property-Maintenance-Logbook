package persistence;

import model.Inspection;
import model.Property;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class JsonTest {
    protected void checkProperty(String address, int size, Property property) {
        assertEquals(address, Property.getAddress());
        assertEquals(size, Property.getSize());
    }

    protected void checkInspection (String date, String plumbing, String drywall, String addedNotes, Inspection inspection) {
        assertEquals(date, inspection.getDate());
        assertEquals(plumbing, inspection.getPlumbing());
        assertEquals(drywall, inspection.getDrywall());
        assertEquals(addedNotes, inspection.getDrywall());
    }
}
