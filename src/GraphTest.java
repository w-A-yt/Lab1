import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest {

	@Test
	public void testQueryBridgeWords() {
		String test;
		String []words= {"to", "explore", "strange", "new", "worlds", "to", "seek", "out", "new", "life", "and", "new", "civilizations"};
		Graph g=new Graph(13, 12, words);
		test=g.queryBridgeWords("new", "and");
		assertEquals("The bridge words from \"new\" to \"and\" is :life",test);
	}

}
