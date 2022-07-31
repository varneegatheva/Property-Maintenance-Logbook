package persistence;

import model.Property;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
    void testReaderEmpty() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLogbook.json");
        try {
            List<Property> lb = reader.read();
            assertEquals(0, lb.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneral() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLogbook.json");
        try {
            List<Property> lb = reader.read();
            assertEquals(2, lb.size());
            checkProperty("1234 blvd", 300, lb.get(0));
            checkProperty("567 ave", 10000, lb.get(1));

            checkInspection("10111997", "n", "y", "y", lb.get(0).getInspections().get(1));
            checkInspection("10101997", "y", "y", "y", lb.get(0).getInspections().get(0));
            checkInspection("12121997", "n", "n", "n", lb.get(1).getInspections().get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
