package pl.com.bits.propertyfile.sorter.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class PropertyFileParser {

  private final InputStream input;

  public PropertyFileParser(InputStream input) {
    this.input = input;
  }

  public PropertyFile parse() throws IOException {
    final PropertyFile file = new PropertyFile();
    final Reader reader = new InputStreamReader(input);
    final BufferedReader bReader = new BufferedReader(reader);

    String line;
    Group group = null;

    while ((line = bReader.readLine()) != null) {
      if (line.trim().length() == 0) {
        file.getElements().add(new NewLine());
        group = null;
      } else {
        if (line.startsWith("#")) {
          file.getElements().add(new Comment(line.substring(1).trim()));
          group = null;
        } else {
          if (group == null) {
            group = new Group();
            file.getElements().add(group);
          }

          String[] parts = line.split("=");
          group.put(parts[0], parts[1]);
        }
      }
    }

    return file;
  }

}
