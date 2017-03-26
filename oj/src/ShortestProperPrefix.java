import java.io.*;
import java.text.ParseException;
import java.util.*;
public class ShortestProperPrefix {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	static int n;
	static class Node{
		String prefix;
		Node[] next = new Node[26];
		int cnt;
	}
	static Node root = new Node();
	static int cnt;
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		for(int i = 0 ; i < n; i++){
			String query = next();
			insert(query);
		}
		find(root);
		System.out.println(cnt);
	}
	private static void find(Node node) {
		if(node == null) return;
		if(node.cnt <= 5)
		{
			cnt++;			
			return;
		}		
		for(int i = 0 ; i < 26 ; i++){
			if(node.next[i] != null){
				find(node.next[i]);
			}			
		}
	}
	private static void insert(String query) {
		Node cur = root;
		int i = 0;
		while(i < query.length()){
			cur.cnt++;
			char c = query.charAt(i);
			if(cur.next[c-'a'] == null)
				cur.next[c-'a'] = new Node();
			cur = cur.next[c-'a'];
			i++;
		}
		cur.prefix = query;
		cur.cnt++;
	}
	

}
