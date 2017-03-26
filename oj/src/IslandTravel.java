import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.nio.Buffer;
import java.util.*;

/**
 * Created by fangyj on 2017/2/20.
 */

public class IslandTravel {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}       

	static class Point{
	    int x;
	    int y;
	    int id;
	    public  Point(int x, int y, int id){
	        this.x = x;
	        this.y = y;
	        this.id  = id;
	    }
	    public int distTo(Point that){
	        return Math.min(Math.abs(this.x - that.x), Math.abs(this.y - that.y));
	    }
	    static class  xComparator implements Comparator<Point>{
	        @Override
	        public int compare(Point o1, Point o2) {
	            return o1.x - o2.x;
	        }
	    }
	    static class  yComparator implements Comparator<Point>{
	        @Override
	        public int compare(Point o1, Point o2) {
	            return o1.y - o2.y;
	        }
	    }
	}
	static class Node{
	    int to;
	    int weight;
	    public Node(int to, int weight) {
	        this.to = to;
	        this.weight = weight;
	    }
	}

	static Point[] points;
    static int minCost = Integer.MAX_VALUE;
    static List<Node>[] adj;
    static int[] distTo;

    public static void main(String[] args) throws IOException{
    	
        int n = nextInt();
        points = new Point[n];
        adj = (List<Node>[]) new LinkedList[n];
        for(int i = 0 ; i < n ; i++){
            adj[i] = new LinkedList<Node>();
            int x  = nextInt();
            int y  = nextInt();
            points[i] = new Point(x,y,i);
        }
        construct();
        minDist();
        System.out.print(minCost);

    }

    private static void minDist() {
        distTo = new int[points.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[0] = 0;
        Queue<Integer> queue = new LinkedList<Integer>();		
		boolean[] onQueue = new boolean[points.length+1];
        queue.offer(0);
        while(!queue.isEmpty()){
            int min = queue.poll();
            onQueue[min] = false;
            relax(min,queue,onQueue);
        }
        minCost = distTo[points.length - 1];
    }

    private static void relax(int v,Queue<Integer> queue,boolean[] onQueue) {
        for(Node n: adj[v]){
            int w = n.to;
            if(distTo[w] > distTo[v] + n.weight) {
                distTo[w] = distTo[v] + n.weight;
                if(!onQueue[w])
                {
                	queue.offer(w);
                	onQueue[w] = false;
                }
            }
        }
    }

    private static void construct() {
        Arrays.sort(points,new Point.xComparator());
        for(int i = 1 ;i < points.length; i++){
            addEdge(points[i],points[i - 1]);
        }
        Arrays.sort(points,new Point.yComparator());
        for(int i = 1 ;i < points.length; i++){
            addEdge(points[i],points[i - 1]);
        }
    }
    private static void addEdge(Point i, Point j) {
        int weight = i.distTo(j);
        adj[i.id].add(new Node(j.id,weight));
        adj[j.id].add(new Node(i.id,weight));
    }
}
