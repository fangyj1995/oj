
import java.io.*;
import java.util.*;

public class RegistrationDay {
	// Scanner scan = new Scanner(System.in);
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public  int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public  double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public  String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	int n;
	int m;
	int k;
	Student[] stus;
	int[] officeTime;
	private class Student{
		int id;
		int arrT;
		int leaveT;
		ArrayList<Procedure> pList;
		int curProcess;
		
		Procedure getCurProcedure(){
			return pList.get(curProcess);
		}
		
	}
	private class Procedure implements Comparable<Procedure>{
		Student stu;
		int arrT = Integer.MAX_VALUE;
		int office;
		int time;
		
		@Override
		public int compareTo(Procedure o) {
			if(arrT != o.arrT)
				return arrT - o.arrT;
			
			return this.stu.id - o.stu.id;			
		}
		
		Procedure processAndGetNext(){						
			officeTime[office] = Math.max(officeTime[office], arrT);
			officeTime[office] += time;
			
			stu.curProcess++;	
			if(stu.curProcess >= stu.pList.size()){
				stu.leaveT = officeTime[office];
				return null;
			}
			
			Procedure p =  stu.getCurProcedure();		
			p.arrT = officeTime[office] + k;
			return p;
		}
		
	}

	public static void main(String[] args) throws IOException{	
		RegistrationDay main = new RegistrationDay();
		main.run();
	}
	public void  run() throws IOException{
		input();
		PriorityQueue<Procedure> pq = new PriorityQueue<Procedure>(n);
		for(int i = 1 ; i <= n ; i++){
			pq.offer(stus[i].getCurProcedure());
		}
		while(!pq.isEmpty()){
			Procedure p = pq.poll();
			Procedure next = p.processAndGetNext();
			if(next != null)
				pq.offer(next);
		}
		for(int i = 1 ; i <= n ; i++){
			System.out.println(stus[i].leaveT);
		}
	}
	private void input() throws IOException{
		n = nextInt();
		m = nextInt();
		k = nextInt();
		stus = new Student[n+1];
		officeTime = new int[m+1];
		for(int i = 1 ; i <= n ; i++){
			stus[i] = new Student();
			stus[i].id = nextInt();
			stus[i].arrT = nextInt();
			int cnt = nextInt();
			stus[i].pList = new ArrayList<Procedure>(cnt);
			for(int j = 0 ; j < cnt ; j++){
				Procedure p = new Procedure();
				p.stu = stus[i];
				p.office = nextInt();
				p.time = nextInt();
				if(j == 0)
					p.arrT = stus[i].arrT+k;
				stus[i].pList.add(p);
			}
			stus[i].curProcess = 0;
		}
	}
}


