package edu.nyu.ll3435;

import java.util.regex.Pattern;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class AddressBookEntry implements Comparable<AddressBookEntry> {
  private final String name;
  private final String address;
  private final String phone;
  private final String email;
  private final String note;

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getNote() {
    return note;
  }

  public static class Builder {
    private String name = "";
    private String address = "";
    private String phone = "";
    private String email = "";
    private String note = "";

    public Builder() {}

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder address(String address) {
      this.address = address;
      return this;
    }

    public Builder phone(String phone) {
      this.phone = phone;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder note(String note) {
      this.note = note;
      return this;
    }

    public AddressBookEntry build() {
      return new AddressBookEntry(this);
    }
    
    public void clear() {
      this.name = "";
      this.address = "";
      this.phone = "";
      this.email = "";
      this.note = "";
    }

    public static Builder instance() {
      return new Builder();
    }
  }

  private AddressBookEntry(Builder builder) {
    name = builder.name;
    address = builder.address;
    phone = builder.phone;
    email = builder.email;
    note = builder.note;
  }

  public boolean isMatch(final Pattern pattern) {
    return pattern.matcher(name).find() || pattern.matcher(address).find()
        || pattern.matcher(phone).find() || pattern.matcher(email).find()
        || pattern.matcher(note).find();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressBookEntry that = (AddressBookEntry) o;
    return Objects.equal(name, that.name) && Objects.equal(address, that.address)
        && Objects.equal(phone, that.phone) && Objects.equal(email, that.email)
        && Objects.equal(note, that.note);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, address, phone, email, note);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("name", name).add("address", address)
        .add("phone", phone).add("email", email).add("note", note).toString();
  }

  @Override
  public int compareTo(AddressBookEntry o) {
    return this.name.compareToIgnoreCase(o.name);
  }
}
