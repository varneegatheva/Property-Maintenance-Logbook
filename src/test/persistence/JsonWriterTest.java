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
            Logbook lb = new Logbook();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLogbook.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLogbook.json");
             lb = reader.read();
            assertEquals(0, lb.numProperties());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLogbook() {
        try {
            Logbook lb = new Logbook();
            lb.addPropertyToList(new Property("1234 blvd", "300", ));
            lb.addPropertyToList(new Property("567 ave", "10000", ));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            lb = reader.read();
            List<Property> listProperty = lb.getListProperty();
            assertEquals(2, listProperty.size());
            checkProperty("1234 blvd", 300, listProperty.get(0));
            checkProperty("567 ave", 10000, listProperty.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }



}
