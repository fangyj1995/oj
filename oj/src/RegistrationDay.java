import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class RegistrationDay {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}       
	static int n, m , k;
	static class Stu{
		int id;
		int finish;
		Queue<Pce> pceList;
		public Stu(int id) {
			super();
			this.id = id;
			pceList = new LinkedList<Pce>();
		}
		boolean hasNextPce(){
			return !pceList.isEmpty();
		}
		void updateNextPce(int arriveTime){
			pceList.peek().arrTime = arriveTime;
		}
		Pce removeNextPce(){
			return pceList.poll();
		}
	}
	static class Pce{
		int office;
		int arrTime;
		int time;
		Stu stu;
		public Pce(int office, int arrTime, int time, Stu stu) {
			super();
			this.office = office;
			this.arrTime = arrTime;
			this.time = time;
			this.stu = stu;
		}
		public String toString(){
			return "stu:"+stu.id+" office:"+office+" arrive at"+arrTime+" time:"+time;
		}
	}
	public static void main(String[] args) throws IOException {
		n = nextInt();
		m = nextInt();
		k = nextInt();
		Stu[] stus = new Stu[n];
		PriorityQueue<Pce> queue = new PriorityQueue<>(100,new Comparator<Pce>(){
			@Override
			public int compare(Pce o1, Pce o2) {
				if(o1.arrTime!=o2.arrTime)
					return o1.arrTime - o2.arrTime;
				else
					return o1.stu.id - o2.stu.id;
			}			
		});
		for(int i = 0 ; i < n; i++){
			Stu stu = new Stu(nextInt());//id
			int arrTime = nextInt();
			int pceNum = nextInt();
			for(int j = 0; j < pceNum; j++){
				Pce pce = new Pce(nextInt(),-1,nextInt(),stu);
				stu.pceList.offer(pce);
			}
			Pce firstPce = stu.removeNextPce();
			firstPce.arrTime = arrTime + k;			
			queue.offer(firstPce);
			stus[i] = stu;
		}
		int[] t = new int[m+1];//每个部门维护一条时间线
		while(!queue.isEmpty()){
			Pce pce = queue.poll();
			Stu stu = pce.stu;
			int office = pce.office;
			if(pce.arrTime > t[office])//到达时间大于部门现在时间，说明部门空闲，将部门时间推移到学生的到达时间
				t[office] = pce.arrTime;
			t[office] += pce.time; //处理手续
			if(!stu.hasNextPce()){
				stu.finish = t[office];
			}
			else{
				stu.updateNextPce(t[office]+k);//更新学生的下一个手续到达时间
				queue.offer(stu.removeNextPce());
			}			
		}
		for(int i = 0 ; i < n; i++){
			System.out.println(stus[i].finish);
		}
	}
}
