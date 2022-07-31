package persistence;

import model.Inspection;
import model.Property;
import org.junit.jupiter.api.Test;
import ui.Logbook;

import java.beans.PropertyEditor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            List<Property> lb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLogbook.json");
        try {
            List<Property> lb = reader.read();
            assertEquals(0, lb.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            List<Property> lb = reader.read();
            List<Property> listProperty = lb.getListProperty();
            assertEquals(2, lb.size());
            checkProperty("1234 blvd", 300, listProperty.get(0));
            checkProperty("567 ave", 10000, listProperty.get(1));

            ArrayList<Property> inspections = reader.read();
            List<Inspection> inspectionList = inspections.getInspections();
            checkInspection("10101997", "y", "y", "y", inspectionList.get(0));
            checkInspection("10111997", "y", "n", "y", inspectionList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
