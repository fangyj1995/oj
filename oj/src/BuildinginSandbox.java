import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class BuildinginSandbox {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}
	
	static boolean[][][] world;
	static int[][] boxs;
	static int[] id;
	static int N = 100;
	static boolean[][][] marked;
	static int cnt;
	static Cord source = new Cord(N+1,N+1,N+1);
	static int[][]dir = new int[][]{
		{1,0,0},
		{-1,0,0},
		{0,1,0},
		{0,-1,0},
		{0,0,1},
		{0,0,-1}
	};
	static class Cord{
		final int x,y,z;
		public Cord(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		public Cord(int[] box) {
			this.x = box[0];
			this.y = box[1];
			this.z = box[2];
		}
		public int id() {		
			return x*(N + 2)*(N + 2) + y*(N + 2) + z;
		}
		public String toString(){
			return x+" , "+y+" , "+z;
		}
	}
	public static void main(String[] args) throws IOException {
		int cases = nextInt();
		for(int i = 0 ; i < cases; i++){
			int n = nextInt();
			world = new boolean[N+2][N+2][N+2];
			boxs = new int[n][3];
			id = new int[(N+2)*(N+2)*(N+2)];
			marked = new boolean[N + 2][N + 2][N + 2];
			cnt = 0;	
			boolean canBuild = true;
			for(int j = 0 ;j < n; j++){
				int x = nextInt();
				int y = nextInt();
				int z = nextInt();
				if(!canBuild) continue;
				Cord c = new Cord(x,y,z);
				boxs[j][0] = x;
				boxs[j][1] = y;
				boxs[j][2] = z;
				if(!build(c))
					canBuild = false;	
				else
					world[x][y][z] = true;
			}
			if(!canBuild) {
				System.out.println("No");
				continue;
			}
			for(int x = 1 ; x < N+1; x++)
				for(int y = 1 ; y < N+1; y++)
					world[x][y][0] = true;
			for(int j = 0 ;j < (N+2)*(N+2)*(N+2); j++)
				id[j] = j;	
			connect();//联通整个空间
			unBuild();
		}
	}
	public static void connect(){
		for(int x = 0 ; x < N+2; x++)
			for(int y = 0 ; y < N+2; y++)
				for(int z = 0 ; z < N+2; z++){					
					if(!world[x][y][z]&&!marked[x][y][z]){
						Cord c = new Cord(x,y,z);
						bfs(c);
						cnt++;
					}
				}			
	}
	private static void bfs(Cord s) {
		Queue<Cord> queue = new LinkedList<>();
		queue.offer(s);
		marked[s.x][s.y][s.z] = true;
		while(!queue.isEmpty()){
			Cord v = queue.poll();
			id[v.id()] = s.id();
			for(int i = 0 ; i < dir.length;i++){
				int x = v.x + dir[i][0];
				int y = v.y + dir[i][1];
				int z = v.z + dir[i][2];
				if(checkVertex(x,y,z)&&!world[x][y][z] && !marked[x][y][z]){
					queue.offer(new Cord(x,y,z));
					marked[x][y][z] = true;				
				}
			}
		}
	}
	private static void unBuild() {		
		for(int i = boxs.length-1 ; i >= 0 ; i-- ){
			Cord c = new Cord(boxs[i]);
			world[c.x][c.y][c.z] = false;
			for(int d = 0 ; d < dir.length;d++){
				int x = c.x + dir[d][0];
				int y = c.y + dir[d][1];
				int z = c.z + dir[d][2];
				if(checkVertex(x,y,z)&&!world[x][y][z])
					union(c, new Cord(x,y,z));
			}	
			if(!connected(c,source)){
				System.out.println("No");
				return;
			}
		}
		System.out.println("Yes");
	}
	private static boolean checkVertex(int x, int y, int z) {
		return !(x < 0 || x > N+1||y < 0 || y > N+1||z < 0 || z > N+1);
	}
	private static boolean build(Cord c) {
		if(world[c.x][c.y][c.z]) return false;
		if(c.z == 1)  return true;	
		for(int i = 0 ; i < dir.length;i++){
			int x = c.x + dir[i][0];
			int y = c.y + dir[i][1];
			int z = c.z + dir[i][2];
			if(world[x][y][z]) return true;
		}
		return false;
	}
	private static void union(Cord c1, Cord c2){
		int r1 = findRoot(c1);
		int r2 = findRoot(c2);
		id[r1] = id[r2];
	}
	public static boolean connected(Cord c1, Cord c2){
		int r1 = findRoot(c1);
		int r2 = findRoot(c2);
		return r1==r2;
	}
	private static int findRoot(Cord c) {
		int p = c.id();
		while(id[p] != p)
			p = id[p];
		return p;
	}
}
