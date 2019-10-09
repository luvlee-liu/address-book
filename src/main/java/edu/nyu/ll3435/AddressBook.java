package edu.nyu.ll3435;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class AddressBook {

  private TreeSet<AddressBookEntry> book = new TreeSet<>();

  public AddressBook() {}

  public boolean add(AddressBookEntry entry) {
    return book.add(entry);
  }

  public boolean remove(AddressBookEntry entry) {
    return book.remove(entry);
  }
  
  public int size() {
    return book.size();
  }

  public List<AddressBookEntry> search(String query) {
    List<AddressBookEntry> ret = new ArrayList<>();
    final Pattern pattern = Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE);
    for (AddressBookEntry entry : book) {
      if (entry.isMatch(pattern)) {
        ret.add(entry);
      }
    }
    return ret;
  }

  public void saveToFile(String path) throws IOException {
    Gson gson = new Gson();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
      gson.toJson(book, writer);
    }
  }

  public void loadFromFile(String path) throws IOException {
    Gson gson = new Gson();
    StringBuilder buffer = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = br.readLine()) != null) {
        buffer.append(line);
      }
      Type collectionType = new TypeToken<Collection<AddressBookEntry>>() {}.getType();

      List<AddressBookEntry> entries = gson.fromJson(buffer.toString(), collectionType);
      book.clear();
      book.addAll(entries);
    }
  }
}
