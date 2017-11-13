package lab1;

import org.junit.Test;

public class test2 {
	@Test
	public void runTest2() {
		String []words= {"to", "explore", "strange", "new", "worlds", "to", "seek", "out", "new", "life", "and", "new", "civilizations"};
		Graph g=new Graph(13, 12, words);
		String inputText = "to have";
		String newT = g.generateNewText(inputText);
		System.out.println(newT);
	}
}
