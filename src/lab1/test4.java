package lab1;

import org.junit.Test;

public class test4 {
	@Test
	public void runTest4() {
		String []words= {"to", "explore", "strange", "new", "worlds", "to", "seek", "out", "new", "life", "and", "new", "civilizations"};
		Graph g=new Graph(13, 12, words);
		String inputText = "";
		String newT = g.generateNewText(inputText);
		System.out.println(newT);
	}
}
