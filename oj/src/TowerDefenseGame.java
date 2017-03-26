import java.util.*;

public class TowerDefenseGame {
	private static int n;
	private static Node[] nodeList;
	private static List<Node>[] adj;
	
	private static class Node implements Comparable<Node>{
		int v;
		int p;
		int q;
		public Node(int v, int p ,int q){
			this.v = v;
			this.p = p;
			this.q = q;
		}
		@Override
		public int compareTo(Node o) {
			Node that = (Node)o;
			return -(this.q - that.q);
		}
	}
	
	public static void towerDefenseGame(){
		dfs(0);
		System.out.println(nodeList[0].p);
	}
	private static void dfs(int v){	
		for(Node n : adj[v]){						
			 dfs(n.v);			
		}		
		Collections.sort(adj[v]);
		
		int needMoney = nodeList[v].p;
		int remain = nodeList[v].q -nodeList[v].p;
		
		for(Node n : adj[v]){//这里的n.p和n.q在之前的dfs调用中已经重新计算过了						
			needMoney = Math.max(needMoney, -(remain - n.p));
			remain += (n.q - n.p);		
		}
		
		nodeList[v].p = needMoney;
		nodeList[v].q = remain + needMoney;
	}
    
	public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		adj = new ArrayList[n];
		nodeList = new Node[n];
		for(int i = 0 ; i <n ; i++){
			adj[i] = new ArrayList<Node>(); 
			int p = scan.nextInt();
			int q = scan.nextInt();
			nodeList[i] = new Node(i,p,q);
		}
		for(int i = 0 ; i < n - 1; i++){
			int v = scan.nextInt();
			int w = scan.nextInt();
			if(v > w){
				int t = w;				
				w = v;
				v = t;
			}
			adj[v - 1].add(nodeList[w - 1]);
		}
		towerDefenseGame();
    }
}
