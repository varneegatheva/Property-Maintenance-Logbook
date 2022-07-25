package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PropertyTest {

  private Property property;

  @BeforeEach
  public void setup() {
    property = new Property();
  }

  @Test
  public void testSetAddress() {
    property.setAddress("Test Address");
    assertEquals("Test Address", property.getAddress());
  }

  @Test
  public void testSetSize() {
    property.setSize(7);
    assertEquals(7, property.getSize());
  }

  @Test
  public void testConstructor() {
    assertEquals(0, property.getInspections().size());
  }

  @Test
  public void testAddInspection() {
    property.addInspection(new Inspection("12121997", "plumbing", "drywall", "notes"));
    assertEquals(1, property.getInspections().size());
    assertEquals("12121997", property.getInspections().get(0).getDate());
  }

  @Test
  public void testAddInspectionWithSameDate() {
    property.addInspection(new Inspection("12121997", "plumbing", "drywall", "notes"));
    property.addInspection(new Inspection("12121997", "plumbing", "drywall", "notes"));
    assertEquals(1, property.getInspections().size());
  }

  @Test
  public void testGetInspectionByDate() {
    property.addInspection(new Inspection("12121997", "plumbing", "drywall", "notes"));
    assertEquals("plumbing", property.getInspectionByDate("12121997").getPlumbing());
  }

  @Test
  public void testGetAllInspections() {
    property.addInspection(new Inspection("12121997", "plumbing1", "drywall1", "notes1"));
    property.addInspection(new Inspection("12121998", "plumbing2", "drywall2", "notes2"));
    assertEquals(2, property.getInspections().size());
  }

  @Test
  public void testToString() {
    property.setAddress("Test Address");
    property.setSize(7);
    String expected = " Property: Test Address\n"
        + " Size: 7";
    assertEquals(expected, property.toString());
  }

}