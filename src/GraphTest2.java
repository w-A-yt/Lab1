import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest2 {

	@Test
	public void testQueryBridgeWords() {
		String test;
		String []words= {"to", "explore", "strange", "new", "worlds", "to", "seek", "out", "new", "life", "and", "new", "civilizations"};
		Graph g=new Graph(13, 12, words);
		test=g.queryBridgeWords("new", "");
		assertEquals("请输入两个单词",test);
	}

}
