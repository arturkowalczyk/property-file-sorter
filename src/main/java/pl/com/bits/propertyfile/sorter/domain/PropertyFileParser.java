package pl.com.bits.propertyfile.sorter.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Pattern;

public class PropertyFileParser {

  private final static Pattern COMMENT_PATTERN = Pattern.compile("^#+");
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
          line = COMMENT_PATTERN.matcher(line).replaceFirst("");

          file.getElements().add(new Comment(line.trim()));
          group = null;
        } else {
          if (group == null) {
            group = new Group();
            file.getElements().add(group);
          }

          final String[] parts = line.split("=");
          final String key = parts[0];
          final StringBuilder value = new StringBuilder();

          if (parts.length >= 2) {
            for (int i = 1; i < parts.length; i++) {
              value.append(parts[i]);

              if (i != parts.length - 1) {
                value.append("=");
              }
            }
          }

          group.put(key, value.toString());
        }
      }
    }

    return file;
  }

}
