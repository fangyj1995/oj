package hihocoder;
import java.io.*;
import java.text.ParseException;
import java.util.*;


public class 并查集二 {
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
	static HashMap<String,String> belong;
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		belong = new HashMap<>();
		int cnt = 0;
		for(int i = 0 ; i < n ; i++){
			int op = nextInt();
			String name1 = next();
			String name2 = next();
			if(!belong.containsKey(name1))
				belong.put(name1, name1);
			if(!belong.containsKey(name2))
				belong.put(name2, name2);

			if(op == 0){				
				union(name1, name2);
			}
			else{
				boolean res = connected(name1, name2);
				System.out.println((res?"yes":"no"));
			}
		}
	}
	private static boolean connected(String name1, String name2) {
		return findRoot(name1).equals(findRoot(name2));
	}
	private static void union(String name1, String name2) {
		String p1 = findRoot(name1);
		String p2 = findRoot(name2);
		belong.put(p1, p2);
		
	}
	private static String findRoot(String name){
		String parent = belong.get(name);
		while(!parent.equals(name)){
			name = parent;
			parent = belong.get(name);
		}
		return name;
	}

}













