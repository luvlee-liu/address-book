package edu.nyu.ll3435;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class AddressBookTests {

  @Test
  void test() {
    AddressBook instance = new AddressBook();
    AddressBookEntry.Builder builder = new AddressBookEntry.Builder();
    AddressBookEntry entry = builder.name("li liu").email("ll3435@nyu.edu").build();
    assertTrue(instance.add(entry), "Add new entry");
    assertFalse(instance.add(entry), "Add duplicate entry");

    instance.add(AddressBookEntry.Builder.instance().name("abc").email("abc@nyu.edu").build());
    try {
      instance.saveToFile("./book.json");
      instance.loadFromFile("./book.json");
      List<AddressBookEntry> entries = instance.search("ll");
      assertEquals(1, entries.size());
      assertEquals("li liu", entries.get(0).getName());
    } catch (IOException e) {
      fail("Should not throw");
    }
  }

}
