import java.io.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;



/**
 * Created by fangyj on 2017/2/20.
 */
public class _403Forbidden {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Writer writer = new OutputStreamWriter(System.out);
    static class Node{
		Node[] next;
		boolean isRule;
		boolean action;
		public Node() {
			next = new Node[2];
		}
		
	}
	static Node root = new Node();
	
	public static void main(String[] args) throws IOException, ParseException {
	  	String[] line = reader.readLine().split(" ");
	  	int n = Integer.parseInt(line[0]);
	  	int m = Integer.parseInt(line[1]);
		for(int i = 0 ; i < n ; i++){
			String rule = reader.readLine();
			addRule(rule);
		}
		for(int i = 0 ; i < m ; i++){
			String rule = reader.readLine();
			boolean res = getRule(rule);
			writer.write(res? "YES\n":"NO\n");
		}
		writer.flush();
	}
	private static boolean getRule(String rule) {
		String[] r = rule.split("\\.");
		int ip = 0;
		for(int i = 0 ; i <= 3; i++){
			ip += Integer.parseInt(r[i])<<(8*(3-i));
		}	
		boolean action = get(ip);
		return action;
	}
	private static boolean get(int ip) {
		Node node = root;
		Node match = null;
		for(int i = 1 ; i <= 32; i++){//一直找到最后一条匹配的规则
			int digit = (ip>>(32-i)) & 1;
			if(node.isRule)  
				match = node;
			node = node.next[digit];
			if(node == null){
				if(match == null) 
					return true;
				else 
					return match.action;
			}
		}
		if(node.isRule)  
			match = node;
		return match.action;
	}
	private static void addRule(String rule) {
		String[] r = rule.split("\\s+|\\.|\\/");
		int mask = 32;
		if(r.length == 6) mask = Integer.parseInt(r[5]);
		int ip = 0;
		for(int i = 1 ; i <= 4; i++){
			ip += Integer.parseInt(r[i])<<(8*(4-i));
		}
		insert(r[0],ip,mask);
	}
	private static void insert(String action, int ip, int mask){
		Node node = root;
		for(int i = 1 ; i <= mask; i++){
			int digit = (ip>>(32-i)) & 1;
			if(node.isRule) return;
			if(node.next[digit] == null)
				node.next[digit] = new Node();
			node = node.next[digit];		
		}
		if(!node.isRule){
			node.isRule = true;
			node.action = action.equals("allow");
		}
	}

}
