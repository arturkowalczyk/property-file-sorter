package pl.com.bits.propertyfile.sorter.domain;

public class Comment implements PropertyFileElement {

  private final String content;

  public Comment(String content) {
    this.content = content;
  }

  public byte[] bytes() {
    return ("# " + content + "\n").getBytes();
  }

  public String getContent() {
    return content;
  }

}
