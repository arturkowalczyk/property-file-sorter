package pl.com.bits.propertyfile.sorter.domain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import pl.com.bits.propertyfile.sorter.domain.Group;

public class GroupTest {
  
  private Group group;

  @Test
  public void test() throws IOException {
    group = new Group();
    group.put("k_c", "value c");
    group.put("k_b", "value b");
    group.put("k_a", "value a");
    
    
    assertEquals("k_a=value a\nk_b=value b\nk_c=value c\n", new String(group.bytes()));
  }

}
