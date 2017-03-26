package leetcode;
import java.util.*;

public class _399EvaluateDivision 
{
    double result=1;
    Set<String> visited;
    boolean found;
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        //equations=edges, values=weight
        Graph g=new Graph(equations,values);
        double[] res=new double[queries.length];
        for(int i=0;i<queries.length;i++){
            result=1;
            found=false;
            visited=new HashSet<String>();
            String s=queries[i][0];
            String e=queries[i][1];
            if(g.adj(s)==null||g.adj(e)==null) res[i]=-1;
            else{
                for(Edge n:g.adj(s))
                    dfs(g,n.v,e);
                res[i]=result;
            }
        }
        return res;
    }
    void dfs(Graph g,String s,String e){
        if(s==e) {
            found=true;
            return;
        }
        visited.add(s);
        for(Edge n:g.adj(s))
            if(!found||!visited.contains(n.v)){
                result*=n.weight;
                dfs(g,n.v,e);
            }
    }
    public static void main(String[] args) {
    	
    }
    
}
class Edge
{
        String v;
        double weight;
        public Edge(String v,double weight){
            this.v=v;
            this.weight=weight;
        }
}
class Graph
{
    Set<String> nodeSet=new HashSet<String>();
    Map<String,List<Edge>> adj=new HashMap<String,List<Edge>>();
    int v;
    int e;
    public Graph(String[][] edges,double[] weights){
        e=edges.length;//设置边的数量
        for(String[] edge:edges){//初始化所有节点
            if(!nodeSet.contains(edge[0])){
                nodeSet.add(edge[0]);
            }
            if(!nodeSet.contains(edge[1])){
                nodeSet.add(edge[1]);
            }
        }
        this.v=nodeSet.size();//设置顶点数量
        for(String v:nodeSet){//初始化邻接表的引用
            adj.put(v,new LinkedList<Edge>());
        }
        for(int i=0;i<e;i++){//初始化所有边
             this.addEdge(edges[i],weights[i]);
        }
    }
    private void addEdge(String[] edge,double weight){
        Edge e1=new Edge(edge[1],weight);
        Edge e2=new Edge(edge[0],1/weight);
        adj.get(edge[0]).add(e1);
        adj.get(edge[1]).add(e2);
    }
    public List<Edge> adj(String key){
        return adj.get(key);
    }
    public boolean exist(String n){
        return nodeSet.contains(n);
    }
}