package hihocoder;
import java.io.*;
import java.text.ParseException;
import java.util.*;



public class 最近公共祖先三 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;
	static TreeNode[][] higher;
	static final int maxPower = 20;
	static HashMap<String,TreeNode> nodes;
	static TreeNode[] dfsOrder;
	static int index = 1;
	static class TreeNode{
		String name;
		TreeNode father;
		List<TreeNode> sons;
		int lastPos;
		int height;
		public TreeNode(String name) {
			super();
			this.name = name;
			this.sons = new LinkedList<>();
		}
		@Override
		public String toString() {
			return "TreeNode [name=" + name + ", lastPos=" + lastPos
					+ ", depth=" + height + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();	
		nodes = new HashMap<>(n);	
		TreeNode root = null;
		higher = new TreeNode[2*n+2][maxPower];
		dfsOrder = new TreeNode[2*n+2];
					
		for(int i = 1 ; i <= n ; i++)
		{
			String father = next();
			String son = next();
			TreeNode f;
			TreeNode s;	
			if(!nodes.containsKey(father)){
				f = new TreeNode(father);
				nodes.put(father, f);
			}
			else 
				f = nodes.get(father);		
			if(!nodes.containsKey(son)){
				s = new TreeNode(son);
				nodes.put(son, s);
			}
			else 
				s = nodes.get(son);		
			s.father = f;
			f.sons.add(s);	
			if(i == 1){
				root = f;
			}
		}
		dfs(root);
		precal(2*n+1);
		m = nextInt();
		for(int i = 1 ; i <= m; i++){
			String name1 = next();
			String name2 = next();				
			TreeNode res = query(name1,name2);
			out.println(res.name);		
		}
		out.flush();
	}
	
	private static void dfs(TreeNode root) {
		root.lastPos = index;
		dfsOrder[index++] = root;
		int maxHeight = 0;
		if(root.sons != null)
		{
			for(TreeNode t:root.sons)
			{
				dfs(t);
				maxHeight = Math.max(maxHeight, t.height);
				root.lastPos = index;
				dfsOrder[index++] = root;
			}
		}		
		root.height = maxHeight+1;
	}
	private static void precal(int n) {
		for(int i = 1 ; i <= n ; i++){
			higher[i][0] = dfsOrder[i];
		}
		for(int len = 2, power = 1 ; len <= n ; len<<=1,power++){
			for(int i = 1 ; i+len-1<=n ; i++){
				int halfLen = (len>>1);
				if(higher[i][power-1].height > higher[i+halfLen][power-1].height)
					higher[i][power] = higher[i][power-1];
				else
					higher[i][power] = higher[i+halfLen][power-1];
			}
		}
	}
	private static TreeNode query(String name1, String name2) {
		int l = nodes.get(name1).lastPos;
		int r = nodes.get(name2).lastPos;
		if(r < l){
			int temp = l;
			l = r;
			r = temp;
		}
		
		int len = r-l+1;
		int power = 0;
		while(len != 1){
			len >>= 1;
			power++;
		}
		len = (1 << power);
		TreeNode max;
		if(higher[l][power].height > higher[r-len+1][power].height)
			max = higher[l][power];
		else
			max = higher[r-len+1][power];
		return max;
	}


}

/**
 * 
 * 
#1069 : 最近公共祖先·三

 


时间限制:10000ms

单点时限:1000ms

内存限制:256MB


描述

上上回说到，小Hi和小Ho使用了Tarjan算法来优化了他们的“最近公共祖先”网站，但是很快这样一个离线算法就出现了问题：如果只有一个人提出了询问，那么小Hi和小Ho很难决定到底是针对这个询问就直接进行计算还是等待一定数量的询问一起计算。毕竟无论是一个询问还是很多个询问，使用离线算法都是只需要做一次深度优先搜索就可以了的。

那么问题就来了，如果每次计算都只针对一个询问进行的话，那么这样的算法事实上还不如使用最开始的朴素算法呢！但是如果每次要等上很多人一起的话，因为说不准什么时候才能够凑够人——所以事实上有可能要等上很久很久才能够进行一次计算，实际上也是很慢的！

“那到底要怎么办呢？在等到10分钟，或者凑够一定数量的人两个条件满足一个时就进行运算？”小Ho想出了一个折衷的办法。

“哪有这么麻烦！别忘了和离线算法相对应的可是有一个叫做在线算法的东西呢！”小Hi笑道。

小Ho面临的问题还是和之前一样：假设现在小Ho现在知道了N对父子关系——父亲和儿子的名字，并且这N对父子关系中涉及的所有人都拥有一个共同的祖先（这个祖先出现在这N对父子关系中），他需要对于小Hi的若干次提问——每次提问为两个人的名字（这两个人的名字在之前的父子关系中出现过），告诉小Hi这两个人的所有共同祖先中辈分最低的一个是谁？
 提示：最近公共祖先无非就是两点连通路径上高度最小的点嘛！  


输入

每个测试点（输入文件）有且仅有一组测试数据。

每组测试数据的第1行为一个整数N，意义如前文所述。

每组测试数据的第2~N+1行，每行分别描述一对父子关系，其中第i+1行为两个由大小写字母组成的字符串Father_i, Son_i，分别表示父亲的名字和儿子的名字。

每组测试数据的第N+2行为一个整数M，表示小Hi总共询问的次数。

每组测试数据的第N+3~N+M+2行，每行分别描述一个询问，其中第N+i+2行为两个由大小写字母组成的字符串Name1_i, Name2_i，分别表示小Hi询问中的两个名字。

对于100%的数据，满足N<=10^5，M<=10^5, 且数据中所有涉及的人物中不存在两个名字相同的人（即姓名唯一的确定了一个人），所有询问中出现过的名字均在之前所描述的N对父子关系中出现过，且每个输入文件中第一个出现的名字所确定的人是其他所有人的公共祖先。

输出

对于每组测试数据，对于每个小Hi的询问，按照在输入中出现的顺序，各输出一行，表示查询的结果：他们的所有共同祖先中辈分最低的一个人的名字。
样例输入4
Adam Sam
Sam Joey
Sam Micheal
Adam Kevin
3
Sam Sam
Adam Sam
Micheal Kevin
样例输出Sam
Adam
Adam
*/
