package lab1;

import org.junit.Test;

public class test {

	
	@Test
	public void runTest4() {
		String []words= {"to", "explore", "strange", "new", "worlds", "to", "seek", "out", "new", "life", "and", "new", "civilizations"};
		Graph g=new Graph(13, 12, words);
		String inputText = "";
		String newT = g.generateNewText(inputText);
		System.out.println(newT);
	}
	
	
	@Test
	public void runTest3() {
		String []words= {"to", "explore", "strange", "new", "worlds", "to", "seek", "out", "new", "life", "and", "new", "civilizations"};
		Graph g=new Graph(13, 12, words);
		String inputText = "to";
		String newT = g.generateNewText(inputText);
		System.out.println(newT);
	}
	
	@Test
	public void runTest2() {
		String []words= {"to", "explore", "strange", "new", "worlds", "to", "seek", "out", "new", "life", "and", "new", "civilizations"};
		Graph g=new Graph(13, 12, words);
		String inputText = "to have";
		String newT = g.generateNewText(inputText);
		System.out.println(newT);
	}
	
	@Test
	public void runTest1() {
		String []words= {"to", "explore", "strange", "new", "worlds", "to", "seek", "out", "new", "life", "and", "new", "civilizations"};
		Graph g=new Graph(13, 12, words);
		String inputText = "to strange";
		String newT = g.generateNewText(inputText);
		System.out.println(newT);
	}
}
