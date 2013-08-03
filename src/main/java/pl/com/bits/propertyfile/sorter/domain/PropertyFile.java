package pl.com.bits.propertyfile.sorter.domain;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PropertyFile {

  private final List<PropertyFileElement> elements = new ArrayList<PropertyFileElement>();

  public List<PropertyFileElement> getElements() {
    return elements;
  }

  public void serialize(OutputStream outStream) throws IOException {
    for (PropertyFileElement element : elements) {
      outStream.write(element.bytes());
    }
  }

}
