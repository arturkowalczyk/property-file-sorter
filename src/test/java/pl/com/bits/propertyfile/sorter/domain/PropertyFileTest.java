package pl.com.bits.propertyfile.sorter.domain;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import pl.com.bits.propertyfile.sorter.domain.Comment;
import pl.com.bits.propertyfile.sorter.domain.Group;
import pl.com.bits.propertyfile.sorter.domain.PropertyFile;
import pl.com.bits.propertyfile.sorter.domain.NewLine;

public class PropertyFileTest {
  
  private PropertyFile file;
  private ByteArrayOutputStream baos;
  
  @Before
  public void init() {
    file = new PropertyFile();
    baos = new  ByteArrayOutputStream();
  }

  @Test
  public void shouldSerializeComment() throws IOException {
    file.getElements().add(new Comment("test comment"));
    
    file.serialize(baos);
    baos.flush();
    
    assertEquals("# test comment\n", content());
  }
  
  @Test
  public void shouldSerializeSingleValue() throws IOException {
    file.getElements().add(new Group("test", "test value"));
    
    file.serialize(baos);
    baos.flush();
    
    assertEquals("test=test value\n", content());
  }
  
  @Test
  public void shouldSerializeNewLine() throws IOException {
    file.getElements().add(new NewLine());
    
    file.serialize(baos);
    baos.flush();
    
    assertEquals("\n", content());
  }
  
  @Test
  public void shouldSerializeSmallFile() throws IOException {
    Group group = new Group();
    group.put("label_password", "Password");
    group.put("label_login", "Login");
    
    file.getElements().add(new Comment("form"));
    file.getElements().add(group);
    file.getElements().add(new NewLine());
    
    file.serialize(baos);
    baos.flush();
    
    assertEquals("# form\nlabel_login=Login\nlabel_password=Password\n\n", content());
  }

  private String content() {
    return new String(baos.toByteArray());
  }

}
