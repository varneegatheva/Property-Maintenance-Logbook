package persistence;

import model.Property;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import ui.Logbook;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Logbook lb = new Logbook();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Logbook logbook = new Logbook();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLogbook.json.json");
            writer.open();
            writer.write(logbook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLogbook.json.json");
            List<Property> lb = reader.read();
            assertEquals(0, lb.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLogbook() {
        try {
            Logbook logbook = new Logbook();
            logbook.addPropertyToList(new Property("1234 blvd", "300", ));
            logbook.addPropertyToList(new Property("567 ave", "10000", ));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(logbook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            List<Property> lb = reader.read();
            assertEquals(2, lb.size());
            checkInspection("10101997", "y", "y", "y", lb.get(0).getInspections().get(0));
            checkInspection("10111997", "y", "n", "y", lb.get(0).getInspections().get(1));
            checkInspection("12121997", "n", "n", "n", lb.get(1).getInspections().get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }



}
