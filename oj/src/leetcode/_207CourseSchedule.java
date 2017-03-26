package leetcode;
import java.util.LinkedList;
import java.util.List;

public class _207CourseSchedule {
	public static void main(String[] args) {
		int pre[][]={{1,0},{0,1}};
		new Solution1().canFinish(2,pre);
	}
}
class Solution1 {	
	boolean hasCycle=false;
	boolean[] marked;
	boolean[] onStack;
 public boolean canFinish(int numCourses, int[][] prerequisites) {
	     List<Integer>[] adjList=(List<Integer>[])new LinkedList[numCourses];
	     for(int i=0;i<numCourses;i++)
	          adjList[i]=new LinkedList<Integer>(); 
		int e=prerequisites.length;	
		marked=new boolean[numCourses];	
		onStack=new boolean[numCourses];
		
		//初始化有向图
		for(int i=0;i<e;i++)
			adjList[prerequisites[i][0]].add(prerequisites[i][1]);
		for(int i=0;i<e;i++)
		{
			if(hasCycle) return false;
			if(!marked[i])
				dfs(adjList,i);
		}
		dfs(adjList,0);
		return !hasCycle;
 }
	private void dfs(List<Integer>[] adjList,int s)
	{
		marked[s]=true;
		onStack[s]=true;
		for(int v:adjList[s])//s->v
		{
			if(!marked[v])
				dfs(adjList,v);
			else if(onStack[v])
			{
				hasCycle=true;
				return;
			}
		}
		onStack[s]=false;
	}
}
