package pl.com.bits.propertyfile.sorter.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class PropertyFileParserIntegrationTest {

  @Test
  public void test() throws IOException {
    InputStream stream = new FileInputStream("src/test/resources/test.properties");
    
    PropertyFileParser parser = new PropertyFileParser(stream);
    PropertyFile file = parser.parse();

    file.serialize(System.out);
  }

}
