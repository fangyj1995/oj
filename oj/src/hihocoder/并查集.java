package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.*;


public class 并查集 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;
	static int maxn = 100000;
	static int[] id ;
	static int[] size;
	static HashMap<String,Integer> map;
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		id = new int[n+1];
		size = new int[n+1];
		map = new HashMap<>();
		for(int i = 0 ; i <= n ; i++){
			id[i] = i;
		}
		Arrays.fill(size, 1);
		int cnt = 0;
		for(int i = 0 ; i < n ; i++){
			int op = nextInt();
			String name1 = next();
			String name2 = next();
			if(!map.containsKey(name1))
				map.put(name1, cnt++);
			if(!map.containsKey(name2))
				map.put(name2, cnt++);
			
			int p = map.get(name1);
			int q = map.get(name2);
			if(op == 0){				
				union(p,q);
			}
			else{
				boolean res = connected(p,q);
				System.out.println((res?"yes":"no"));
			}
		}
	}
	private static boolean connected(int p, int q) {	
		return findRoot(p) == findRoot(q);
	}
	private static int findRoot(int p){
		while(id[p] != p){
			p = id[p];
		}
		return p;
	}
	private static void union(int p, int q) {
		int pRoot = findRoot(p);
		int qRoot = findRoot(q);
		assert( (id[pRoot] == pRoot) &(id[qRoot] == qRoot));
		if(size[pRoot]>size[qRoot]){//q并入p
			id[qRoot] = pRoot;
			size[qRoot]++;
		}
		else{//p并入q
			id[pRoot] = qRoot;
			size[pRoot]++;
		}
	}

}














