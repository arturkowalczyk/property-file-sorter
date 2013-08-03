package pl.com.bits.propertyfile.sorter.domain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Group implements PropertyFileElement {

  private final Map<String, String> values;

  public Group(String key, String value) {
    this.values = new TreeMap<String, String>();
    this.values.put(key, value);
  }

  public Group() {
    this.values = new TreeMap<String, String>();
  }

  public byte[] bytes() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    for (String key : values.keySet()) {
      baos.write(key.getBytes());
      baos.write("=".getBytes());
      baos.write(values.get(key).getBytes());
      baos.write("\n".getBytes());
    }

    return baos.toByteArray();
  }

  public String value(String key) {
    return this.values.get(key);
  }

  public void put(String key, String value) {
    this.values.put(key, value);
  }

}
