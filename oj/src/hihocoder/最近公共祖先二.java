package hihocoder;
import java.io.*;
import java.text.ParseException;
import java.util.*;


public class 最近公共祖先二 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;
	static final int  grey = 0;//表示dfs正在进行中的顶点
	static final int  black = 1;//表示dfs已经退出的顶点
	static final int  white = -1;//表示dfs还没有到的顶点
	static HashMap<String,TreeNode> treeNodes;
	static HashMap<String,String> belong;
	static String[] ans;
	static class Query{
		String name;
		int index;
		public Query(String name, int index) {
			super();
			this.name = name;
			this.index = index;
		}
	}
	static class TreeNode{
		String name;
		TreeNode father;
		List<TreeNode> sons;
		int color;
		public TreeNode(String name) {
			super();
			this.name = name;
			this.sons = new LinkedList<>();
			color = white;
		}
	}
	private static void union(String name1, String name2) {
		String p1 = findRoot(name1);
		String p2 = findRoot(name2);
		belong.put(p2, p1);		
	}
	private static String findRoot(String name){
		String parent = belong.get(name);
		while(!parent.equals(name)){
			name = parent;
			parent = belong.get(name);
		}
		return name;
	}
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		treeNodes = new HashMap<>(n);	
		belong = new HashMap<>();
		TreeNode root = null;
		
		for(int i = 1 ; i <= n ; i++){
			String father = next();
			String son = next();
			TreeNode f;
			TreeNode s;	
			if(!treeNodes.containsKey(father)){
				f = new TreeNode(father);
				treeNodes.put(father, f);
				belong.put(father, father);
			}
			else 
				f = treeNodes.get(father);
			
			if(!treeNodes.containsKey(son)){
				s = new TreeNode(son);
				treeNodes.put(son, s);
				belong.put(son, son);
			}
			else 
				s = treeNodes.get(son);
			
			s.father = f;
			f.sons.add(s);
			
			if(i == 1){
				root = f;
			}
		}
		m = nextInt();
		HashMap<String,List<Query>> querys = new HashMap<>(m);
		for(int i = 1 ; i <= m ; i++){
			String name1 = next();
			String name2 = next();			
			if(!querys.containsKey(name1)){
				List<Query> list = new LinkedList<>();
				list.add(new Query(name2,i));
				querys.put(name1, list);
			}
			else
				querys.get(name1).add(new Query(name2,i));
			if(!querys.containsKey(name2)){
				List<Query> list = new LinkedList<>();
				list.add(new Query(name1,i));
				querys.put(name2, list);
			}
			else
				querys.get(name2).add(new Query(name1,i));			
		}
		ans = new String[m+1];		
		dfs(root,querys);
		for(int i = 1 ; i <= m ; i++){
			System.out.println(ans[i]);
		}
	}
	private static void dfs(TreeNode t,HashMap<String,List<Query>> querys) {
		t.color = grey;
		List<Query> list = querys.get(t.name);
		if(list!=null){
			for(Query query :list){
				if(ans[query.index] != null)
					continue;
				int color = treeNodes.get(query.name).color;
				if(color == grey){
					ans[query.index] = query.name;
				}
				else if(color == black){			
					ans[query.index] = findRoot(query.name);
				}
			}
		}
		for(TreeNode son : t.sons){
			dfs(son,querys);
		}
		t.color = black;
		if(t.father != null)
			union(t.father.name, t.name);
	}

}


