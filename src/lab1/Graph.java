package lab1;

import java.util.Scanner;
import java.util.Random;
public class Graph {
	//邻接表中表对应的链表的顶点
	//定义边的结构体
	private class ENode
	{
		int ivex;						//该边所指向的顶点的位置
		ENode nextEdge;					//指向下一条弧的指针
		int weight=0;					//边的权重
		Boolean hasvisted=false;		//该边是否访问过
	}
	
	//邻接表中表的顶点
	//定义顶点的结构体
	private class VNode
	{
		String data="*";				//顶点信息
		ENode firstEdge;				// 指向第一条依附该顶点的弧
	}
	
	private int vertex=0;				//有向图中顶点的个数
	private int edge=0;					//有向图中边的个数
	public int exsit=0;					//标识两个单词之间是否含有桥接词，(1为含有)
	private VNode[] mVertex;	//输入的点的集合(重复的点记为*)
	private String[] StrPath=new String [100];
	private int NumofPath=0;
		public Graph(int v,int e,String[] w)	//图的构造函数
		{
			edge=e;
			mVertex=new VNode[v];
			vertex++;
			mVertex[0]=new VNode();
			mVertex[0].data=w[0];	
			mVertex[0].firstEdge=null;
			for(int i=1;i<v;i++)				//构造顶点
			{
				mVertex[i]=new VNode();
				int flag1=0;
				for(int j=0;j<i;j++)
				{
					if(mVertex[j].data.equals(w[i]))
					{
						flag1=1;
					}
				}
				if(flag1==0)
				{
					vertex++;
					mVertex[i].data=w[i];
					mVertex[i].firstEdge=null;
				}
			}
			for(int i=0;i<edge;i++)				//构造边
			{
				String str1=w[i];
				String str2=w[i+1];
				int p1=getPosition(str1);
				int p2=getPosition(str2);
				ENode node1=new ENode();
				node1.ivex=p2;
				if(mVertex[p1].firstEdge==null)
				{
					mVertex[p1].firstEdge=node1;
					mVertex[p1].firstEdge.weight++;
				}
				else
				{
					int flag=0;
					ENode t=mVertex[p1].firstEdge;
					while(t!=null)
					{
						if(t.ivex==node1.ivex)
						{
							t.weight++;
							flag=1;
							break;
						}
						t=t.nextEdge;
					}
					if(flag==0)
					{
						linkNext(mVertex[p1].firstEdge,node1);
					}
				}
			}
		}

//将node节点链接在list后面
public static void linkNext(ENode list,ENode node)
{
	ENode t=list;
	while(t.nextEdge!=null)
	{
		t=t.nextEdge;
	}
	t.nextEdge=node;
	t.nextEdge.weight++;
}

//获得字符串在邻接表头中的位置
public int getPosition(String s)		
{
	int po=0;
	for(int i=0;i<mVertex.length;i++)
	{
		if(mVertex[i].data.equals(s))
		{
			po=i;
			break;
		}
	}
	return po;
}

public String getStr() 
{
	String myStr="";
	Scanner scan=new Scanner(System.in);
	if(scan.hasNextLine())
	{
		String str1=scan.nextLine();
		myStr=myStr+str1;
	}
	scan.close();
	
	return myStr;
}

public void showDirectedGraph() {
    System.out.printf("List Graph are:\n");
    System.out.printf("hello git");
    for (int i = 0; i < mVertex.length; i++) {
    	if(!(mVertex[i].data.equals("*")))
    	{
    		System.out.println("hhhhh");
    		System.out.println(mVertex[i].data);
    		ENode node = mVertex[i].firstEdge;
    		while (node != null) {
    			System.out.println("->"+mVertex[node.ivex].data+"  weight:"+node.weight);		
                node = node.nextEdge;
    		}
    	}
    }
}

//查询word1与word2的桥接词
public String queryBridgeWords(String word1, String word2)
{
	System.out.println("bridgewords");
	String s="";
	int num=0;
	String[] Bword=new String[100];
	int po1=0;
	int po2=0;
	int flag1=0;
	int flag2=0;
	for(int i=0;i<mVertex.length;i++)
	{
		if((mVertex[i].data.equals(word1)))
    	{
			po1=i;
			flag1=1;
			break;
    	}
	}
	for(int i=0;i<mVertex.length;i++)
	{
		if((mVertex[i].data.equals(word2)))
    	{
			po2=i;
			flag2=1;
			break;
    	}
	}
	if(flag1==0&&flag2==1)
	{
		s="No "+"\""+ word1 +"\""+" in the graph";
	}
	if(flag1==1&&flag2==0)
	{
		s="No "+"\""+word2 +"\""+" in the graph";
	}
	if(flag1==0&&flag2==0)
	{
		s="No "+"\""+word1+"\"" +" and "+"\""+word2+"\"" +" in the graph";
	}
	if(flag1==1&&flag2==1)
	{	
		ENode p=mVertex[po1].firstEdge;
		while(p!=null)
		{
			int position=p.ivex;
			if(mVertex[position].firstEdge!=null)
			{
				if(mVertex[position].firstEdge.ivex==po2)
				{
					Bword[num]=mVertex[position].data;
					num++;
				}
				else
				{
					mVertex[position].firstEdge=mVertex[position].firstEdge.nextEdge;
				}
			}
			p=p.nextEdge;		
		}
	
	String str1="";
	for(int i=0;i<num;i++)
	{
		str1+=Bword[i];
		str1+=",";
	}
	if(num==0) {
		s="No bridge words from "+"\""+ word1+"\""+" to "+"\""+word2+"\""+"!";
	}
	if(flag1==1&&flag2==1&&num!=0)
	{
		exsit=1;
		s=str1;
	}
}
	return s;
}


//根据桥接词生成新文本
public String generateNewText(String inputText)
{
	String[]str=new String [100];
	String s="";
	String str1="";
	String str2="";
	int num=0;
	for(String retval:inputText.split(" "))
	{
		str[num]=retval;
		num++;
	}
	for(int i=0;i<num;i++)
	{	
		exsit=0;
		str2=queryBridgeWords(str[i],str[i+1]);
		s+=str[i];
		if(exsit==1)
		{
			
			for(String retval:str2.split(","))
			{
				str1=retval;
				s+=" ";
				s+=str1;
				break;
			}
		}
		s+=" ";
		
	}
	System.out.println(s);
	return s;
}

//递归输出最短路径所经过的点
int dis(int num1,int num2,int path[][])
{
	int pos1=num1;int pos2=num2;
	int p1;
	int pathofv=path[pos1][pos2];
	if(pathofv>=0)
	{
		p1=dis(pos1,pathofv,path);
		System.out.println(mVertex[pathofv].data+"->");
		pathofv=p1+dis(pathofv,pos2,path);
	}
	return pathofv;
}

//利用floyd算法求最短路径
//当输入的word2=="**"时，计算word1与图中其他点的最短路径
String calcShortestPath(String word1, String word2)
{
	
	String s="";
	int [][]g=new int[edge+1][edge+1];
	int [][]path=new int[edge+1][edge+1];
	int pos1=0;
	int pos2=0;
	int v;
	ENode p;
	if (word2.equals("**"))
	{
		for(int i=0;i<edge+1;i++)
		{
			if(!(mVertex[i].data.equals(word1))&&mVertex[i].data!="**"&&mVertex[i].data!="*")
			{
				NumofPath=0;
				for(int j=0;j<100;j++)
				{
					StrPath[j]=null;
				}
				calcShortestPath(word1,mVertex[i].data);
			}
		}
	}
	else
	{
	for(int i=0;i<edge+1;i++)
	{
		if(mVertex[i].data.equals(word1))
		{
			pos1=i;
		}
		if(mVertex[i].data.equals(word2))
		{
			pos2=i;
		}
	}
	for(int i=0;i<edge+1;i++)
	{
		for(int j=0;j<edge+1;j++)
		{
			g[i][j]=10000;
			path[i][j]=-1;
		}
	}
	
	for(int i=0;i<edge+1;i++)
	{
		if(mVertex[i].data.equals("*"))
		{
			for(int j=0;j<edge+1;j++)
			{
				g[i][j]=-1;
				g[j][i]=-1;
			}
		}
		else
		{	
			p=mVertex[i].firstEdge;
			while(p!=null)
			{
				v=p.ivex;
				g[i][v]=p.weight;
				path[i][v]=-1;
				p=p.nextEdge;
			}
			g[i][i]=0;
		}
	}
	for(int k=0;k<g[0].length;k++)
	{
		for(int i=0;i<g[0].length;i++)
		{
			for(int j=0;j<g[0].length;j++)
			{
				if(g[i][k]!=-1&&g[k][j]!=-1&&g[i][j]!=-1&&((g[i][k]+g[k][j])<g[i][j]))//遇到更小的路径则更新
				{
					g[i][j]=g[i][k]+g[k][j];
					path[i][j]=k;
				}
			}
		}
	}
	if(g[pos1][pos2]==10000)
	{
		s=word1+"到"+word2+"不可达";
		System.out.println(s);
	}
	else
	{
		System.out.println(word1+"到"+word2+"的最短路径为：");
		System.out.println(mVertex[pos1].data+"->");
		dis(pos1,pos2,path);
		System.out.println(mVertex[pos2].data);
	}
	}
	
	return s;
}

//随机游走
//先生成一个随机数确定从随机产生的顶点进行访问，再生成一个随机数从与顶点相邻的边进行随机访问
String randomWalk()
{
	String s="";
	int min=0;
	int max=edge+1;
	int num=0;
	Random random = new Random();
	int r=random.nextInt(max)%(max-min+1) + min;
	while(mVertex[r].data=="*")
	{
		r=random.nextInt(max)%(max-min+1) + min;
	}
	int []order=new int[edge+10];
	for(int i=0;i<edge+10;i++)
	{
		order[i]=-1;
	}
	order[num]=r;
	if(mVertex[r].firstEdge!=null)
	{
		ENode e=mVertex[r].firstEdge;
		ENode p=mVertex[r].firstEdge;
		int numofv=0;
		while(p!=null)
		{
			p=p.nextEdge;
			numofv++;
		}
		int r1=random.nextInt(numofv)%(numofv-1+1) +1;
		while(r1!=1)
		{
			e=e.nextEdge;
			r1--;
		}
		if(e!=null)
		{
			e.hasvisted=true;
			order[num]=e.ivex;
			num++;
		}
		ENode next=mVertex[e.ivex].firstEdge;
	while(next!=null)
	{
		int num1=0;
		int flag=0;
		ENode p1=next;
		while(p1!=null)
		{
			if(p1.hasvisted==false)
			{
				flag=1;
			}
			p1=p1.nextEdge;
			num1++;
		}
		if(flag==0)
		{
			break;
		}
		r1=random.nextInt(num1)%(num1-1+1) +1;
		while(r1!=1)
		{
			next=next.nextEdge;
			r1--;
		}
		if(next!=null)
		{
			if(next.hasvisted!=true)
			{
				next.hasvisted=true;
				order[num]=next.ivex;
				num++;
				next=mVertex[next.ivex].firstEdge;
			}
		}
	}
		
	for(int i=0;order[i]!=-1;i++)
	{
		s+=mVertex[order[i]].data;
		s+=" ";
	}
	}
	else
	{
		s=mVertex[r].data;
	}
	System.out.println(s);
	return s;
}



public static void main(String[] args) {
	// TODO Auto-generated method stub

}

}