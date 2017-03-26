import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by fangyj on 2017/2/19.
 */
class Module{
    int s;
    int id;
    int cnt;
    LinkedList<Integer> signals;
    public Module(int s, int id){
        this.s = s;
        this.id = id;
        signals  = new LinkedList<>();
    }

}
public class ProfessorQsSoftware {
    static Module[] modules;
    static int[] inDegree;
    int n;//the number of modules
    int m;//signals that Professor Q generates initially
    int[] initials;
    //Map<Integer, List<Integer>> map = new HashMap<>();
    List<Integer>[] map ;
    List<Integer>[] adj ;
    public ProfessorQsSoftware(int n, int m){
        this.n = n;
        this.m = m;
        map = ( List<Integer>[]) new LinkedList[100001];
        adj = ( List<Integer>[]) new LinkedList[n];
        initials = new int[m];
        inDegree = new int[n];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       int t,n,m;
       t = Integer.parseInt(reader.readLine());
       for(int i = 0 ; i < t; i++){
           String[] line = reader.readLine().split("\\s+");
           assert(line.length == 2);
           int N = Integer.parseInt(line[0]);
           int M = Integer.parseInt(line[1]);
           ProfessorQsSoftware software = new ProfessorQsSoftware(N,M);
           line = reader.readLine().split("\\s+");//each line describes an module
           assert line.length == M;
           for(int j = 0; j < M ; j++)
               software.initials[j] = Integer.parseInt(line[j]);
           modules = new Module[N];
           int[][] signals = new int[N][3];
           int[] ks = new int[N];
           for(int j = 0 ; j < N ; j++){
               line = reader.readLine().split("\\s+");//each line describes an module
               int s = Integer.parseInt(line[0]);
               int k = Integer.parseInt(line[1]);
               ks[j] = k;
               assert line.length == 2 + k;
               modules[j] = new Module(s,j);
               for(int l = 0 ; l < k ; l++){
                   int signal = Integer.parseInt(line[l+2]);
                   modules[j].signals.add(signal);
               }
               software.addModule(s,j);
           }
           for(int j = 0 ; j < N ; j++){
               software.adj[j] = new LinkedList<Integer>();
               for(int signal : modules[j].signals){//模块发射的信号
                   if(software.map[signal] == null) continue;
                   for(int w : software.map[signal]){//信号激活的模块
                       software.adj[j].add(w);
                       inDegree[w]++;
                   }
               }
           }
           software.start();
           for(Module module: modules)
               System.out.print(module.cnt % 142857+" ");
           System.out.println();
       }
    }
    public void addModule(int s, int m) {
        if(map[s] == null)
            map[s] = new LinkedList<Integer>();
        map[s].add(m);
    }
    public void start() {
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0 ; i < m; i++){
           if(map[initials[i]] == null) continue;
           List<Integer> list = map[initials[i]];
           for(int id: list) {
               modules[id].cnt++;
           }
       }
       for(int i = 0 ; i < n ; i++){
            if(inDegree[i] == 0)
                queue.add(i);
       }
       while (!queue.isEmpty()){
           int v = queue.poll();
           if(adj[v] == null) {
               continue;
           }
           for(int w : adj[v]){
               modules[w].cnt += modules[v].cnt;
               if( --inDegree[w] == 0)
                   queue.offer(w);
           }
       }

    }
}
