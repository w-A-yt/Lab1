package lab.w1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import gTest.Lab1Test.WordNode;

public class Main {


  /**a.
   *
   */
  public static class WordNode {
    private String word;
    private List<WordNode> outwn = new ArrayList<>();
    private List<Integer> weight = new ArrayList<>();
  }

  /**
   * a.
   * 2017.10
   * @param head false
   * @param nextWord false
   * @return false
   */
  public static WordNode addWeight(WordNode head, String nextWord) {
    if (head.outwn.size() < 2) {
      head.weight.add(1);
    } else {
      for (int i = 0; i < head.outwn.size() - 1; i++) {
        if (head.outwn.get(i).word.equals(nextWord)) {
          head.weight.set(i, head.weight.get(i) + 1);
        }
      }
      head.weight.add(1);
    }
    return head;
  }

  public static WordNode visitoutnode(WordNode wn) {
    int i = wn.outwn.size() - 1;
    return wn.outwn.get(i);
  }

  /**
   * b.
   * @param head false
   * @param nodeword false
   * @param wordNodes false
   * @return false
   */
  public static WordNode addWordNode(WordNode head, String nodeword, List<WordNode> wordNodes) {
    int site;
    for (site = 0; site < wordNodes.size(); site++) {
      if (head.word == wordNodes.get(site).word) {
        break;
      }
    }
    WordNode node = new WordNode();
    WordNode headown = visitoutnode(head);
    node.word = nodeword;
    node.outwn.add(headown);
    int i = head.outwn.size() - 1;
    head.outwn.remove(i);
    head.outwn.add(node);
    head.weight.add(1);
    wordNodes.set(site, head);
    return node;
  }

  /**
   * c.
   * @param head false
   * @param wordNodes false
   * @param j false
   * @return false
   */
  public static WordNode addNoded(WordNode head, List<WordNode> wordNodes, int j) {//NOPMD
    WordNode noded = wordNodes.get(j);
    WordNode headown = visitoutnode(head);
    noded.outwn.add(headown);
    int i = head.outwn.size() - 1;
    head.outwn.remove(i);
    head.outwn.add(noded);
    head = addWeight(head, wordNodes.get(j).word);
    wordNodes.set(j, noded);
    return noded;
  }

  /**
   * d.
   * @param words false
   * @param file false
   */
  public static void writeToFile(List<String> words, File file) {

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

  /**
   * e.
   * @param wordNodes false
   */
  public static void showInDot(List<WordNode> wordNodes) {
    List<String> sdot = new ArrayList<>();
    sdot.add("digraph G {");
    for (WordNode w : wordNodes) {
      if (w != null) {
        List<WordNode> wout = w.outwn;
        for (int i = 0; i < wout.size(); i++) {
          if (wout.get(i) != null) {
            sdot.add(w.word + "->" + wout.get(i).word + "[label = \"" + w.weight.get(i) + "\"];");
          }
        }
      }
    }
    sdot.add("}");
    writeToFile(sdot, new File("D:/work/java/RFF/result.dot"));
  }

  /**
   * f.
   * @param workSpace false
   * @param wordPath false
   */
  public static void openGra(String workSpace, String wordPath) {
    try {
      String[] cmd = {workSpace, wordPath};
      Process proc = Runtime.getRuntime().exec(cmd);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * g.
   * @param args false
   */
  public static void main(String[] args) {
    ReadFromFile read = new ReadFromFile();
    System.out.println("请输入文件路径：");
    Scanner sc = new Scanner(System.in);
    String f = sc.nextLine();
    File file = new File(f);// D:/work/java/RFF/test.txt
    while (file.exists()) {
      System.out.println("请输入文件路径：");
      sc = new Scanner(System.in);
      f = sc.nextLine();
      file = new File(f);
    }
    List<String> words = ReadFromFile.getLowerCaseWords(file);

    List<WordNode> wordNodes = new ArrayList<>();

    WordNode firstNode = new WordNode();
    firstNode.word = words.get(0);

    // 调用节点,初始化一个next
    firstNode.outwn.add(null);
    wordNodes.add(firstNode);
    for (int i = 1; i < words.size(); i++) {
      int flag = 1;
      for (int j = 0; j < wordNodes.size(); j++) {
        if (words.get(i).equals(wordNodes.get(j).word)) {
          flag = 0;
          firstNode = addNoded(firstNode, wordNodes, j);
        }
      }
      if (flag == 1) {
        firstNode = addWordNode(firstNode, words.get(i), wordNodes);
        wordNodes.add(firstNode);
      }
    }
    showInDot(wordNodes);
    String openGra = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\gvedit.exe";
    String path = "D:\\work\\java\\RFF\\result.dot";
    openGra(openGra, path);

    ReadFromFile.WriteToFile(words, new File("C:/lab/output.txt"));

    int vex = words.size();
    int edge = words.size() - 1;
    String[] s = new String[words.size()];
    words.toArray(s);
    Graph g = new Graph(vex, edge, s);
    g.showDirectedGraph();
    String func;
    System.out.println('\n' + "查询桥接词按1，生成新文本按2，最短路径按3，随机游走按4，退出按5：");
    func = sc.nextLine();
    while (func.equals("5")) {
      if (func.equals("1")) {
        String qs1;
        System.out.println("输入词桥查询的单词1：");
        qs1 = sc.nextLine();
        qs1 = qs1.toLowerCase();
        System.out.println("输入词桥查询的单词2：");
        String qs2;
        qs2 = sc.nextLine();
        qs2 = qs2.toLowerCase();
        String result;
        result = g.queryBridgeWords(qs1, qs2);
        if (g.exsit == 1) {
          System.out.println("The bridge words from " + "\""
              + qs1 + "\"" + " to " + "\"" + qs2 + "\""
                + " are:" + result);
        } else {
          System.out.println(result);
        }

      } else if (func.equals("2")) {
        System.out.println("请输入文本：");
        String nt;
        nt = sc.nextLine();// Seek to explore new and exciting synergies
        g.generateNewText(nt);
      } else if (func.equals("3")) {

        String sp1;
        System.out.println("当输入的word2为**是则遍历其与图中所有顶点的最短路径");
        System.out.println("输入最短路径的word1：");
        sp1 = sc.nextLine();
        sp1 = sp1.toLowerCase();
        System.out.println("输入最短路径的word2：");
        String sp2;
        sp2 = sc.nextLine();
        sp2 = sp2.toLowerCase();
        g.calcShortestPath(sp1, sp2);
      } else if (func.equals("4")) {
        g.randomWalk();
      } else {
        System.out.println("输入错误");
      }
      System.out.println("查询桥接词按1，生成新文本按2，最短路径按3，随机游走按4，退出按5：");
      func = sc.nextLine();
    }
    sc.close();
  }
}
