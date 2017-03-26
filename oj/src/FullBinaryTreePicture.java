import java.io.*;
import java.text.ParseException;
import java.util.*;
public class FullBinaryTreePicture {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	

	static class TreeNode{
		TreeNode l;
		TreeNode r;
		int level;
		int x;
		int y;
		int dir;//0为左
	}
	static class Rect{
		int xmin,ymin,xmax,ymax;

		public Rect(int xmin, int ymin, int xmax, int ymax) {
			super();
			this.xmin = xmin;
			this.ymin = ymin;
			this.xmax = xmax;
			this.ymax = ymax;
		}
		public boolean hasPoint(int x, int y){
			return((x>=xmin&&x<=xmax)&&(y>=ymin&&y<=ymax));
		}
		@Override
		public String toString() {
			return "Rect [xmin=" + xmin + ", ymin=" + ymin + ", xmax=" + xmax + ", ymax=" + ymax + "]";
		}
		
	}
	static int[] len;
	static int cnt ;
	public static void main(String[] args) throws IOException, ParseException {
		int height = nextInt();
		len = new int[height+5];
		if(height >= 2) len[height-1] = 1;
		if(height >= 3) len[height-2] = 2;
		if(height >= 4){
			for(int i = height-3 ; i >= 1 ; i--){
				len[i] = 2*len[i+1] + 1;
			}
		}
		TreeNode root = new TreeNode();
		root.l = construct(root , height, 0);
		root.r = construct(root , height, 1);
		int n = nextInt();	
		for(int i = 0 ; i < n ; i++){
			cnt = 0;
			int xmin,ymin,xmax,ymax;
			xmin = nextInt();
			ymin = nextInt();
			xmax = nextInt();
			ymax = nextInt();
			Rect r = new Rect(xmin,ymin,xmax,ymax);
			find(root, r);
			System.out.println(cnt);
		}
	}
	static void find(TreeNode tree, Rect r){
		if(tree == null) return;
		if(r.hasPoint(tree.x, tree.y))
			cnt++;
		find(tree.l,r);
		find(tree.r,r);
	}
	public static TreeNode construct(TreeNode p, int height,int dir){
		if(height-1 == p.level) return null;
		TreeNode t = new TreeNode();
		t.level = p.level+1;		
		if(dir == 0){//左子树
			t.x = p.x + len[t.level]+1;
			t.y = p.y - len[t.level]-1;
		}
		else{
			t.x = p.x + len[t.level]+1;
			t.y = p.y + len[t.level]+1;
		}			
		t.l = construct(t,height,0);
		t.r = construct(t,height,1);
		return t;
	}
}
