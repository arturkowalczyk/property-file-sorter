package pl.com.bits.propertyfile.sorter.domain;

public class NewLine implements PropertyFileElement {

  public byte[] bytes() {
    return "\n".getBytes();
  }

}
