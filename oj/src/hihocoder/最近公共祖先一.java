package hihocoder;
import java.io.*;
import java.text.ParseException;
import java.util.*;


public class 最近公共祖先一 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;
	static HashMap<String,TreeNode> map;
	static int res;
	static int[] value;
	static class TreeNode{
		String name;
		TreeNode father;
		public TreeNode(String name) {
			super();
			this.name = name;
		}
	}
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		map = new HashMap<>(n);
		for(int i = 1 ; i <= n ; i++){
			String father = next();
			String son = next();
			TreeNode f;
			TreeNode s;		
			if(!map.containsKey(father)){
				f = new TreeNode(father);
				map.put(father, f);
			}
			else 
				f = map.get(father);
			if(!map.containsKey(son)){
				s = new TreeNode(son);
				map.put(son, s);
			}
			else 
				s = map.get(son);
			s.father = f;
		}
		m = nextInt();
		for(int i = 1 ; i <= m ; i++){
			String name1 = next();
			String name2 = next();			
			TreeNode t1 = map.get(name1);
			TreeNode t2 = map.get(name2);	
			if(t1 == null && t2 == null && name1.equals(name2)){
				System.out.println(name1);
				continue;
			}
			Set<TreeNode> set = new HashSet<TreeNode>();
			while(t1!=null){
				set.add(t1);
				t1=t1.father;
			}
			TreeNode common = null;
			while(t2!=null){
				if(set.contains(t2)){
					common = t2;
					break;
				}
				t2 = t2.father;
			}
			if(common == null)
				System.out.println(-1);
			else
				System.out.println(common.name);
		}
		
	}

}
/**
 * 
#1062 : 最近公共祖先·一

 时间限制:10000ms

单点时限:1000ms

内存限制:256MB


描述

小Ho最近发现了一个神奇的网站！虽然还不够像58同城那样神奇，但这个网站仍然让小Ho乐在其中，但这是为什么呢？

“为什么呢？”小Hi如是问道，在他的观察中小Ho已经沉迷这个网站一周之久了，甚至连他心爱的树玩具都弃置一边。

“嘿嘿，小Hi，你快过来看！”小Ho招呼道。

“你看，在这个对话框里输入我的名字，在另一个对话框里，输入你的名字，再点这个查询按钮，就可以查出来……什么！我们居然有同一个祖祖祖祖祖爷爷？”

“诶，真是诶……这个网站有点厉害啊。”小Hi不由感叹道。

“是啊，这是什么算法啊，这么厉害！”小Ho也附和道。

“别2，我说的是他能弄到这些数据很厉害，而人类的繁殖树这种层数比较浅的树对这类算法的要求可是简单的不得了，你都能写出来呢！”小Hi道。

“啊？我也能写出来？可是……该从哪开始呢？”小Ho困惑了。

小Ho要面临的问题是这样的，假设现在他知道了N个人的信息——他们的父亲是谁，他需要对于小Hi的每一次提问——两个人的名字，告诉小Hi这两个人的是否存在同一个祖先，如果存在，那么他们的所有共同祖先中辈分最低的一个是谁？

提示：不着急，慢慢来，另外我有一个问题：挖掘机技术哪家强？

输入

每个测试点（输入文件）有且仅有一组测试数据。

每组测试数据的第1行为一个整数N，意义如前文所述。

每组测试数据的第2~N+1行，每行分别描述一对父子关系，其中第i+1行为两个由大小写字母组成的字符串Father_i, Son_i，分别表示父亲的名字和儿子的名字。

每组测试数据的第N+2行为一个整数M，表示小Hi总共询问的次数。

每组测试数据的第N+3~N+M+2行，每行分别描述一个询问，其中第N+i+2行为两个由大小写字母组成的字符串Name1_i, Name2_i，分别表示小Hi询问中的两个名字。

对于100%的数据，满足N<=10^2，M<=10^2, 且数据中所有涉及的人物中不存在两个名字相同的人（即姓名唯一的确定了一个人）。

输出

对于每组测试数据，对于每个小Hi的询问，输出一行，表示查询的结果：如果根据已知信息，可以判定询问中的两个人存在共同的祖先，则输出他们的所有共同祖先中辈分最低的一个人的名字，否则输出-1。
样例输入11
JiaYan JiaDaihua
JiaDaihua JiaFu
JiaDaihua JiaJing
JiaJing JiaZhen
JiaZhen JiaRong
JiaYuan JiaDaishan
JiaDaishan JiaShe
JiaDaishan JiaZheng
JiaShe JiaLian
JiaZheng JiaZhu
JiaZheng JiaBaoyu
3
JiaBaoyu JiaLian
JiaBaoyu JiaZheng
JiaBaoyu LinDaiyu
样例输出JiaDaishan
JiaZheng
-1

 */
