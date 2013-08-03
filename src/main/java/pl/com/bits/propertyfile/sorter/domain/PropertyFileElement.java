package pl.com.bits.propertyfile.sorter.domain;

import java.io.IOException;

public interface PropertyFileElement {

  byte[] bytes() throws IOException;
  
}
