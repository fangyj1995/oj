import java.util.Scanner;

public class SwimmingPlans {
	private static int T,L,R,N,Q;
	private static class Plan{//已有的游泳计划
		int t,l,n,d;
		public Plan(int t, int l, int n , int d){
			this.t = t;//开始游泳时间
			this.l = l;//游泳持续时间
			this.n = n;//泳道
			this.d = d;//方向
		}		
	}
	private static class Event implements Comparable<Event>{//steve 的游泳事件
		int t , n , x;//x: 该事件向量的类型，1表示最晚事件向量，-1表示最早事件向量。
		Plan plan;
		
		public Event(int t, int n, int x,Plan plan) {
			this.t = t;
			this.n = n;
			this.x = x;
			this.plan = plan;
		}

		@Override
		public int compareTo(Event o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	
	public static void swimmingPlans(Plan[] plans){
		Event[][] events = new Event[2][2 * Q];
		for(Plan plan:plans){
			addEvent(events,plan);			
		}
		
		/*lane[0][i]表示当前时刻对于泳道i，从游泳池0侧出发，会影响到小Hi游泳的人数。
		 * 同理lane[1][i]表示从游泳池1侧出发。
		*/
		int[][] lane = new int[2][N];
		int time = T;        // 当前时间
		int dir = 0;   // 游泳的方向
		int[] point = new int[]{0, 0}; // 两个方向已经处理过的事件数量
		for(int r = 0 ; r < R ; r++){
			boolean found = false;
			while(point[dir] < 2 * Q){
				Event e = events[dir][point[dir]];
				if(time <= e.t){//当前时间在事件的时间之前
					for(int i = 0 ; i < N ; i++){//检查每个泳道
						if(lane[dir][i] == 0){//该泳道无人占用
							found = true;
							time += L;
							break;
						}
					}
					if(found) break;
					//not found
					// 此时表示在T~event.t这段时间内均没有可以使用的泳道
		            // 则我们直接将时间T推移至event.t，并执行这个事件向量
					time = e.t;
					lane[dir][e.n] += e.x;
					point[dir]++;
				}
				else{//time > e.t  , 当前时间在事件的时间之后
					/*如果比最晚出发时间t1晚,则该泳道该方向影响人数加一
					 * 如果比最早出发时间t2晚,则该泳道该方向影响人数减一
					 * */
					lane[dir][e.n] += e.x;//x: 该事件向量的类型，1表示最晚事件向量，-1表示最早事件向量。
					point[dir]++;
				}			
			}
			if(!found) time += L;
	        // 会出现found为false的情况，则表明该方向所有的event都已经结束
	        // 此时只有小Hi一个人还在游泳
			dir = 1 - dir;
		}
		System.out.println(time);
	}
	
	
	public static void addEvent(Event[][] events, Plan plan){
		
	}
	
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();
		for(int i = 0; i < m ;i++){
			T = scan.nextInt();
			L = scan.nextInt();
			R = scan.nextInt();
			N = scan.nextInt();
			Q = scan.nextInt();
			Plan[] plans = new Plan[Q];
			for(int j  = 0 ; j < Q ; j++){
				int t = scan.nextInt();
				int l = scan.nextInt();
				int n = scan.nextInt();
				int d = scan.nextInt();
				plans[j] = new Plan(t,l,n,d);
				swimmingPlans(plans);
			}
			
		}
	}
}
