

import java.io.*;
import java.util.*;

public class StableMembers {
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public  int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  

	
	private class Member{
		int id;
		List<Member> mentors;
		List<Member> students;
		int converge;
		int degree;
		
		public Member(int id){
			this.id = id;
		}
		public void check(){
			if(mentors.size() == 0){
				converge = -1;
				return;
			}
			Set<Integer> set = new HashSet<Integer>();
			for(Member mentor :  mentors){
				if(mentor.id == 0){
					converge = id;
					return;
				}
				converge = mentor.converge;
				set.add(mentor.converge);
			}
			if(set.size() > 1){
				converge = id;
			}
		}
		
	}
	
	int n;
	Member[] members;
	public static void main(String[] args) throws IOException{	
		StableMembers main = new StableMembers();
		main.run();
	}
	public void  run() throws IOException{
		input();
		for(int i = 1 ; i <= n ; i++){
			members[i].degree = members[i].mentors.size();
		}
		members[0].converge = 0;
		Queue<Member> queue = new LinkedList<Member>();
		queue.offer(members[0]);
		while(!queue.isEmpty()){
			Member m = queue.poll();
			for(Member stu : m.students){
				stu.degree--;
				stu.check();
				if(stu.degree == 0)
					queue.offer(stu);
			}
		}
		int cnt = 0;
		for(int i = 1 ; i <= n ; i++){
			if(members[i].id != members[i].converge){
				cnt++;
			}
		}
		System.out.println(n-cnt);
	}
	private void input() throws IOException{
		n = nextInt();
		members = new Member[n+1];		
		for(int i = 0 ; i <= n ; i++){
			members[i] = new Member(i);
			members[i].mentors = new LinkedList<Member>();
			members[i].students = new LinkedList<Member>();
		}
		for(int i = 1 ; i <= n ; i++){
			int m = nextInt();
			for(int j = 0 ; j < m; j++){
				int mentor = nextInt();
				members[i].mentors.add(members[mentor]);
				members[mentor].students.add(members[i]);
			}
		}
	}
}



