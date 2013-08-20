package pl.com.bits.propertyfile.sorter.domain;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

import pl.com.bits.propertyfile.sorter.domain.Comment;
import pl.com.bits.propertyfile.sorter.domain.Group;
import pl.com.bits.propertyfile.sorter.domain.PropertyFile;
import pl.com.bits.propertyfile.sorter.domain.PropertyFileParser;
import pl.com.bits.propertyfile.sorter.domain.NewLine;

public class PropertyFileParserTest {

  private PropertyFileParser parser;

  @Test
  public void shouldParseNewLine() throws IOException {
    prepareParser("");

    PropertyFile file = parser.parse();

    assertEquals(1, file.getElements().size());
    assertEquals(NewLine.class, file.getElements().get(0).getClass());
  }
  
  @Test
  public void shouldParseDoubleNewLine() throws IOException {
    prepareParser("", "");

    PropertyFile file = parser.parse();

    assertEquals(2, file.getElements().size());
    assertEquals(NewLine.class, file.getElements().get(0).getClass());
    assertEquals(NewLine.class, file.getElements().get(1).getClass());
  }
  
  @Test
  public void shouldParseComment() throws IOException {
    prepareParser("# labels");

    PropertyFile file = parser.parse();

    assertEquals(1, file.getElements().size());
    assertEquals(Comment.class, file.getElements().get(0).getClass());    
    assertEquals("labels", comment(file, 0).getContent());
  }
  
  @Test
  public void shouldParseComment1() throws IOException {
    prepareParser("## labels");

    PropertyFile file = parser.parse();

    assertEquals(1, file.getElements().size());
    assertEquals(Comment.class, file.getElements().get(0).getClass());    
    assertEquals("labels", comment(file, 0).getContent());
  }
  
  @Test
  public void shouldParseComment2() throws IOException {
    prepareParser("## labels");

    PropertyFile file = parser.parse();

    assertEquals(1, file.getElements().size());
    assertEquals(Comment.class, file.getElements().get(0).getClass());    
    assertEquals("labels", comment(file, 0).getContent());
  }
  
  @Test
  public void shouldParseTwoComment() throws IOException {
    prepareParser("# first ", "# second ");

    PropertyFile file = parser.parse();

    assertEquals(2, file.getElements().size());
    assertEquals(Comment.class, file.getElements().get(0).getClass());
    assertEquals(Comment.class, file.getElements().get(1).getClass()); 
    assertEquals("first", comment(file, 0).getContent());
    assertEquals("second", comment(file, 1).getContent());
  }
  
  @Test
  public void shouldParseGroupWithSingleElement() throws IOException {
    prepareParser("key=value");

    PropertyFile file = parser.parse();

    assertEquals(1, file.getElements().size());
    assertEquals(Group.class, file.getElements().get(0).getClass());    
    assertEquals("value", group(file, 0).value("key"));
  }
  
  @Test
  public void shouldParseGroup() throws IOException {
    prepareParser("key2=value2", "key1=value1", "key0=value0");

    PropertyFile file = parser.parse();

    assertEquals(1, file.getElements().size());
    assertEquals(Group.class, file.getElements().get(0).getClass());    
    assertEquals("value0", group(file, 0).value("key0"));
    assertEquals("value1", group(file, 0).value("key1"));
    assertEquals("value2", group(file, 0).value("key2"));
  }
  
  @Test
  public void shouldParseHtml1() throws IOException {
    prepareParser("key=<b>value</b>");

    PropertyFile file = parser.parse();

    assertEquals(1, file.getElements().size());
    assertEquals(Group.class, file.getElements().get(0).getClass());    
    assertEquals("<b>value</b>", group(file, 0).value("key"));
  }
  
  @Test
  public void shouldParseHtml2() throws IOException {
    prepareParser("key=<b style='new'>value</b>");

    PropertyFile file = parser.parse();

    assertEquals(1, file.getElements().size());
    assertEquals(Group.class, file.getElements().get(0).getClass());    
    assertEquals("<b style='new'>value</b>", group(file, 0).value("key"));
  }
  
  @Test
  public void shouldParseHtml3() throws IOException {
    prepareParser("key=<b style=\"new\">value</b>");

    PropertyFile file = parser.parse();

    assertEquals(1, file.getElements().size());
    assertEquals(Group.class, file.getElements().get(0).getClass());    
    assertEquals("<b style=\"new\">value</b>", group(file, 0).value("key"));
  }
  
  @Test
  public void shouldParseFile() throws IOException {
    prepareParser("# login form", "label_login=Login", "label_pass=Password", "", "", "# validation", "Min=The value is too low", "");

    PropertyFile file = parser.parse();

    assertEquals(7, file.getElements().size());
    assertEquals("login form", comment(file, 0).getContent());
    assertEquals("Login", group(file, 1).value("label_login"));
    assertEquals("Password", group(file, 1).value("label_pass"));
    assertEquals(NewLine.class, file.getElements().get(2).getClass());
    assertEquals(NewLine.class, file.getElements().get(3).getClass());
    assertEquals("validation", comment(file, 4).getContent());
    assertEquals("The value is too low", group(file, 5).value("Min"));
    assertEquals(NewLine.class, file.getElements().get(6).getClass());
  }

  private void prepareParser(String ... lines) {
    final StringBuilder content = new StringBuilder();
    
    for (String line : lines) {
      content.append(line).append("\n");
    }
    
    parser = new PropertyFileParser(new ByteArrayInputStream(content.toString().getBytes()));
  }
  
  private Comment comment(PropertyFile file, int index) {
    return (Comment) file.getElements().get(index);
  }
  
  private Group group(PropertyFile file, int index) {
    return (Group) file.getElements().get(index);
  }

}
