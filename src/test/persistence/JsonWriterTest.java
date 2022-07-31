package persistence;

import java.util.ArrayList;

import model.Inspection;
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
            Logbook lb = new Logbook(new ArrayList<>());
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLogbook.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLogbook.json");
            List<Property> properties = reader.read();
            assertEquals(0, properties.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLogbook() {
        try {
            List<Property> listProperty = new ArrayList<>();
            Property property1 = new Property();
            property1.setAddress("1234 blvd");
            property1.setSize(300);
            property1.addInspection(new Inspection("10101997", "y", "y", "y"));
            Property property2 = new Property();
            property2.setAddress("567 ave");
            property2.setSize(10000);
            property2.addInspection(new Inspection("10101997", "n", "n", "n"));

            listProperty.add(property1);
            listProperty.add(property2);
            Logbook lb = new Logbook(listProperty);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLogbook.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLogbook.json");
            List<Property> properties = reader.read();
            assertEquals(2, properties.size());
            checkProperty("1234 blvd", 300, properties.get(0));
            checkProperty("567 ave", 10000, properties.get(1));
            checkInspection("10101997", "y", "y", "y", property1.getInspections().get(0));
            checkInspection("10101997", "n", "n", "n", property2.getInspections().get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
