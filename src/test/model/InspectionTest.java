package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionTest {

    private Inspection inspection;

    @BeforeEach
    public void setup() {
        inspection = new Inspection("12121997", "plumbing", "drywall", "notes");
    }

    @Test
    public void testConstructor() {
        assertEquals("12121997", inspection.getDate());
        assertEquals("plumbing", inspection.getPlumbing());
        assertEquals("drywall", inspection.getDrywall());
        assertEquals("notes", inspection.getAddedNotes());
    }

    @Test
    public void testToString() {
        String expected = " Date: 12121997\n"
                + " Plumbing: plumbing\n"
                + " Drywall: drywall\n"
                + " Additional Notes: notes";
        assertEquals(expected, inspection.toString());
    }
}

