package lab1;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ReadFromFile read=new ReadFromFile();
		System.out.println("请输入文件路径：");
		Scanner sc = new Scanner(System.in);
		String f = sc.nextLine();
		File file = new File(f);//D:/work/java/RFF/test.txt
		while(file.exists()==false) {
			System.out.println("请输入文件路径：");
			sc = new Scanner(System.in);
			f = sc.nextLine();
			file = new File(f);
		}
		List<String> words = read.getLowerCaseWords(file); 
		
		read.WriteToFile(words, new File("C:/lab/output.txt"));
		
		int vex=words.size();
		int edge=words.size()-1;
		String [] s=new String[words.size()];
		words.toArray(s);
		Graph g=new Graph(vex,edge,s);
		g.showDirectedGraph();
		String func;
		System.out.println("lalalala");
		System.out.println('\n'+"查询桥接词按1，生成新文本按2，最短路径按3，随机游走按4，退出按5：");
		func = sc.nextLine();
		
			if(func.equals("1")==true) {
				String QS1,QS2;
		        System.out.println("输入词桥查询的单词1：");
		        QS1 = sc.nextLine();
		        QS1.toLowerCase();
		        System.out.println("输入词桥查询的单词2：");
		        QS2 = sc.nextLine();
		        QS2.toLowerCase();
		        String result;
		        result = g.queryBridgeWords(QS1, QS2);
		        if(g.exsit==1)
		        {
		        	System.out.println("The bridge words from "+"\""+QS1+"\""+" to "+ "\""+QS2+"\""+" are:"+result);
		        }
		        else
		        {
		        	System.out.println(result);
		        }
		        
			}else if(func.equals("2")==true) {
				System.out.println("请输入文本文件：");
				String NT;
				NT = sc.nextLine();//Seek to explore new and exciting synergies
				g.generateNewText(NT);			
			}else if(func.equals("3")==true) {
				
				String SP1,SP2;
				System.out.println("当输入的word2为**是则遍历其与图中所有顶点的最短路径");
				System.out.println("输入最短路径的word1：");
			    SP1 = sc.nextLine();
			    SP1.toLowerCase();
			    System.out.println("输入最短路径的word2：");
			    SP2 = sc.nextLine();
			    SP2.toLowerCase();
			    g.calcShortestPath(SP1, SP2);
			}else if(func.equals("4")==true) {
				g.randomWalk();			
			}else {
				System.out.println("输入错误");
			}
			System.out.println("查询桥接词按1，生成新文本按2，最短路径按3，随机游走按4，退出按5：");
			func = sc.nextLine();
		
		sc.close();
		}
	
}



