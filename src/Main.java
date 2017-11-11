

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {

	public static class WordNode {
		private String word;
		private List<WordNode> OutWN = new ArrayList<>();
		private List<Integer> weight = new ArrayList<>();
	}

	public static WordNode addWeight(WordNode head, String nextWord) {
		if (head.OutWN.size() < 2) {
			head.weight.add(1);
		} else {
			for (int i = 0; i < head.OutWN.size() - 1; i++) {
				if (head.OutWN.get(i).word.equals(nextWord)) {
					head.weight.set(i, head.weight.get(i) + 1);
				}
			}
			head.weight.add(1);
		}
		return head;
	}

	public static WordNode VisitOutNode(WordNode WN) {
		int i = WN.OutWN.size() - 1;
		return WN.OutWN.get(i);
	}

	public static WordNode AddWordNode(WordNode head, String Nodeword, List<WordNode> WordNodes) {
		int site;
		for (site = 0; site < WordNodes.size(); site++) {
			if (head.word == WordNodes.get(site).word) {
				break;
			}
		}
		WordNode Node = new WordNode();
		WordNode HeadOWN = VisitOutNode(head);
		Node.word = Nodeword;
		Node.OutWN.add(HeadOWN);
		int i = head.OutWN.size() - 1;
		head.OutWN.remove(i);
		head.OutWN.add(Node);
		head.weight.add(1);
		WordNodes.set(site, head);
		return Node;
	}

	public static WordNode AddNoded(WordNode head, List<WordNode> WordNodes, int j) {
		WordNode Noded = WordNodes.get(j);
		WordNode HeadOWN = VisitOutNode(head);
		Noded.OutWN.add(HeadOWN);
		int i = head.OutWN.size() - 1;
		head.OutWN.remove(i);
		head.OutWN.add(Noded);
		head = addWeight(head, WordNodes.get(j).word);
		WordNodes.set(j, Noded);
		return Noded;
	}

	public static void WriteToFile(List<String> words, File file) {

		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			for (String word : words) {
				writer.write(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

//	public static void ShowInDot(List<WordNode> WordNodes) {
//		List<String> SDot = new ArrayList<>();
//		SDot.add("digraph G {");
//		for (WordNode W : WordNodes) {
//			if (W != null) {
//				List<WordNode> WOut = W.OutWN;
//				for (int i = 0; i < WOut.size(); i++) {
//					if (WOut.get(i) != null) {
//						SDot.add(W.word + "->" + WOut.get(i).word + "[label = \"" + W.weight.get(i) + "\"];");
//					}
//				}
//			}
//		}
//		SDot.add("}");
//		WriteToFile(SDot, new File("D:/work/java/RFF/result.dot"));
//	}

	public static void OpenGra(String workSpace, String wordPath) {
		try {
			String[] cmd = { workSpace, wordPath };
			Process proc = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	


	public static void main(String[] args) {
		ReadFromFile read = new ReadFromFile();
		System.out.println("请输入文件路径：");
		Scanner sc = new Scanner(System.in);
		String f = sc.nextLine();
		File file = new File(f);// D:/work/java/RFF/test.txt
		while (file.exists() == false) {
			System.out.println("请输入文件路径：");
			sc = new Scanner(System.in);
			f = sc.nextLine();
			file = new File(f);
		}
		List<String> words = read.getLowerCaseWords(file);

		List<WordNode> WordNodes = new ArrayList<>();

		WordNode FirstNode = new WordNode();
		FirstNode.word = words.get(0);

		// 调用节点,初始化一个next
		FirstNode.OutWN.add(null);
		WordNodes.add(FirstNode);
		for (int i = 1; i < words.size(); i++) {
			int flag = 1;
			for (int j = 0; j < WordNodes.size(); j++) {
				if (words.get(i).equals(WordNodes.get(j).word)) {
					flag = 0;
					FirstNode = AddNoded(FirstNode, WordNodes, j);
				}
			}
			if (flag == 1) {
				FirstNode = AddWordNode(FirstNode, words.get(i), WordNodes);
				WordNodes.add(FirstNode);
			}
		}
//		ShowInDot(WordNodes);
//		String OpenGra = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\gvedit.exe";
//		String path = "D:\\work\\java\\RFF\\result.dot";
//		OpenGra(OpenGra, path);

		read.WriteToFile(words, new File("F:\\output.txt"));

		int vex = words.size();
		int edge = words.size() - 1;
		String[] s = new String[words.size()];
		words.toArray(s);
		Graph g = new Graph(vex, edge, s);
		g.showDirectedGraph();
		String func;
		System.out.println('\n' + "查询桥接词按1，生成新文本按2，最短路径按3，随机游走按4，退出按5：");
		func = sc.nextLine();
			if (func.equals("1") == true) {
				String QS1, QS2;
				System.out.println("输入词桥查询的单词1：");
				QS1 = sc.nextLine();
				QS1.toLowerCase();
				System.out.println("输入词桥查询的单词2：");
				QS2 = sc.nextLine();
				QS2.toLowerCase();
				String result;
				result = g.queryBridgeWords(QS1, QS2);
//				if (g.exsit == 1) {
//					System.out.println("The bridge words from " + "\"" + QS1 + "\"" + " to " + "\"" + QS2 + "\""
//							+ " are:" + result);
//				} else {
//					System.out.println(result);
//				}
				System.out.println(result);
			} else if (func.equals("2") == true) {
				System.out.println("请输入文本：");
				String NT;
				NT = sc.nextLine();// Seek to explore new and exciting synergies
				g.generateNewText(NT);
			} else if (func.equals("3") == true) {

				String SP1, SP2;
				System.out.println("当输入的word2为**是则遍历其与图中所有顶点的最短路径");
				System.out.println("输入最短路径的word1：");
				SP1 = sc.nextLine();
				SP1.toLowerCase();
				System.out.println("输入最短路径的word2：");
				SP2 = sc.nextLine();
				SP2.toLowerCase();
				g.calcShortestPath(SP1, SP2);
			} else if (func.equals("4") == true) {
				g.randomWalk();
			} else {
				System.out.println("输入错误");
			}
			System.out.println("查询桥接词按1，生成新文本按2，最短路径按3，随机游走按4，退出按5：");
			func = sc.nextLine();
		
		sc.close();
	}
}
