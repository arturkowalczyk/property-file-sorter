package pl.com.bits.propertyfile.sorter.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pl.com.bits.propertyfile.sorter.domain.Comment;

public class CommentTest {

  private Comment comment;
  
  @Test
  public void shouldSerializeComment() {
    comment = new Comment("test comment");
    
    assertEquals("# test comment\n", new String(comment.bytes()));
  }

}
