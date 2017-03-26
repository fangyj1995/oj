package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.*;
public class 清理海报 {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n,w,h;
	static LinkedList<Integer>[] adj;
	static class Poster{
		int id;
		int xmin;
		int ymin;
		int xmax;
		int ymax;
		boolean lt;
		boolean rt;
		boolean lb;
		boolean rb;
		public Poster(int id, int xmin, int ymin, int xmax, int ymax) {
			super();
			this.id = id;
			this.xmin = xmin;
			this.ymin = ymin;
			this.xmax = xmax;
			this.ymax = ymax;
		}	
		private boolean hasPoint(int x,int y){
			return x<xmax && x>xmin && y<ymax && y>ymin;
		}
		public boolean intersect(Poster that){
			return !(
					this.xmin>=that.xmax||this.ymin>=that.ymax||
					that.xmin>=this.xmax||that.ymin>=this.ymax
					 );
		}
		private void checklt(Poster other){
			if (other.id > id && other.hasPoint(xmin, ymax)) lt = true;
		}
		private void checkrt(Poster other){
			if (other.id > id && other.hasPoint(xmax, ymax)) rt = true;
		}
		private void checklb(Poster other){
			if (other.id > id && other.hasPoint(xmin, ymin)) lb = true;
		}
		private void checkrb(Poster other){
			if (other.id > id && other.hasPoint(xmax, ymin)) rb = true;
		}
		private  boolean isAllCovered(){
			return lt&&rt&&lb&&rb;
		}
	}
	static Poster[] posters;
	static boolean[] unable;
	static int cnt;
	public static void main(String[] args) throws IOException, ParseException {
		w = nextInt();
		h = nextInt();
		n = nextInt();
		posters = new Poster[n];
		adj = new LinkedList[n];
		unable = new boolean[n];
		for(int i = 0 ; i < n ; i++)
		{
			Poster p = new Poster(i,nextInt(),nextInt(),nextInt(),nextInt());
			posters[i] = p;
			adj[i] = new LinkedList<>();
		}
		for(int i = 0 ; i < n ; i++)
		{
			Poster later = posters[i];
			for(int j = 0 ; j < i ; j++)
			{
				Poster before = posters[j];
				before.checklt(later);
				before.checkrt(later);
				before.checklb(later);
				before.checkrb(later);
				if(later.intersect(before)){
					int v = before.id;
					int w = later.id;
					adj[v].add(w);
				}
			}
		}
		for(int i = 0 ; i < n ; i++){
			if(posters[i].isAllCovered())
				unable[i] = true;
		}
		int max = Integer.MIN_VALUE;
		int id = 0;
		for(int i = 0 ; i < n ; i++){
			cnt = 0;
			if(!unable[i])
			{
				uncover(i,new boolean[n]);
				if(cnt > max || (cnt == max && i < id)){
					max = cnt;
					id = i;
				}
			}
		}
		System.out.println(max+" "+(id+1));
	}
	private static void uncover(int v,boolean[] marked) {
		marked[v] = true;
		cnt++;
		for(int w:adj[v]){
			if(!marked[w])uncover(w,marked);
		}
	}
}

