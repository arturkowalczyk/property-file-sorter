package pl.com.bits.propertyfile.sorter.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.com.bits.propertyfile.sorter.domain.NewLine;

public class NewLineTest {

  private NewLine newLine = new NewLine();

  @Test
  public void shouldSerializeNewLine() {
    assertEquals("\n", new String(newLine.bytes()));
  }

}
